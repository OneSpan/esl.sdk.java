package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.CcExpiration;
import com.silanis.esl.sdk.CreditCard;

public class CreditCardBuilder {
    private String cvv;
    private CcExpiration expiration;
    private String name;
    private String number;
    private String type;

    private CreditCardBuilder() {
    }

    public static CreditCardBuilder newCreditCard() {
        return new CreditCardBuilder();
    }

    public CreditCardBuilder withCvv(String cvv) {
        this.cvv = cvv;
        return this;
    }

    public CreditCardBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CreditCardBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public CreditCardBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public CreditCardBuilder withExpiration(CcExpiration expiration) {
        this.expiration = expiration;
        return this;
    }

    public CreditCardBuilder withExpiration(Integer month, Integer year) {
        expiration = new CcExpiration();
        expiration.setMonth(month);
        expiration.setYear(year);
        return this;
    }

    public CreditCard build() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCvv(cvv);
        creditCard.setName(name);
        creditCard.setNumber(number);
        creditCard.setType(type);
        creditCard.setExpiration(expiration);
        return creditCard;
    }
}
