package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Optional;
import com.silanis.esl.api.model.Transaction;

public class TransactionConverter {

    private Optional<Transaction> apiTransactionOptional;
    private Optional<com.silanis.esl.sdk.Transaction> sdkTransactionOptional;

    public TransactionConverter(Transaction apiTransaction ) {
        apiTransactionOptional = Optional.fromNullable( apiTransaction );
        sdkTransactionOptional = Optional.absent();
    }

    public TransactionConverter(com.silanis.esl.sdk.Transaction sdkTransaction ) {
        apiTransactionOptional = Optional.absent();
        sdkTransactionOptional = Optional.fromNullable( sdkTransaction );
    }

    public Transaction toAPITransaction() {
        if ( sdkTransactionOptional.isPresent() ) {
            Transaction apiTransaction = new Transaction();
            com.silanis.esl.sdk.Transaction sdkTransaction = sdkTransactionOptional.get();
            apiTransaction.setCreated(sdkTransaction.getCreated());
            if (sdkTransaction.getCreditCard() != null){
                apiTransaction.setCreditCard(new CreditCardConverter(sdkTransaction.getCreditCard()).toAPICreditCard());
            }
            if (sdkTransaction.getPrice() != null){
                apiTransaction.setPrice(new PriceConverter(sdkTransaction.getPrice()).toAPIPrice());
            }
            return apiTransaction;
        } else {
            return apiTransactionOptional.orNull();
        }
    }

    public com.silanis.esl.sdk.Transaction toSDKTransaction() {
        if ( apiTransactionOptional.isPresent() ) {
            com.silanis.esl.sdk.Transaction sdkTransaction = new com.silanis.esl.sdk.Transaction();
            Transaction apiTransaction = apiTransactionOptional.get();
            sdkTransaction.setCreated(apiTransaction.getCreated());
            if (apiTransaction.getCreditCard() != null){
                sdkTransaction.setCreditCard(new CreditCardConverter(apiTransaction.getCreditCard()).toSDKCreditCard());
            }
            if (apiTransaction.getPrice() != null){
                sdkTransaction.setPrice(new PriceConverter(apiTransaction.getPrice()).toSDKPrice());
            }
            return sdkTransaction;
        } else {
            return sdkTransactionOptional.orNull();
        }
    }
}
