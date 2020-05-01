package com.silanis.esl.sdk;


public class CreditCard {

    private String cvv;
    private CcExpiration expiration;
    private String name;
    private String number;
    private String type;

    public void setCvv(String cvv){
        this.cvv = cvv;
    }

    public String getCvv() {
        return cvv;
    }

    public void setExpiration(CcExpiration expiration) {
        this.expiration = expiration;
    }

    public CcExpiration getExpiration() {
        return expiration;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}