package com.silanis.esl.sdk;

import com.silanis.esl.api.model.Auth;
import com.silanis.esl.api.model.AuthChallenge;
import com.silanis.esl.api.model.AuthScheme;

import java.util.ArrayList;
import java.util.List;

public class Authentication {

    private final AuthenticationMethod method;
    private List<Challenge> challenges = new ArrayList<Challenge>();
    private String phoneNumber;

    public Authentication(AuthenticationMethod method) {
        this.method = method;
    }

    public Authentication(List<Challenge> challenges) {
        this(AuthenticationMethod.CHALLENGE);
        this.challenges.addAll(challenges);
    }

    public Authentication(String phoneNumber) {
        this(AuthenticationMethod.SMS);
        this.phoneNumber = phoneNumber;
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
}