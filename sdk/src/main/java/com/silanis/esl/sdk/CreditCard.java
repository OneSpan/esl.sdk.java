package com.silanis.esl.sdk;


public class CreditCard {

    private String cvv;
    private CcExpiration expiration;
    private String name;
    private String number;
    private String type;

    public void setCvv(String value){ cvv = value; }

    public String getCvv() { return cvv; }

    public void setExpiration(CcExpiration value) { expiration = value; }

    public CcExpiration getExpiration() { return expiration; }
    
    public void setName(String value){ name = value; }

    public String getName() { return name; }

    public void setNumber(String value) { number = value; }

    public String getNumber() { return number; }

    public void setType(String value) { type = value; }

    public String getType() { return type; }

}