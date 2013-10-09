package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
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