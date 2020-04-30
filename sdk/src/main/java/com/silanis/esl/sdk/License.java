package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.List;

public class License{

    private java.util.Date created;
    private java.util.Date paidUntil = null;
    private Plan plan;
    private String status = "ACTIVE";
    private List<Transaction> transactions = new ArrayList<Transaction>();


    public void setCreated(java.util.Date value) { created = value; }

    public java.util.Date getCreated(){
        return created;
    }

    public void setPaidUntil(java.util.Date value) { paidUntil = value; }

    public java.util.Date getPaidUntil(){
        return paidUntil;
    }
    
    public void setPlan(Plan value) { plan = value; }

    public Plan getPlan() { return plan; }

    public void setStatus(String value) { status = value; }

    public String getStatus() { return status; }

    public void setTransactions(List<Transaction> value) { transactions = value; }

    public List<Transaction> getTransactions() { return transactions; }

    public void addTransaction(Transaction value) {
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        transactions.add(value);
    }
}