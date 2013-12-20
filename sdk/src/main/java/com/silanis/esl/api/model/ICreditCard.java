package com.silanis.esl.api.model;
//

public interface ICreditCard {
    public ICreditCard setCvv( String value);
    public String getCvv();
    public ICreditCard setExpiration( CcExpiration value);
    public CcExpiration getExpiration();
    public ICreditCard setName( String value);
    public String getName();
    public ICreditCard setNumber( String value);
    public String getNumber();
    public ICreditCard setType( String value);
    public String getType();
    }