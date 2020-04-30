package com.silanis.esl.sdk;

public class Transaction{

    private java.util.Date created;
    private CreditCard creditCard;
    private Price price;

    public void setCreated(java.util.Date value){ created = value; }

    public java.util.Date getCreated(){
        return created;
    }
    
    public void setCreditCard(CreditCard value){ creditCard = value; }

    public CreditCard getCreditCard(){
        return creditCard;
    }

    public void setPrice(Price value){ price = value; }

    public Price getPrice(){
        return price;
    }
}