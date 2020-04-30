package com.silanis.esl.sdk;

public class Price{

    private Integer amount = 0;
    private Currency currency;

    public void setAmount(Integer value){ amount = value; }

    public Integer getAmount(){
        return amount;
    }

    public void setCurrency(Currency value){ currency = value; }

    public Currency getCurrency(){
        return currency;
    }
}