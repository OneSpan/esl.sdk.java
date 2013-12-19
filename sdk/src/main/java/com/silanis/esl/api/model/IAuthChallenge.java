package com.silanis.esl.api.model;
//

public interface IAuthChallenge {
    public IAuthChallenge setAnswer( String value);
    public String getAnswer();
    public IAuthChallenge setMaskInput( Boolean value);
    public Boolean getMaskInput();
    public IAuthChallenge setQuestion( String value);
    public String getQuestion();
    }