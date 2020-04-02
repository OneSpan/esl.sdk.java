package com.silanis.esl.sdk;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by schoi on 2020-04-01.
 */
public class Handover implements Serializable {
    private Locale language;
    private String href;
    private String text;
    private String title;

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}