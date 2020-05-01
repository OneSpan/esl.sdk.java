package com.silanis.esl.sdk;

public class CycleCount {

    private Integer count = 0;
    private String cycle = "DAY";
    
    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount(){
        return count;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getCycle(){
        return cycle;
    }
    
}