package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Package;
import com.silanis.esl.api.model.Role;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.BasePackageType;
import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.Placeholder;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.internal.converter.BasePackageTypeConverter;
import com.silanis.esl.sdk.internal.converter.DocumentPackageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * The TemplateService class provides methods to help create templates from packages.
 */
public class TemplateService extends EslComponent {

    private PackageService packageService;

    public TemplateService(RestClient client, String baseUrl, PackageService packageService) {
        super(client, baseUrl);
        this.packageService = packageService;
    }

    /**
     * Creates a template from package.
     *
     * @param originalPackageId the package ID used as template
     * @param delta             the document package to replace in the template
     * @return the package ID
     */
    public PackageId createTemplateFromPackage(PackageId originalPackageId, DocumentPackage delta) {
        Package deltaPackage = new DocumentPackageConverter(delta).toAPIPackage();
        deltaPackage.setType(new BasePackageTypeConverter(BasePackageType.TEMPLATE).toAPIBasePackageType());

        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.TEMPLATE_PATH)
                .replace("{packageId}", originalPackageId.getId())
                .build();
        String deltaJson = Serialization.toJson(deltaPackage);

        Package returnedPackage;
        try {
            String response = getClient().post(path, deltaJson);
            returnedPackage = Serialization.fromJson(response, Package.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create template", e);
        } catch (Exception e) {
            throw new EslException("Could not create template", e);
        }

        return new DocumentPackageConverter(returnedPackage).toSDKPackage().getId();
    }

    /**
     * Creates a template from package.
     *
     * @param originalPackageId the package ID used as template
     * @param templateName      the name of new template
     * @return the package ID
     */
    public PackageId createTemplateFromPackage(PackageId originalPackageId, String templateName) {
        DocumentPackage sdkPackage = PackageBuilder.newPackageNamed(templateName).build();
        return createTemplateFromPackage(originalPackageId, sdkPackage);
    }

    /**
     * @deprecated Please use createPackageFromTemplate( PackageId packageId, DocumentPackage documentPackage ) instead of this method.
     */
    @Deprecated
    public PackageId createPackageFromTemplate(DocumentPackage documentPackage, PackageId packageId) {
        return createPackageFromTemplate(packageId, documentPackage);
    }

    /**
     * Creates a package based on an existent template.
     *
     * @param packageId       the package ID used as template for the new package
     * @param documentPackage the document package
     * @return the package ID
     */
    public PackageId createPackageFromTemplate(PackageId packageId, DocumentPackage documentPackage) {
        setNewSignersIndexIfRoleWorkflowEnabled(packageId, documentPackage);
        Package packageToCreate = new DocumentPackageConverter(documentPackage).toAPIPackage();

        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.TEMPLATE_PATH)
                .replace("{packageId}", packageId.getId())
                .build();
        String packageJson = Serialization.toJson(packageToCreate);

        Package createdPackage;
        try {
            String response = getClient().post(path, packageJson);
            createdPackage = Serialization.fromJson(response, Package.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create a new package from template", e);
        } catch (Exception e) {
            throw new EslException("Could not create a new package from template", e);
        }

        return new PackageId(createdPackage.getId());
    }

    private void setNewSignersIndexIfRoleWorkflowEnabled(PackageId packageId, DocumentPackage documentPackage) {
        DocumentPackage template = new DocumentPackageConverter(packageService.getApiPackage(packageId.getId())).toSDKPackage();
        if (checkSignerOrdering(template)) {
            int firstSignerIndex = getMaxSigningOrder(template, documentPackage) + 1;
            for (Signer signer : documentPackage.getSigners()) {
                Signer templatePlaceholder = template.getPlaceholder(signer.getId());
                if (templatePlaceholder != null) {
                    signer.setSigningOrder(templatePlaceholder.getSigningOrder());
                }

                if (signer.getSigningOrder() <= 0) {
                    signer.setSigningOrder(firstSignerIndex);
                    firstSignerIndex++;
                }
            }
        }
    }

