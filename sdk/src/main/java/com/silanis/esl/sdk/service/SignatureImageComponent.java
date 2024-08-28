package com.silanis.esl.sdk.service;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.SignatureImageFormat;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.io.DownloadedFile;

/**
 * Created by schoi on 10/16/15.
 */
public class SignatureImageComponent extends EslComponent {

    public SignatureImageComponent(RestClient client, String baseUrl ) {
        super(client, baseUrl);
    }

    public DownloadedFile getSignatureImageForSender(String senderId, SignatureImageFormat imageFormat) {
        String path = new UrlTemplate(getBaseUrl()).urlFor( UrlTemplate.SIGNATURE_IMAGE_FOR_SENDER_PATH)
                              .replace("{senderId}", senderId)
                              .build();
        try {
            return getClient().getBytes(path, imageFormat.getAcceptType());
        } catch (RequestException e){
            throw new EslServerException( "Could not download signature image for sender.", e);
        } catch (Exception e) {
            throw new EslException("Could not download signature image for sender." + " Exception: " + e.getMessage());
        }
    }

    public DownloadedFile getSignatureImageForPackageRole(PackageId packageId, String signerId, SignatureImageFormat imageFormat) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.SIGNATURE_IMAGE_FOR_PACKAGE_ROLE_PATH)
                              .replace("{packageId}", packageId.getId())
                              .replace("{roleId}", signerId)
                              .build();
        try {
            return getClient().getBytes(path, imageFormat.getAcceptType());
        } catch (RequestException e){
            throw new EslServerException( "Could not download signature image for package signer.", e);
        } catch (Exception e) {
            throw new EslException("Could not download signature image for package signer." + " Exception: " + e.getMessage());
        }
    }
}
