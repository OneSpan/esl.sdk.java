package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Package;
import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.internal.converter.DocumentPackageConverter;

/**
 * The TemplateService class provides methods to help create templates from packages.
 */
public class TemplateService {

    private UrlTemplate urls;
    private RestClient client;
    private PackageService packageService;

    public TemplateService(RestClient client, String baseUrl, PackageService packageService) {
        this.client = client;
        urls = new UrlTemplate(baseUrl);
        this.packageService = packageService;
    }

    /**
     * Creates a template from package.
     *
     * @param originalPackageId	the package ID used as template
     * @param delta the document package to replace in the template
      * @return	the package ID
     */
    public PackageId createTemplateFromPackage(PackageId originalPackageId, DocumentPackage delta) {
        Package deltaPackage = new DocumentPackageConverter(delta).toAPIPackage();
        deltaPackage.setType("TEMPLATE");

        String path = urls.urlFor(UrlTemplate.TEMPLATE_PATH)
                .replace("{packageId}", originalPackageId.getId())
                .build();
        String deltaJson = Serialization.toJson(deltaPackage);

        Package returnedPackage;
        try {
            String response = client.post(path, deltaJson);
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
     * @param originalPackageId	the package ID used as template
     * @param templateName the name of new template
     * @return	the package ID
     */
    public PackageId createTemplateFromPackage(PackageId originalPackageId, String templateName) {
        DocumentPackage sdkPackage = PackageBuilder.newPackageNamed(templateName).build();
        return createTemplateFromPackage(originalPackageId, sdkPackage);
    }

    /**
     * Creates a package based on an existent template.
     *
     * @param documentPackage the document package
     * @param packageId	the package ID used as template for the new package
     * @return	the package ID
     */
    public PackageId createPackageFromTemplate(DocumentPackage documentPackage, PackageId packageId) {
        Package packageToCreate = new DocumentPackageConverter(documentPackage).toAPIPackage();
        return packageService.createPackageFromTemplate( packageId, packageToCreate );
    }

    /**
     * Creates a package based on an existent template.
     *
     * @param packageId	the package ID used as template for the new package
     * @param documentPackage the document package
     * @return	the package ID
     */
    public PackageId createPackageFromTemplate(PackageId packageId, DocumentPackage documentPackage) {
        setNewSignersIndexIfRoleWorkflowEnabled(packageId, documentPackage);
        Package packageToCreate = new DocumentPackageConverter(documentPackage).toAPIPackage();

        String path = urls.urlFor(UrlTemplate.TEMPLATE_PATH)
                .replace( "{packageId}", packageId.getId() )
                .build();
        String packageJson = Serialization.toJson(packageToCreate);

        Package createdPackage;
        try {
            String response = client.post(path, packageJson);
            createdPackage = Serialization.fromJson( response, Package.class );
        } catch ( RequestException e ) {
            throw new EslServerException( "Could not create a new package from template", e );
        } catch ( Exception e ) {
            throw new EslException( "Could not create a new package from template", e );
        }

        return new PackageId(createdPackage.getId());
    }

    private void setNewSignersIndexIfRoleWorkflowEnabled(PackageId packageId, DocumentPackage documentPackage) {
        DocumentPackage template = new DocumentPackageConverter(packageService.getApiPackage(packageId.getId())).toSDKPackage();
        if (checkSignerOrdering(template)) {
            int firstSignerIndex = template.getSigners().size();
            for(Signer signer : documentPackage.getSigners().values()){
                signer.setSigningOrder(firstSignerIndex);
                firstSignerIndex++;
            }
        }
    }

    private boolean checkSignerOrdering(DocumentPackage template) {
        for(Signer signer : template.getSigners().values()) {
            if (signer.getSigningOrder() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a template.
     *
     * @param template	the document package
     * @return	the package ID
     */
    public PackageId createTemplate(DocumentPackage template) {
        Package packageToCreate = new DocumentPackageConverter(template).toAPIPackage();
        packageToCreate.setType("TEMPLATE");
        String path = urls.urlFor(UrlTemplate.PACKAGE_PATH).build();
        String packageJson = Serialization.toJson(packageToCreate);

        PackageId templateId;
        try {
            String response = client.post(path, packageJson);
            templateId = Serialization.fromJson(response, PackageId.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create template", e);
        } catch (Exception e) {
            throw new EslException("Could not create template", e);
        }

        DocumentPackage createdTemplate = packageService.getPackage(templateId);

        for (Document document : template.getDocuments()) {
            packageService.uploadDocument( templateId, document.getFileName(), document.getContent(), document, createdTemplate);
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
        packageToUpdate.setType("TEMPLATE");

        String path = urls.urlFor(UrlTemplate.PACKAGE_ID_PATH)
                          .replace( "{packageId}", packageToUpdate.getId() )
                          .build();

        String packageJson = Serialization.toJson(packageToUpdate);

        try {
            client.post(path, packageJson);
        } catch (RequestException e) {
            throw new EslServerException("Could not update template", e);
        } catch (Exception e) {
            throw new EslException("Could not update template", e);
        }
    }
}
