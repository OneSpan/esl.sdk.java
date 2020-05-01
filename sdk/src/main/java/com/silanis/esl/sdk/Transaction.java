package com.silanis.esl.sdk;

public class Transaction{

    private java.util.Date created;
    private CreditCard creditCard;
    private Price price;

    public void setCreated(java.util.Date created){
        this.created = created;
    }

    public java.util.Date getCreated(){
        return created;
    }
    
    public void setCreditCard(CreditCard creditCard){
        this.creditCard = creditCard;
    }

    public CreditCard getCreditCard(){
        return creditCard;
    }

    public void setPrice(Price price){
        this.price = price;
    }

    public Price getPrice(){
        return price;
    }
}