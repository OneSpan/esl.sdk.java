package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;

import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthChallenge extends Model
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_ANSWER = "answer";
    @JsonIgnore
    public static final String FIELD_MASKINPUT = "maskInput";
    @JsonIgnore
    public static final String FIELD_QUESTION = "question";

    @JsonIgnore
    public static final String FIELD_CHALLENGETYPE = "challengeType";
    
    // Empty Constructor
    public AuthChallenge ( ) {}
    
    // Fields
    protected String _answer = "";
    protected Boolean _maskInput = false;
    protected String _question = "";
    protected String _challengeType = null;
    
    // Accessors
        
    
    public AuthChallenge setAnswer( String value ){
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._answer == value ) return this;
        this._answer = value;
        setDirty(FIELD_ANSWER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AuthChallenge safeSetAnswer( String value ){
        if ( value != null ) { this.setAnswer( value ); }
        return this;
    }
    public String getAnswer(){
        return _answer;
    }
    
        
    
    public AuthChallenge setMaskInput( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_MASKINPUT,value);
        // TODO With proper compare
        // if ( this._maskInput == value ) return this;
        this._maskInput = value;
        setDirty(FIELD_MASKINPUT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AuthChallenge safeSetMaskInput( Boolean value ){
        if ( value != null ) { this.setMaskInput( value ); }
        return this;
    }
    public Boolean getMaskInput(){
        return _maskInput;
    }
    @JsonIgnore
    public boolean evalMaskInput(){
        return _maskInput == null ? false : _maskInput.booleanValue();
    }
    
        
    
    public AuthChallenge setQuestion( String value ){
        SchemaSanitizer.throwOnNull(FIELD_QUESTION,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._question == value ) return this;
        this._question = value;
        setDirty(FIELD_QUESTION);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AuthChallenge safeSetQuestion( String value ){
        if ( value != null ) { this.setQuestion( value ); }
        return this;
    }
    public String getQuestion(){
        return _question;
    }


    public AuthChallenge setChallengeType( String value ){
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
//         if ( this._challengeType == value ) return this;
        this._challengeType = value;
        setDirty(FIELD_CHALLENGETYPE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AuthChallenge safeSetChallengeType( String value ){
        if ( value != null ) { this.setChallengeType( value ); }
        return this;
    }
    public String getChallengeType(){
        return _challengeType;
    }

    
}