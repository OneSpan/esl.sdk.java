package com.silanis.esl.sdk;

public class Quota {

    private String cycle;
    private Integer limit = 0;
    private String scope = "SENDER";
    private String target = "SIGNER";

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getCycle(){
        return cycle;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit(){
        return limit;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getScope(){
        return scope;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }
}