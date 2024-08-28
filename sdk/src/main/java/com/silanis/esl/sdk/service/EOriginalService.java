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
public class EOriginalService extends EslComponent {

    public EOriginalService(RestClient restClient, String baseUrl) {
        super(restClient, baseUrl);
    }

    public void updateVaultingData( PackageId packageId, DocumentPackage sdkPackage ) throws EslException {
        String path = new UrlTemplate(getBaseUrl()).urlFor( UrlTemplate.E_ORIGINAL_VAULTING_DATA_PATH)
                .replace("{packageId}", packageId.getId())
                .build();

        Package aPackage = new DocumentPackageConverter(sdkPackage).toAPIPackage();

        String packageJson = Serialization.toJson( aPackage );
        try {
            getClient().put(path, packageJson);
        } catch (RequestException e) {
            throw new EslServerException("Could not update the package.", e);
        } catch (Exception e) {
            throw new EslException("Could not update the package.", e);
        }
    }

    public void getVaultingData( PackageId packageId) throws EslException {
        String path = new UrlTemplate(getBaseUrl()).urlFor( UrlTemplate.E_ORIGINAL_VAULTING_DATA_PATH)
                .replace("{packageId}", packageId.getId())
                .build();
        try {
            getClient().get(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not get the package.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the package.", e);
        }
    }

    public void revault( PackageId packageId) throws EslException {
        String path = new UrlTemplate(getBaseUrl()).urlFor( UrlTemplate.E_ORIGINAL_REVAULT_PATH)
                .replace("{packageId}", packageId.getId())
                .build();

        try {
            getClient().post(path, null);
        } catch (RequestException e) {
            throw new EslServerException("Could not revault the package.", e);
        } catch (Exception e) {
            throw new EslException("Could not revault the package.", e);
        }
    }
}
