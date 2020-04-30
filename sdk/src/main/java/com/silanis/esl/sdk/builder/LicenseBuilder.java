package com.silanis.esl.sdk.builder;


import com.silanis.esl.sdk.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class LicenseBuilder {
    private Date created;
    private Date paidUntil;
    private Plan plan;
    private String status;
    private List<Transaction> transactions;

    private LicenseBuilder() {
        transactions = new ArrayList<Transaction>();
    }

    public static LicenseBuilder newLicense() {
        return new LicenseBuilder();
    }

    public LicenseBuilder withCreated(Date value) {
        created = value;
        return this;
    }

    public LicenseBuilder withPaidUntil(Date value ) {
        paidUntil = value;
        return this;
    }

    public LicenseBuilder withStatus(String value) {
        status = value;
        return this;
    }

    public LicenseBuilder withPlan(Plan value) {
        plan = value;
        return this;
    }

    public LicenseBuilder withTransactions(List<Transaction> value) {
        transactions = value;
        return this;
    }

    public LicenseBuilder withTransaction(Transaction value) {
        transactions.add(value);
        return this;
    }

    public LicenseBuilder withTransaction(Date created, CreditCard creditCard, Price price)
    {
        Transaction transaction = new Transaction();
        transaction.setCreated(created);
        transaction.setCreditCard(creditCard);
        transaction.setPrice(price);
        transactions.add(transaction);
        return this;
    }

    public License build() {
        License license = new License();
        license.setCreated(created);
        license.setPaidUntil(paidUntil);
        license.setStatus(status);
        license.setPlan(plan);
        license.setTransactions(transactions);
        return license;
    }
}
