package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Package;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.internal.converter.DocumentPackageConverter;

/**
 * The EOriginalService class provides methods to help update eOrignial Workflow data
 */
public class EOriginalService {

    private UrlTemplate template;
    private RestClient client;

    public EOriginalService(RestClient restClient, String baseUrl) {
        this.client = restClient;
        template = new UrlTemplate(baseUrl);
    }

    public void updateVaultingData( PackageId packageId, DocumentPackage sdkPackage ) throws EslException {
        String path = template.urlFor( UrlTemplate.E_ORIGINAL_VAULTING_DATA_PATH)
                .replace("{packageId}", packageId.getId())
                .build();

        Package aPackage = new DocumentPackageConverter(sdkPackage).toAPIPackage();

        String packageJson = Serialization.toJson( aPackage );
        try {
            client.put(path, packageJson);
        } catch (RequestException e) {
            throw new EslServerException("Could not update the package.", e);
        } catch (Exception e) {
            throw new EslException("Could not update the package.", e);
        }
    }

    public void getVaultingData( PackageId packageId) throws EslException {
        String path = template.urlFor( UrlTemplate.E_ORIGINAL_VAULTING_DATA_PATH)
                .replace("{packageId}", packageId.getId())
                .build();
        try {
            client.get(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not get the package.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the package.", e);
        }
    }

    public void revault( PackageId packageId) throws EslException {
        String path = template.urlFor( UrlTemplate.E_ORIGINAL_REVAULT_PATH)
                .replace("{packageId}", packageId.getId())
                .build();

        try {
            client.post(path, null);
        } catch (RequestException e) {
            throw new EslServerException("Could not update the package.", e);
        } catch (Exception e) {
            throw new EslException("Could not update the package.", e);
        }
    }
}
