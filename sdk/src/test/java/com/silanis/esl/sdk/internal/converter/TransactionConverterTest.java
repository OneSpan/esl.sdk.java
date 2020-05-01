package com.silanis.esl.sdk.internal.converter;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class TransactionConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.Transaction sdkTransaction = null;
    private com.silanis.esl.api.model.Transaction apiTransaction = null;
    private TransactionConverter converter;

   
    private static final Date TRANS_CREATED = new Date();
    private static final String TRANS_CC_CVV = "transactioncreditCard_cvv";
    private static final String TRANS_CC_NAME = "transactioncreditCard_name";
    private static final String TRANS_CC_NUM = "transactioncreditCard_number";
    private static final String TRANS_CC_TYPE = "transactioncreditCard_type";
    private static final Integer TRANS_CC_EXP_MONTH = 12;
    private static final Integer TRANS_CC_EXP_YEAR = 12;
    private static final Integer TRANS_PRICE_AMOUNT = 1000;
    private static final String TRANS_PRICE_CURR_ID = "transaction_price_currency_id";
    private static final String TRANS_PRICE_CURR_NAME = "transaction_price_currency_name";
    private static final Map<String, Object> TRANS_PRICE_CURR_DATA = ImmutableMap.<String, Object>of("transaction_price_currency_data_0_key", "transaction_price_currency_data_0_value");
    

    @Test
    public void convertNullSDKToAPI() {
        sdkTransaction = null;
        converter = new TransactionConverter(sdkTransaction);

        assertThat(converter.toAPITransaction(), nullValue());
    }


    @Test
    public void convertNullAPIToSDK() {
        apiTransaction = null;
        converter = new TransactionConverter(apiTransaction);

        assertThat( converter.toSDKTransaction(), nullValue());
    }

    @Test
    public void convertNullSDKToSDK() {
        sdkTransaction = null;
        converter = new TransactionConverter(sdkTransaction);

        assertThat( converter.toSDKTransaction(), nullValue());
    }


    @Test
    public void convertNullAPIToAPI() {
        apiTransaction = null;
        converter = new TransactionConverter(apiTransaction);

        assertThat(converter.toAPITransaction(), nullValue());
    }

    @Test
    public void convertSDKToSDK() {
        sdkTransaction = createTypicalSDKTransaction();
        converter = new TransactionConverter( sdkTransaction );

        com.silanis.esl.sdk.Transaction transaction = converter.toSDKTransaction();

        assertThat(transaction, is( notNullValue() ) );
        assertThat(transaction, is( equalTo( transaction ) ) );
    }

    @Test
    public void convertAPIToAPI() {
        apiTransaction = createTypicalAPITransaction();
        converter = new TransactionConverter( apiTransaction );

        com.silanis.esl.api.model.Transaction transaction = converter.toAPITransaction();

        assertThat( transaction, is( notNullValue() ) );
        assertThat( transaction, is( equalTo( transaction ) ) );
    }

    @Test
    public void convertAPIToSDK() {
        apiTransaction = createTypicalAPITransaction();
        converter = new TransactionConverter( apiTransaction );

        com.silanis.esl.sdk.Transaction transaction = converter.toSDKTransaction();

        assertThat(transaction, is( notNullValue() ) );
        assertThat(transaction.getCreditCard().getNumber(), is(equalTo(TRANS_CC_NUM)));
        assertThat(transaction.getPrice().getAmount(), is(equalTo(TRANS_PRICE_AMOUNT)));
    }

    @Test
    public void convertSDKToAPI() {
        sdkTransaction = createTypicalSDKTransaction();
        converter = new TransactionConverter( sdkTransaction );
        com.silanis.esl.api.model.Transaction transaction = converter.toAPITransaction();

        assertThat(transaction, is( notNullValue() ) );
        assertThat(transaction.getCreditCard().getNumber(), is(equalTo(TRANS_CC_NUM)));
        assertThat(transaction.getPrice().getAmount(), is(equalTo(TRANS_PRICE_AMOUNT)));

    }

    private com.silanis.esl.sdk.Transaction createTypicalSDKTransaction() {

        com.silanis.esl.sdk.Transaction transaction = new com.silanis.esl.sdk.Transaction();
        transaction.setCreated(TRANS_CREATED);
        com.silanis.esl.sdk.CreditCard creditCard = new com.silanis.esl.sdk.CreditCard();
        creditCard.setCvv(TRANS_CC_CVV);
        creditCard.setType(TRANS_CC_TYPE);
        creditCard.setName(TRANS_CC_NAME);
        creditCard.setNumber(TRANS_CC_NUM);
        com.silanis.esl.sdk.CcExpiration ccExpiration = new com.silanis.esl.sdk.CcExpiration();
        ccExpiration.setMonth(TRANS_CC_EXP_MONTH);
        ccExpiration.setYear(TRANS_CC_EXP_YEAR);
        creditCard.setExpiration(ccExpiration);
        transaction.setCreditCard(creditCard);
        com.silanis.esl.sdk.Price price = new com.silanis.esl.sdk.Price();
        price.setAmount(TRANS_PRICE_AMOUNT);
        com.silanis.esl.sdk.Currency currency = new com.silanis.esl.sdk.Currency();
        currency.setData(TRANS_PRICE_CURR_DATA);
        currency.setName(TRANS_PRICE_CURR_NAME);
        currency.setId(TRANS_PRICE_CURR_ID);
        price.setCurrency(currency);
        transaction.setPrice(price);

        return transaction;
    }

    private com.silanis.esl.api.model.Transaction createTypicalAPITransaction() {
        com.silanis.esl.api.model.Transaction transaction = new com.silanis.esl.api.model.Transaction();
        transaction.setCreated(TRANS_CREATED);
        com.silanis.esl.api.model.CreditCard creditCard = new com.silanis.esl.api.model.CreditCard();
        creditCard.setCvv(TRANS_CC_CVV);
        creditCard.setType(TRANS_CC_TYPE);
        creditCard.setName(TRANS_CC_NAME);
        creditCard.setNumber(TRANS_CC_NUM);
        com.silanis.esl.api.model.CcExpiration ccExpiration = new com.silanis.esl.api.model.CcExpiration();
        ccExpiration.setMonth(TRANS_CC_EXP_MONTH);
        ccExpiration.setYear(TRANS_CC_EXP_YEAR);
        creditCard.setExpiration(ccExpiration);
        transaction.setCreditCard(creditCard);
        com.silanis.esl.api.model.Price price = new com.silanis.esl.api.model.Price();
        price.setAmount(TRANS_PRICE_AMOUNT);
        com.silanis.esl.api.model.Currency currency = new com.silanis.esl.api.model.Currency();
        currency.setData(TRANS_PRICE_CURR_DATA);
        currency.setName(TRANS_PRICE_CURR_NAME);
        currency.setId(TRANS_PRICE_CURR_ID);
        price.setCurrency(currency);
        transaction.setPrice(price);

        return transaction;
    }
}
