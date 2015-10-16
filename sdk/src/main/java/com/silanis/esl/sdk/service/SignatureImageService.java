package com.silanis.esl.sdk.service;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.io.DownloadedFile;

/**
 * Created by schoi on 10/16/15.
 */
public class SignatureImageService {
    private final UrlTemplate template;
    private final RestClient client;
    private final String acceptType = "image/jpeg";

    public SignatureImageService( RestClient client, String baseUrl ) {
        template = new UrlTemplate( baseUrl );
        this.client = client;
    }

    public DownloadedFile getSignatureImageForSender(String senderId) {
        String path = template.urlFor( UrlTemplate.SIGNATURE_IMAGE_FOR_SENDER_PATH)
                              .replace("{senderId}", senderId)
                              .build();
        try {
            return client.getBytes(path, acceptType);
        } catch (RequestException e){
            throw new EslServerException( "Could not download signature image for sender.", e);
        } catch (Exception e) {
            throw new EslException("Could not download signature image for sender." + " Exception: " + e.getMessage());
        }
    }

    public DownloadedFile getSignatureImageForPackageRole(PackageId packageId, String signerId) {
        String path = template.urlFor(UrlTemplate.SIGNATURE_IMAGE_FOR_PACKAGE_ROLE_PATH)
                              .replace("{packageId}", packageId.getId())
                              .replace("{roleId}", signerId)
                              .build();
        try {
            return client.getBytes(path, acceptType);
        } catch (RequestException e){
            throw new EslServerException( "Could not download signature image for package signer.", e);
        } catch (Exception e) {
            throw new EslException("Could not download signature image for package signer." + " Exception: " + e.getMessage());
        }
    }
}
