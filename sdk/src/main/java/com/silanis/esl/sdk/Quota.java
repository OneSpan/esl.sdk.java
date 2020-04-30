package com.silanis.esl.sdk;

public class Quota {

    private String cycle;
    private Integer limit = 0;
    private String scope = "SENDER";
    private String target = "SIGNER";

    public void setCycle(String value) { cycle = value; }

    public String getCycle(){
        return cycle;
    }

    public void setLimit(Integer value) { limit = value; }

    public Integer getLimit(){
        return limit;
    }

    public void setScope(String value) { scope = value; }

    public String getScope(){
        return scope;
    }

    public void setTarget(String value) { target = value; }

    public String getTarget(){
        return target;
    }
}