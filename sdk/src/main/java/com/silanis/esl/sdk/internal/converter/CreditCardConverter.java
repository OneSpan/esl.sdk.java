package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Optional;
import com.silanis.esl.api.model.CcExpiration;
import com.silanis.esl.api.model.CreditCard;

public class CreditCardConverter {

    private Optional<CreditCard> apiCreditCardOptional;
    private Optional<com.silanis.esl.sdk.CreditCard> sdkCreditCardOptional;

    public CreditCardConverter(CreditCard apiCreditCard ) {
        apiCreditCardOptional = Optional.of( apiCreditCard );
        sdkCreditCardOptional = Optional.absent();
    }

    public CreditCardConverter(com.silanis.esl.sdk.CreditCard sdkCreditCard ) {
        apiCreditCardOptional = Optional.absent();
        sdkCreditCardOptional = Optional.of( sdkCreditCard );
    }

    public CreditCard toAPICreditCard() {
        if ( sdkCreditCardOptional.isPresent() ) {
            CreditCard apiCreditCard = new CreditCard();
            com.silanis.esl.sdk.CreditCard sdkCreditCard = sdkCreditCardOptional.get();
            apiCreditCard.setCvv(sdkCreditCard.getCvv());
            apiCreditCard.setName(sdkCreditCard.getName());
            apiCreditCard.setNumber(sdkCreditCard.getNumber());
            apiCreditCard.setType(sdkCreditCard.getType());
            if (sdkCreditCard.getExpiration() != null ) {
                CcExpiration apiCcExpiration = new CcExpiration();
                apiCcExpiration.setMonth(sdkCreditCard.getExpiration().getMonth());
                apiCcExpiration.setYear(sdkCreditCard.getExpiration().getYear());
                apiCreditCard.setExpiration(apiCcExpiration);
            }
            return apiCreditCard;
        } else {
            return apiCreditCardOptional.get();
        }
    }

    public com.silanis.esl.sdk.CreditCard toSDKCreditCard() {
        if ( apiCreditCardOptional.isPresent() ) {
            com.silanis.esl.sdk.CreditCard sdkCreditCard = new com.silanis.esl.sdk.CreditCard();
            CreditCard apiCreditCard = apiCreditCardOptional.get();
            sdkCreditCard.setCvv(apiCreditCard.getCvv());
            sdkCreditCard.setNumber(apiCreditCard.getNumber());
            sdkCreditCard.setName(apiCreditCard.getName());
            sdkCreditCard.setType(apiCreditCard.getType());
            if (apiCreditCard.getExpiration() != null) {
                com.silanis.esl.sdk.CcExpiration sdkCcExpiration = new com.silanis.esl.sdk.CcExpiration();
                sdkCcExpiration.setYear(apiCreditCard.getExpiration().getYear());
                sdkCcExpiration.setMonth(apiCreditCard.getExpiration().getMonth());
                sdkCreditCard.setExpiration(sdkCcExpiration);
            }
            return sdkCreditCard;
        } else {
            return sdkCreditCardOptional.get();
        }
    }
}
