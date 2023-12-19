package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

@JsonIgnoreProperties(ignoreUnknown=true)
public class KnowledgeBasedAuthentication extends Model
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_KNOWLEDGEBASEDAUTHENTICATIONSTATUS = "knowledgeBasedAuthenticationStatus";
    @JsonIgnore
    public static final String FIELD_SIGNERINFORMATIONFOREQUIFAXCANADA = "signerInformationForEquifaxCanada";
    @JsonIgnore
    public static final String FIELD_SIGNERINFORMATIONFOREQUIFAXUSA = "signerInformationForEquifaxUSA";
    @JsonIgnore
    public static final String FIELD_SIGNERINFORMATIONFORLEXISNEXIS = "signerInformationForLexisNexis";
    
    // Empty Constructor
    public KnowledgeBasedAuthentication ( ) {}
    
    // Fields
    protected String _knowledgeBasedAuthenticationStatus = "NOT_YET_ATTEMPTED";
    protected SignerInformationForEquifaxCanada _signerInformationForEquifaxCanada = null;
    protected SignerInformationForEquifaxUSA _signerInformationForEquifaxUSA = null;

    protected SignerInformationForLexisNexis _signerInformationForLexisNexis = null;


    // Accessors
        
    
    public KnowledgeBasedAuthentication setKnowledgeBasedAuthenticationStatus( String value ){
        SchemaSanitizer.throwOnNull(FIELD_KNOWLEDGEBASEDAUTHENTICATIONSTATUS,value);
        // TODO With proper compare
        // if ( this._knowledgeBasedAuthenticationStatus == value ) return this;
        this._knowledgeBasedAuthenticationStatus = value;
        setDirty(FIELD_KNOWLEDGEBASEDAUTHENTICATIONSTATUS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public KnowledgeBasedAuthentication safeSetKnowledgeBasedAuthenticationStatus( String value ){
        if ( value != null ) { this.setKnowledgeBasedAuthenticationStatus( value ); }
        return this;
    }
    public String getKnowledgeBasedAuthenticationStatus(){
        return _knowledgeBasedAuthenticationStatus;
    }
    
        
    
    public KnowledgeBasedAuthentication setSignerInformationForEquifaxCanada( SignerInformationForEquifaxCanada value ){
        // TODO With proper compare
        // if ( this._signerInformationForEquifaxCanada == value ) return this;
        this._signerInformationForEquifaxCanada = value;
        setDirty(FIELD_SIGNERINFORMATIONFOREQUIFAXCANADA);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public KnowledgeBasedAuthentication safeSetSignerInformationForEquifaxCanada( SignerInformationForEquifaxCanada value ){
        if ( value != null ) { this.setSignerInformationForEquifaxCanada( value ); }
        return this;
    }
    public SignerInformationForEquifaxCanada getSignerInformationForEquifaxCanada(){
        return _signerInformationForEquifaxCanada;
    }
    
        
    
    public KnowledgeBasedAuthentication setSignerInformationForEquifaxUSA( SignerInformationForEquifaxUSA value ){
        // TODO With proper compare
        // if ( this._signerInformationForEquifaxUSA == value ) return this;
        this._signerInformationForEquifaxUSA = value;
        setDirty(FIELD_SIGNERINFORMATIONFOREQUIFAXUSA);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public KnowledgeBasedAuthentication safeSetSignerInformationForEquifaxUSA( SignerInformationForEquifaxUSA value ){
        if ( value != null ) { this.setSignerInformationForEquifaxUSA( value ); }
        return this;
    }
    public SignerInformationForEquifaxUSA getSignerInformationForEquifaxUSA(){
        return _signerInformationForEquifaxUSA;
    }

    public KnowledgeBasedAuthentication setSignerInformationForLexisNexis( SignerInformationForLexisNexis value ){
        // TODO With proper compare
        // if ( this._signerInformationForEquifaxUSA == value ) return this;
        this._signerInformationForLexisNexis = value;
        setDirty(FIELD_SIGNERINFORMATIONFORLEXISNEXIS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public KnowledgeBasedAuthentication safeSetSignerInformationForLexisNexis( SignerInformationForLexisNexis value ){
        if ( value != null ) { this.setSignerInformationForLexisNexis( value ); }
        return this;
    }
    public SignerInformationForLexisNexis getSignerInformationForLexisNexis(){
        return _signerInformationForLexisNexis;
    }
    
    
}