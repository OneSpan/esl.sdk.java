package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.CcExpiration;
import com.silanis.esl.sdk.CreditCard;

public class CreditCardBuilder {
    private String cvv;
    private CcExpiration expiration;
    private String name;
    private String number;
    private String type;

    private CreditCardBuilder(){}

    public static CreditCardBuilder newCreditCard() { return new CreditCardBuilder(); }

    public CreditCardBuilder withCvv(String value) {
        cvv = value;
        return this;
    }

    public CreditCardBuilder withName(String value) {
        name = value;
        return this;
    }

    public CreditCardBuilder withType(String value) {
        type = value;
        return this;
    }

    public CreditCardBuilder withNumber(String value) {
        number = value;
        return this;
    }

    public CreditCardBuilder withExpiration(CcExpiration value) {
        expiration = value;
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
