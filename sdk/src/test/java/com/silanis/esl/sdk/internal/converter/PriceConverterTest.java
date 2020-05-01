package com.silanis.esl.sdk.internal.converter;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class PriceConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.Price sdkPrice = null;
    private com.silanis.esl.api.model.Price apiPrice = null;
    private PriceConverter converter;
    
    private static final Integer PRICE_AMOUNT = 1000;
    private static final String PRICE_CURR_ID = "price_currency_id";
    private static final String PRICE_CURR_NAME = "price_currency_name";
    private static final Map<String, Object> PRICE_CURR_DATA = ImmutableMap.<String, Object>of("price_currency_data_0_key", "price_currency_data_0_value");


    @Test
    public void convertNullSDKToAPI() {
        sdkPrice = null;
        converter = new PriceConverter(sdkPrice);

        assertThat(converter.toAPIPrice(), nullValue());
    }


    @Test
    public void convertNullAPIToSDK() {
        apiPrice = null;
        converter = new PriceConverter(apiPrice);

        assertThat( converter.toSDKPrice(), nullValue());
    }

    @Test
    public void convertNullSDKToSDK() {
        sdkPrice = null;
        converter = new PriceConverter(sdkPrice);

        assertThat( converter.toSDKPrice(), nullValue());
    }


    @Test
    public void convertNullAPIToAPI() {
        apiPrice = null;
        converter = new PriceConverter(apiPrice);

        assertThat(converter.toAPIPrice(), nullValue());
    }

    @Test
    public void convertSDKToSDK() {
        sdkPrice = createTypicalSDKPrice();
        converter = new PriceConverter( sdkPrice );

        com.silanis.esl.sdk.Price price = converter.toSDKPrice();

        assertThat(price, is( notNullValue() ) );
        assertThat(price, is( equalTo( price ) ) );
    }

    @Test
    public void convertAPIToAPI() {
        apiPrice = createTypicalAPIPrice();
        converter = new PriceConverter( apiPrice );

        com.silanis.esl.api.model.Price price = converter.toAPIPrice();

        assertThat( price, is( notNullValue() ) );
        assertThat( price, is( equalTo( price ) ) );
    }

    @Test
    public void convertAPIToSDK() {
        apiPrice = createTypicalAPIPrice();
        converter = new PriceConverter( apiPrice );

        com.silanis.esl.sdk.Price price = converter.toSDKPrice();

        assertThat(price, is( notNullValue() ) );
        assertThat(price.getAmount(), is(equalTo(PRICE_AMOUNT)));
    }

    @Test
    public void convertSDKToAPI() {
        sdkPrice = createTypicalSDKPrice();
        converter = new PriceConverter( sdkPrice );
        com.silanis.esl.api.model.Price price = converter.toAPIPrice();

        assertThat(price, is( notNullValue() ) );
        assertThat(price.getAmount(), is(equalTo(PRICE_AMOUNT)));
    }

    private com.silanis.esl.sdk.Price createTypicalSDKPrice() {
        com.silanis.esl.sdk.Price price = new com.silanis.esl.sdk.Price();

        price.setAmount(PRICE_AMOUNT);
        com.silanis.esl.sdk.Currency currency = new com.silanis.esl.sdk.Currency();
        currency.setData(PRICE_CURR_DATA);
        currency.setName(PRICE_CURR_NAME);
        currency.setId(PRICE_CURR_ID);
        price.setCurrency(currency);

        return price;
    }

    private com.silanis.esl.api.model.Price createTypicalAPIPrice() {
        com.silanis.esl.api.model.Price price = new com.silanis.esl.api.model.Price();
        price.setAmount(PRICE_AMOUNT);
        com.silanis.esl.api.model.Currency currency = new com.silanis.esl.api.model.Currency();
        currency.setData(PRICE_CURR_DATA);
        currency.setName(PRICE_CURR_NAME);
        currency.setId(PRICE_CURR_ID);
        price.setCurrency(currency);

        return price;
    }
}
