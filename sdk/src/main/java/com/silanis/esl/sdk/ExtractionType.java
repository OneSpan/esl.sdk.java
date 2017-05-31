package com.silanis.esl.sdk;

/**
 * Created by schoi on 23/05/17.
 */
public enum ExtractionType {
    FORM_FIELDS_ONLY(""),
    TEXT_TAGS_ONLY("1"),
    FORM_FIELDS_WITH_TEXT_TAGS("2");

    private final String value;

    ExtractionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
