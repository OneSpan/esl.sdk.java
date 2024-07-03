package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.List;

public class Authentication {

    private final AuthenticationMethod method;
    private List<Challenge> challenges = new ArrayList<Challenge>();
    private String phoneNumber;
    private IdvWorkflow idvWorkflow;

    public Authentication(AuthenticationMethod method) {
        this.method = method;
    }

    public Authentication(List<Challenge> challenges) {
        this(AuthenticationMethod.CHALLENGE);
        this.challenges.addAll(challenges);
    }

    public Authentication(AuthenticationMethod method, List<Challenge> challenges) {
        this(method);
        this.challenges.addAll(challenges);
    }

    public Authentication(AuthenticationMethod method, String phoneNumber) {
        this(method);
        this.phoneNumber = phoneNumber;
    }

    public Authentication(AuthenticationMethod method, String phoneNumber, IdvWorkflow idvWorkflow) {
        this(method);
        this.phoneNumber = phoneNumber;
        this.idvWorkflow = idvWorkflow;
    }

    public AuthenticationMethod getMethod() {
        return method;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public IdvWorkflow getIdvWorkflow() {
        return idvWorkflow;
    }
}