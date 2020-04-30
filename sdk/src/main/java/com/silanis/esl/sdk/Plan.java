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


    public void setContract(String value){ contract = value; }

    public String getContract(){
        return contract;
    }

    public void setCycle(String value){ cycle = value; }

    public String getCycle(){
        return cycle;
    }

    public void setData(Map<String, Object> value) { data = value; }

    public Map<String, Object> getData() { return data; }

    public void setId(String value) { id = value; }

    public String getId() { return id; }

    public void setName(String value) { name = value; }

    public String getName() { return name; }

    public void setDescription(String value){ description = value; }

    public String getDescription(){
        return description;
    }

    public void setFeatures(Map<String, Object> value){ features = value; }

    public Map<String, Object> getFeatures(){
        return features;
    }

    public void setFreeCycles(CycleCount value){ freeCycles = value; }

    public CycleCount getFreeCycles(){
        return freeCycles;
    }

    public void setGroup(String value) { group = value; }

    public String getGroup(){
        return group;
    }

    public void setOriginal(String value) { original = value; }

    public String getOriginal(){
        return original;
    }

    public void setPrice(Price value){ price = value; }

    public Price getPrice(){
        return price;
    }
    
    public void setQuotas(List<Quota> value) { quotas = value; }

    public List<Quota> getQuotas(){
        return quotas;
    }

    public void addQuota(Quota value){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        quotas.add(value);
    }
}