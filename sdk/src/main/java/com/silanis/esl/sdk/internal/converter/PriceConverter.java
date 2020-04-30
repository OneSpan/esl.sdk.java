package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Optional;
import com.silanis.esl.api.model.Currency;
import com.silanis.esl.api.model.Price;

public class PriceConverter {

    private Optional<Price> apiPriceOptional;
    private Optional<com.silanis.esl.sdk.Price> sdkPriceOptional;

    public PriceConverter(Price apiPrice ) {
        apiPriceOptional = Optional.of( apiPrice );
        sdkPriceOptional = Optional.absent();
    }

    public PriceConverter(com.silanis.esl.sdk.Price sdkPrice ) {
        apiPriceOptional = Optional.absent();
        sdkPriceOptional = Optional.of( sdkPrice );
    }

    public Price toAPIPrice() {
        if ( sdkPriceOptional.isPresent() ) {
            Price apiPrice = new Price();
            com.silanis.esl.sdk.Price sdkPrice = sdkPriceOptional.get();
            apiPrice.setAmount(sdkPrice.getAmount());
            if (sdkPrice.getCurrency() != null) {
                Currency apiCurrency = new Currency();
                apiCurrency.setId(sdkPrice.getCurrency().getId());
                apiCurrency.setName(sdkPrice.getCurrency().getName());
                apiCurrency.setData(sdkPrice.getCurrency().getData());
                apiPrice.setCurrency(apiCurrency);
            }
            return apiPrice;
        } else {
            return apiPriceOptional.get();
        }
    }

    public com.silanis.esl.sdk.Price toSDKPrice() {
        if ( apiPriceOptional.isPresent() ) {
            com.silanis.esl.sdk.Price sdkPrice = new com.silanis.esl.sdk.Price();
            Price apiPrice = apiPriceOptional.get();
            sdkPrice.setAmount(apiPrice.getAmount());
            if (apiPrice.getCurrency() != null){
                com.silanis.esl.sdk.Currency sdkCurrency = new com.silanis.esl.sdk.Currency();
                sdkCurrency.setId(apiPrice.getCurrency().getId());
                sdkCurrency.setName(apiPrice.getCurrency().getName());
                sdkCurrency.setData(apiPrice.getCurrency().getData());
                sdkPrice.setCurrency(sdkCurrency);
            }
            return sdkPrice;
        } else {
            return sdkPriceOptional.get();
        }
    }
}
