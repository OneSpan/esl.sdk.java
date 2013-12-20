package com.silanis.esl.api.model;
//

public interface ITransaction {
    public ITransaction setCreated( java.util.Date value);
    public java.util.Date getCreated();
    public ITransaction setCreditCard( CreditCard value);
    public CreditCard getCreditCard();
    public ITransaction setPrice( Price value);
    public Price getPrice();
    }