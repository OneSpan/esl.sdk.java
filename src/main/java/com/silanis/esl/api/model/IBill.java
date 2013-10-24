package com.silanis.esl.api.model;
//

public interface IBill {
    public IBill setAddress( Address value);
    public Address getAddress();
    public IBill setCreditCard( CreditCard value);
    public CreditCard getCreditCard();
    public IBill setPhone( String value);
    public String getPhone();
    public IBill setPlan( String value);
    public String getPlan();
    public IBill setSenderQuantity( Integer value);
    public Integer getSenderQuantity();
    }