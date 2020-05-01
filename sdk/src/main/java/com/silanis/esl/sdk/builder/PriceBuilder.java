package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Currency;
import com.silanis.esl.sdk.Price;

import java.util.Map;

public class PriceBuilder {
    private Integer amount = 0;
    private Currency currency;

    private PriceBuilder() {
    }

    public static PriceBuilder newPrice() {
        return new PriceBuilder();
    }

    public PriceBuilder withAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public PriceBuilder withCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public PriceBuilder withCurrency(String id, String name, Map<String, Object> data) {
        currency = new Currency();
        currency.setId(id);
        currency.setName(name);
        currency.setData(data);
        return this;
    }

    public Price build() {
        Price price = new Price();
        price.setAmount(amount);
        price.setCurrency(currency);
        return price;
    }
}
