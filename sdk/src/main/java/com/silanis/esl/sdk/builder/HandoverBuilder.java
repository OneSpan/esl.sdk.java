package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Handover;

import java.util.Locale;

/**
 * Created by schoi on 2020-04-01.
 */
public class HandoverBuilder {
    private Locale language;
    private String href;
    private String text;
    private String title;

    private HandoverBuilder(Locale language) {
        this.language = language;
    }

    public static HandoverBuilder newHandover(Locale language) {
        if (language == null) {
            throw new BuilderException("Language cannot be null.");
        }
        return new HandoverBuilder(language);
    }

    public HandoverBuilder withHref(String href) {
        this.href = href;
        return this;
    }

    public HandoverBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public HandoverBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public Handover build() {
        Handover handover = new Handover();
        handover.setLanguage(language);
        handover.setHref(href);
        handover.setText(text);
        handover.setTitle(title);
        return handover;
    }
}