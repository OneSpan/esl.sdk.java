package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IAuth {
    public IAuth setChallenges( List<AuthChallenge> value);
    public List<AuthChallenge> getChallenges();
    public IAuth setScheme( AuthScheme value);
    public AuthScheme getScheme();
    }