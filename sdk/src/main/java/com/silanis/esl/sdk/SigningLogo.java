package com.silanis.esl.sdk;

import java.io.Serializable;
import java.util.Locale;

public class SigningLogo implements Serializable {
    private Locale language;
    private String image;

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
