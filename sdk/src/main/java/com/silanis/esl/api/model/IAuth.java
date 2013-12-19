package com.silanis.esl.api.model;
//
import java.util.List;

public interface IAuth {
    public IAuth setChallenges( List<AuthChallenge> value);
    public List<AuthChallenge> getChallenges();
    public IAuth setScheme( AuthScheme value);
    public AuthScheme getScheme();
    }