package com.silanis.esl.api.model;
//
import java.util.List;

public interface IGuestAuth extends IAuth{
    public IGuestAuth setChallenges( List<AuthChallenge> value);
    public List<AuthChallenge> getChallenges();
    public IGuestAuth setLoginToken( String value);
    public String getLoginToken();
    public IGuestAuth setPackage( Package value);
    public Package getPackage();
    public IGuestAuth setScheme( AuthScheme value);
    public AuthScheme getScheme();
    public IGuestAuth setUser( User value);
    public User getUser();
    }