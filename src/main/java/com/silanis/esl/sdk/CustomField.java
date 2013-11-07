package com.silanis.esl.sdk;

import java.util.List;

public class CustomField {
    private String id;
    private String value;
    private Boolean required;
    private List<Translation> translations;

    public void setId( String id ) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setValue( String value ) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setRequired( Boolean required ) {
        this.required = required;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setTranslations( List<Translation> translations ) {
        this.translations = translations;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public com.silanis.esl.api.model.CustomField toAPICustomField() {
        com.silanis.esl.api.model.CustomField result = new com.silanis.esl.api.model.CustomField();

        result.setId( id );
        result.setValue( value );

        for ( Translation translation : translations ) {
            result.addTranslation( translation.toAPITranslation() );
        }

        return result;
    }
}
