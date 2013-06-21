package com.silanis.esl.sdk;

import com.silanis.awsng.web.rest.model.Auth;
import com.silanis.awsng.web.rest.model.AuthChallenge;
import com.silanis.awsng.web.rest.model.AuthScheme;

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

    Auth toAPIAuthentication() {
        Auth auth = new Auth().setScheme(scheme());

        for (Challenge challenge : challenges) {
            auth.addChallenge(new AuthChallenge().setQuestion(challenge.getQuestion()).setAnswer(challenge.getAnswer()));
        }

        if (phoneNumber != null) {
            auth.addChallenge(new AuthChallenge().setQuestion(phoneNumber));
        }

        return auth;
    }

    private AuthScheme scheme() {
        switch (method) {
            case EMAIL:
                return AuthScheme.NONE;
            case CHALLENGE:
                return AuthScheme.CHALLENGE;
            case SMS:
                return AuthScheme.SMS;
        }

        throw new IllegalStateException("Unknown authentication method");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}