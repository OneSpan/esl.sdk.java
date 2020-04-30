package com.silanis.esl.sdk;

public class CycleCount {

    private Integer count = 0;
    private String cycle = "DAY";
    
    public void setCount(Integer value) { count = value; }

    public Integer getCount(){
        return count;
    }

    public void setCycle(String value) { cycle = value; }

    public String getCycle(){
        return cycle;
    }
    
}