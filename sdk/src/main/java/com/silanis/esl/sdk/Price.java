package com.silanis.esl.sdk;

public class Price{

    private Integer amount = 0;
    private Currency currency;

    public void setAmount(Integer amount){
        this.amount = amount;
    }

    public Integer getAmount(){
        return amount;
    }

    public void setCurrency(Currency currency){
        this.currency = currency;
    }

    public Currency getCurrency(){
        return currency;
    }
}