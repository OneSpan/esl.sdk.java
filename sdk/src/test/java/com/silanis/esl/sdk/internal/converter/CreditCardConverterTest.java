package com.silanis.esl.sdk.internal.converter;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class CreditCardConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.CreditCard sdkCreditCard = null;
    private com.silanis.esl.api.model.CreditCard apiCreditCard = null;
    private CreditCardConverter converter;
    
    private static final String CC_CVV = "creditCard_cvv";
    private static final String CC_NAME = "creditCard_name";
    private static final String CC_NUM = "creditCard_number";
    private static final String CC_TYPE = "creditCard_type";
    private static final Integer CC_EXP_MONTH = 12;
    private static final Integer CC_EXP_YEAR = 12;


    @Test
    public void convertNullSDKToAPI() {
        sdkCreditCard = null;
        converter = new CreditCardConverter(sdkCreditCard);

        assertThat(converter.toAPICreditCard(), nullValue());
    }


    @Test
    public void convertNullAPIToSDK() {
        apiCreditCard = null;
        converter = new CreditCardConverter(apiCreditCard);

        assertThat( converter.toSDKCreditCard(), nullValue());
    }

    @Test
    public void convertNullSDKToSDK() {
        sdkCreditCard = null;
        converter = new CreditCardConverter(sdkCreditCard);

        assertThat( converter.toSDKCreditCard(), nullValue());
    }


    @Test
    public void convertNullAPIToAPI() {
        apiCreditCard = null;
        converter = new CreditCardConverter(apiCreditCard);

        assertThat(converter.toAPICreditCard(), nullValue());
    }

    @Test
    public void convertSDKToSDK() {
        sdkCreditCard = createTypicalSDKCreditCard();
        converter = new CreditCardConverter( sdkCreditCard );

        com.silanis.esl.sdk.CreditCard creditCard = converter.toSDKCreditCard();

        assertThat(creditCard, is( notNullValue() ) );
        assertThat(creditCard, is( equalTo( creditCard ) ) );
    }

    @Test
    public void convertAPIToAPI() {
        apiCreditCard = createTypicalAPICreditCard();
        converter = new CreditCardConverter( apiCreditCard );

        com.silanis.esl.api.model.CreditCard creditCard = converter.toAPICreditCard();

        assertThat( creditCard, is( notNullValue() ) );
        assertThat( creditCard, is( equalTo( creditCard ) ) );
    }

    @Test
    public void convertAPIToSDK() {
        apiCreditCard = createTypicalAPICreditCard();
        converter = new CreditCardConverter( apiCreditCard );

        com.silanis.esl.sdk.CreditCard creditCard = converter.toSDKCreditCard();

        assertThat(creditCard, is( notNullValue() ) );
        assertThat(creditCard.getNumber(), is(equalTo(CC_NUM)));
        assertThat(creditCard.getCvv(), is(equalTo(CC_CVV)));
        assertThat(creditCard.getName(), is(equalTo(CC_NAME)));
        assertThat(creditCard.getExpiration().getMonth(), is(equalTo(CC_EXP_MONTH)));

    }

    @Test
    public void convertSDKToAPI() {
        sdkCreditCard = createTypicalSDKCreditCard();
        converter = new CreditCardConverter( sdkCreditCard );
        com.silanis.esl.api.model.CreditCard creditCard = converter.toAPICreditCard();

        assertThat(creditCard, is( notNullValue() ) );
        assertThat(creditCard, is( notNullValue() ) );
        assertThat(creditCard.getNumber(), is(equalTo(CC_NUM)));
        assertThat(creditCard.getCvv(), is(equalTo(CC_CVV)));
        assertThat(creditCard.getName(), is(equalTo(CC_NAME)));
        assertThat(creditCard.getExpiration().getMonth(), is(equalTo(CC_EXP_MONTH)));
    }

    private com.silanis.esl.sdk.CreditCard createTypicalSDKCreditCard() {

        com.silanis.esl.sdk.CreditCard creditCard = new com.silanis.esl.sdk.CreditCard();
        creditCard.setCvv(CC_CVV);
        creditCard.setType(CC_TYPE);
        creditCard.setName(CC_NAME);
        creditCard.setNumber(CC_NUM);
        com.silanis.esl.sdk.CcExpiration ccExpiration = new com.silanis.esl.sdk.CcExpiration();
        ccExpiration.setMonth(CC_EXP_MONTH);
        ccExpiration.setYear(CC_EXP_YEAR);
        creditCard.setExpiration(ccExpiration);

        return creditCard;
    }

    private com.silanis.esl.api.model.CreditCard createTypicalAPICreditCard() {
        com.silanis.esl.api.model.CreditCard creditCard = new com.silanis.esl.api.model.CreditCard();

        creditCard.setCvv(CC_CVV);
        creditCard.setType(CC_TYPE);
        creditCard.setName(CC_NAME);
        creditCard.setNumber(CC_NUM);
        com.silanis.esl.api.model.CcExpiration ccExpiration = new com.silanis.esl.api.model.CcExpiration();
        ccExpiration.setMonth(CC_EXP_MONTH);
        ccExpiration.setYear(CC_EXP_YEAR);
        creditCard.setExpiration(ccExpiration);

        return creditCard;
    }
}
