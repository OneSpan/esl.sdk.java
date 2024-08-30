package com.silanis.esl.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.silanis.esl.api.model.*;
import com.silanis.esl.api.model.Package;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.internal.converter.DocumentConverter;
import com.silanis.esl.sdk.internal.converter.DocumentPackageConverter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * The LayoutService class provides methods to help create and apply document layouts.
 */
public class LayoutService extends EslComponent {

    public LayoutService(RestClient restClient, String baseUrl) {
        super(restClient, baseUrl);
    }

    /**
     * Create a document layout from an already created DocumentPackage. Will only save document fields for one document
     * in the package.
     *
     * @param layout the DocumentPackage with one document from which to create layout.
     * @return The layout id.
     */
    public String createLayout(DocumentPackage layout) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.LAYOUT_PATH)
                .build();

        Package layoutToCreate = new DocumentPackageConverter(layout).toAPIPackage();

        for (com.silanis.esl.sdk.Document document : layout.getDocuments()) {
            layoutToCreate.addDocument(new DocumentConverter(document).toAPIDocument(layoutToCreate));
        }

        String packageString = Serialization.toJson(layoutToCreate);
        Template template = Serialization.fromJson(packageString, Template.class);
        template.setId(layout.getId().getId());
        String templateString = Serialization.toJson(template);

        try {
            String response = getClient().post(path, templateString);
            Package aPackage = Serialization.fromJson(response, Package.class);
            return aPackage.getId();
        } catch (RequestException e) {
            throw new EslServerException("Could not create layout.", e);
        } catch (Exception e) {
            throw new EslException("Could not create layout." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Create and get a layout from an already created DocumentPackage.
     *
     * @param layout the DocumentPackage with one document from which to create layout.
     * @return DocumentPackage layout.
     */
    public DocumentPackage createAndGetLayout(DocumentPackage layout) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.LAYOUT_PATH)
                .build();

        Package layoutToCreate = new DocumentPackageConverter(layout).toAPIPackage();

        for (com.silanis.esl.sdk.Document document : layout.getDocuments()) {
            layoutToCreate.addDocument(new DocumentConverter(document).toAPIDocument(layoutToCreate));
        }

        String packageString = Serialization.toJson(layoutToCreate);
        Template template = Serialization.fromJson(packageString, Template.class);
        template.setId(layout.getId().getId());
        String templateString = Serialization.toJson(template);

        try {
            String response = getClient().post(path, templateString);
            Package aPackage = Serialization.fromJson(response, Package.class);
            return new DocumentPackageConverter(aPackage).toSDKPackage();
        } catch (RequestException e) {
            throw new EslServerException("Could not create layout.", e);
        } catch (Exception e) {
            throw new EslException("Could not create layout." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Get a list of layouts (DocumentPackages).
     *
     * @param direction of retrieved list to be sorted in ascending or descending order by name.
     * @param request   Identifying which page of results to return.
     * @return The list of layouts.
     */
    public List<DocumentPackage> getLayouts(Direction direction, PageRequest request) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.LAYOUT_LIST_PATH)
                .replace("{dir}", direction.getDirection())
                .replace("{from}", Integer.toString(request.getFrom()))
                .replace("{to}", Integer.toString(request.to()))
                .build();

        try {
            String response = getClient().get(path);
            Result<com.silanis.esl.api.model.Package> results = JacksonUtil.deserialize(response, new TypeReference<Result<Package>>() {
            });

            List<DocumentPackage> layouts = new ArrayList<DocumentPackage>();
            for (Package layout : results.getResults()) {
                layouts.add(new DocumentPackageConverter(layout).toSDKPackage());
            }
            return layouts;
        } catch (RequestException e) {
            throw new EslServerException("Could not get list of layouts.", e);
        } catch (Exception e) {
            throw new EslException("Could not get list of layouts." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Apply a document layout to a document in a DocumentPackage. Adds fields to signer's signature or if the signer
     * does not exist, will create placeholders.
     *
     * @param packageId  The package id of the DocumentPackage to apply layout.
     * @param documentId The document id of the document to apply layout.
     * @param layoutId   The layout id of the layout to apply.
     */
    public void applyLayout(PackageId packageId, String documentId, String layoutId) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.APPLY_LAYOUT_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{documentId}", documentId)
                .replace("{layoutId}", layoutId)
                .build();

        try {
            getClient().post(path, "");
        } catch (RequestException e) {
            throw new EslServerException("Could not apply layout.", e);
        } catch (Exception e) {
            throw new EslException("Could not apply layout." + " Exception: " + e.getMessage());
        }
    }
    
    /**
     * Apply a document layout to a document in a DocumentPackage. Adds fields to signer's signature or if the signer
     * does not exist, will create placeholders.
     *
     * @param packageId  The package id of the DocumentPackage to apply layout.
     * @param documentId The document id of the document to apply layout.
     * @param layoutName   The layout name of the layout to apply.
     */
    public void applyLayoutByName(PackageId packageId, String documentId, String layoutName) {

        try {
            String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.APPLY_LAYOUT_BY_NAME_PATH)
                    .replace("{packageId}", packageId.getId())
                    .replace("{documentId}", documentId)
                    .replace("{layoutName}", URLEncoder.encode(layoutName, "UTF-8"))
                    .build();

            getClient().post(path, "");
        } catch (UnsupportedEncodingException e) {
            throw new EslException("Layout name : '" + layoutName + "' can not be url encoded.", e);
        } catch (RequestException e) {
            throw new EslServerException("Could not apply layout.", e);
        } catch (Exception e) {
            throw new EslException("Could not apply layout." + " Exception: " + e.getMessage());
        }
    }

}
