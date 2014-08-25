package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
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
    
    // Empty Constructor
    public KnowledgeBasedAuthentication ( ) {}
    
    // Fields
    protected KnowledgeBasedAuthenticationStatus _knowledgeBasedAuthenticationStatus = KnowledgeBasedAuthenticationStatus.NOT_YET_ATTEMPTED;
    protected SignerInformationForEquifaxCanada _signerInformationForEquifaxCanada = null;
    protected SignerInformationForEquifaxUSA _signerInformationForEquifaxUSA = null;
    
    // Accessors
        
    
    public KnowledgeBasedAuthentication setKnowledgeBasedAuthenticationStatus( KnowledgeBasedAuthenticationStatus value ){
        SchemaSanitizer.throwOnNull(FIELD_KNOWLEDGEBASEDAUTHENTICATIONSTATUS,value);
        // TODO With proper compare
        // if ( this._knowledgeBasedAuthenticationStatus == value ) return this;
        this._knowledgeBasedAuthenticationStatus = value;
        setDirty(FIELD_KNOWLEDGEBASEDAUTHENTICATIONSTATUS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public KnowledgeBasedAuthentication safeSetKnowledgeBasedAuthenticationStatus( KnowledgeBasedAuthenticationStatus value ){
        if ( value != null ) { this.setKnowledgeBasedAuthenticationStatus( value ); }
        return this;
    }
    public KnowledgeBasedAuthenticationStatus getKnowledgeBasedAuthenticationStatus(){
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
    
    
}