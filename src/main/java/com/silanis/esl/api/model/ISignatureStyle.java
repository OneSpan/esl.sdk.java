package com.silanis.esl.api.model;
//

public interface ISignatureStyle {
    public ISignatureStyle setHanddrawn( String value);
    public String getHanddrawn();
    public ISignatureStyle setTextual( TextualSignatureStyle value);
    public TextualSignatureStyle getTextual();
    }