    private int getMaxSigningOrder(DocumentPackage template, DocumentPackage documentPackage) {
        List<Signer> signers = new ArrayList<Signer>();
        signers.addAll(documentPackage.getSigners());
        signers.addAll(template.getSigners());
        int maxSigningOrder = 0;
        for (Signer signer : signers) {
            if (signer.getSigningOrder() > maxSigningOrder) {
                maxSigningOrder = signer.getSigningOrder();
            }
        }
        return maxSigningOrder;
    }

    private boolean checkSignerOrdering(DocumentPackage template) {
        List<Signer> signers = new ArrayList<Signer>();
        signers.addAll(template.getSigners());
        signers.addAll(template.getPlaceholders());
        for (Signer signer : signers) {
            if (signer.getSigningOrder() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a template.
     *
     * @param template the document package
     * @return the package ID
     */
    public PackageId createTemplate(DocumentPackage template) {
        Package packageToCreate = new DocumentPackageConverter(template).toAPIPackage();
        packageToCreate.setType(new BasePackageTypeConverter(BasePackageType.TEMPLATE).toAPIBasePackageType());
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.PACKAGE_PATH).build();
        String packageJson = Serialization.toJson(packageToCreate);

        PackageId templateId;
        try {
            String response = getClient().post(path, packageJson);
            templateId = Serialization.fromJson(response, PackageId.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create template", e);
        } catch (Exception e) {
            throw new EslException("Could not create template", e);
        }

        for (Document document : template.getDocuments()) {
            packageService.uploadDocument(templateId, document.getFileName(), document.getContent(), document);
        }

        return templateId;
    }

    /**
     * Update a template.
     *
     * @param template the document package
     */
    public void updateTemplate(DocumentPackage template) {
        if (template.getId() == null) {
            throw new IllegalArgumentException("templateId cannot be null");
        }

        Package packageToUpdate = new DocumentPackageConverter(template).toAPIPackage();
        packageToUpdate.setType(new BasePackageTypeConverter(BasePackageType.TEMPLATE).toAPIBasePackageType());

        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.PACKAGE_ID_PATH)
                .replace("{packageId}", packageToUpdate.getId())
                .build();

        String packageJson = Serialization.toJson(packageToUpdate);

        try {
            getClient().put(path, packageJson);
        } catch (RequestException e) {
            throw new EslServerException("Could not update template", e);
        } catch (Exception e) {
            throw new EslException("Could not update template", e);
        }
    }

    /**
     * Adds a placeholder to the template.
     *
     * @param templateId
     * @param placeholder
     * @return The role added
     * @throws EslException
     */
    public Placeholder addPlaceholder(PackageId templateId, Placeholder placeholder) throws EslException {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ROLE_PATH)
                .replace("{packageId}", templateId.getId())
                .build();

        String placeholderJson = JacksonUtil.serializeDirty(placeholder);
        String stringResponse;
        try {
            stringResponse = getClient().post(path, placeholderJson);
        } catch (RequestException e) {
            throw new EslServerException("Could not add placeholder.", e);
        } catch (Exception e) {
            throw new EslException("Could not add placeholder.", e);
        }
        Role role = Serialization.fromJson(stringResponse, Role.class);

        return new Placeholder(role.getId(), role.getName());
    }

    /**
     * Update a placeholder.
     *
     * @param templateId
     * @param placeholder
     * @return The role added
     * @throws EslException
     */
    public Placeholder updatePlaceholder(PackageId templateId, Placeholder placeholder) throws EslException {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ROLE_ID_PATH)
                .replace("{packageId}", templateId.getId())
                .replace("{roleId}", placeholder.getId())
                .build();

        String placeholderJson = JacksonUtil.serializeDirty(placeholder);
        String stringResponse;
        try {
            stringResponse = getClient().put(path, placeholderJson);
        } catch (RequestException e) {
            throw new EslServerException("Could not update the placeholder.", e);
        } catch (Exception e) {
            throw new EslException("Could not update the placeholder.", e);
        }
        Role role = Serialization.fromJson(stringResponse, Role.class);

        return new Placeholder(role.getId(), role.getName());
    }
}
