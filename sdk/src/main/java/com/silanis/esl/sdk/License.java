package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.List;

public class License{

    private java.util.Date created;
    private java.util.Date paidUntil = null;
    private Plan plan;
    private String status = "ACTIVE";
    private List<Transaction> transactions = new ArrayList<Transaction>();


    public void setCreated(java.util.Date created) {
        this.created = created;
    }

    public java.util.Date getCreated(){
        return created;
    }

    public void setPaidUntil(java.util.Date paidUntil) {
        this.paidUntil = paidUntil;
    }

    public java.util.Date getPaidUntil(){
        return paidUntil;
    }
    
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        if (transaction == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        transactions.add(transaction);
    }
}