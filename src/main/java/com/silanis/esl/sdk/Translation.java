package com.silanis.esl.sdk;

public class Translation {
    private String name;
    private String language;
    private String description;

    public void setName( String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLanguage( String language ) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public com.silanis.esl.api.model.Translation toAPITranslation() {
        com.silanis.esl.api.model.Translation result = new com.silanis.esl.api.model.Translation();

        result.safeSetName( name );
        result.safeSetLanguage( language );
        result.safeSetDescription( description );

        return result;
    }
}
