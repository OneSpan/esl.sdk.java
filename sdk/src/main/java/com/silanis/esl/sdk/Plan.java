package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Plan {

    private String contract;
    private String cycle;
    private String description;
    private Map<String, Object> features;
    private CycleCount freeCycles;
    private String group;
    private String original;
    private Price price;
    private List<Quota> quotas = new ArrayList<Quota>();
    private Map<String, Object> data;
    private String id;
    private String name;


    public void setContract(String contract){
        this.contract = contract;
    }

    public String getContract(){
        return contract;
    }

    public void setCycle(String cycle){
        this.cycle = cycle;
    }

    public String getCycle(){
        return cycle;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setFeatures(Map<String, Object> features){
        this.features = features;
    }

    public Map<String, Object> getFeatures(){
        return features;
    }

    public void setFreeCycles(CycleCount cycles){
        freeCycles = cycles;
    }

    public CycleCount getFreeCycles(){
        return freeCycles;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getOriginal() {
        return original;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Price getPrice(){
        return price;
    }
    
    public void setQuotas(List<Quota> quotas) {
        this.quotas = quotas;
    }

    public List<Quota> getQuotas(){
        return quotas;
    }

    public void addQuota(Quota quota){
        if (quota == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        quotas.add(quota);
    }
}