package com.silanis.esl.api.model;
//

public interface ISamlMetadata {
    public ISamlMetadata setAccountUid( String value);
    public String getAccountUid();
    public ISamlMetadata setActive( Boolean value);
    public Boolean getActive();
    public ISamlMetadata setEntityId( String value);
    public String getEntityId();
    public ISamlMetadata setMetadata( String value);
    public String getMetadata();
    public ISamlMetadata setPublicKey( String value);
    public String getPublicKey();
    public ISamlMetadata setUid( String value);
    public String getUid();
    public ISamlMetadata setUrl( String value);
    public String getUrl();
    }