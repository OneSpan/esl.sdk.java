package com.silanis.esl.api.model;
//

public interface IError {
    public IError setCode( Integer value);
    public Integer getCode();
    public IError setEntity( Entity value);
    public Entity getEntity();
    public IError setMessage( String value);
    public String getMessage();
    public IError setMessageKey( String value);
    public String getMessageKey();
    public IError setName( String value);
    public String getName();
    public IError setTechnical( String value);
    public String getTechnical();
    }