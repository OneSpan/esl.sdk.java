package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Auth extends Model
      implements java.io.Serializable, IAuth
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CHALLENGES = "challenges";
    @JsonIgnore
    public static final String FIELD_SCHEME = "scheme";
    
    // Empty Constructor
    public Auth ( ) {}
    
    // Fields
    protected List<AuthChallenge> _challenges = new ArrayList<AuthChallenge>();
    protected AuthScheme _scheme = AuthScheme.NONE;
    
    // Accessors
        
    
    public Auth setChallenges( List<AuthChallenge> value ){
        SchemaSanitizer.throwOnNull(FIELD_CHALLENGES,value);
        // TODO With proper compare
        // if ( this._challenges == value ) return this;
        this._challenges = value;
        setDirty(FIELD_CHALLENGES);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Auth safeSetChallenges( List<AuthChallenge> value ){
        if ( value != null ) { this.setChallenges( value ); }
        return this;
    }
    public List<AuthChallenge> getChallenges(){
        return _challenges;
    }
    // List adder
    public Auth addChallenge( AuthChallenge value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._challenges.add(value);
        setDirty(FIELD_CHALLENGES);
        return this;
    }
    
        
    
    public Auth setScheme( AuthScheme value ){
        SchemaSanitizer.throwOnNull(FIELD_SCHEME,value);
        // TODO With proper compare
        // if ( this._scheme == value ) return this;
        this._scheme = value;
        setDirty(FIELD_SCHEME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Auth safeSetScheme( AuthScheme value ){
        if ( value != null ) { this.setScheme( value ); }
        return this;
    }
    public AuthScheme getScheme(){
        return _scheme;
    }
    
    
}