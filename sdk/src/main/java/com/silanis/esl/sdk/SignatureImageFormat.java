package com.silanis.esl.sdk;

import com.silanis.esl.sdk.internal.converter.EslEnumeration;

/**
 * Created by afazaeli on 16/10/15.
 */
public class SignatureImageFormat extends EslEnumeration {

    public static final SignatureImageFormat PNG = new SignatureImageFormat("image/png", "image/png", 0);
    public static final SignatureImageFormat JPG = new SignatureImageFormat("image/jpeg", "image/jpeg", 1);
    public static final SignatureImageFormat GIF = new SignatureImageFormat("image/gif", "image/gif", 2);

    protected SignatureImageFormat(String apiValue, String sdkValue, int index) {
        super(apiValue, sdkValue, index);
    }
}
