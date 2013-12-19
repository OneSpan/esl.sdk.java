package com.silanis.esl.api.model;
//

public interface IAddress {
    public IAddress setAddress1( String value);
    public String getAddress1();
    public IAddress setAddress2( String value);
    public String getAddress2();
    public IAddress setCity( String value);
    public String getCity();
    public IAddress setCountry( String value);
    public String getCountry();
    public IAddress setState( String value);
    public String getState();
    public IAddress setZipcode( String value);
    public String getZipcode();
    }