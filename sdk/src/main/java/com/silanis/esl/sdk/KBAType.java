package com.silanis.esl.sdk;

/**
 * Created by schoi on 9/5/14.
 */
public enum KBAType {

    EQUIFAX_USA, EQUIFAX_CANADA;

    public static KBAType getTypeFromKBA(KnowledgeBasedAuthentication knowledgeBasedAuthentication){
        if(knowledgeBasedAuthentication.isEquifaxUSA()) return EQUIFAX_USA;
        if(knowledgeBasedAuthentication.isEquifaxCanada()) return EQUIFAX_CANADA;
        throw new EslException("error.validation.kba.kbaProviderNotSelected");
    }
}
