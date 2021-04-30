package com.silanis.esl.api.model;
//

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Auth extends Model
        implements java.io.Serializable {

    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CHALLENGES = "challenges";
    @JsonIgnore
    public static final String FIELD_SCHEME = "scheme";
    @JsonIgnore
    public static final String FIELD_IDV_WORKFLOW = "idvWorkflow";

    // Empty Constructor
    public Auth() {
    }

    // Fields
    protected List<AuthChallenge> _challenges = new ArrayList<AuthChallenge>();
    protected IdvWorkflow _idvWorkflow;
    protected String _scheme = "NONE";

    // Accessors

    public IdvWorkflow getIdvWorkflow() {
        return _idvWorkflow;
    }

    public Auth setIdvWorkflow(IdvWorkflow value) {
        this._idvWorkflow = value;
        setDirty(FIELD_IDV_WORKFLOW);
        return this;
    }

    public Auth setChallenges(List<AuthChallenge> value) {
        SchemaSanitizer.throwOnNull(FIELD_CHALLENGES, value);
        // TODO With proper compare
        // if ( this._challenges == value ) return this;
        this._challenges = value;
        setDirty(FIELD_CHALLENGES);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Auth safeSetChallenges(List<AuthChallenge> value) {
        if (value != null) {
            this.setChallenges(value);
        }
        return this;
    }

    public List<AuthChallenge> getChallenges() {
        return _challenges;
    }

    // List adder
    public Auth addChallenge(AuthChallenge value) {
        if (value == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        this._challenges.add(value);
        setDirty(FIELD_CHALLENGES);
        return this;
    }


    public Auth setScheme(String value) {
        SchemaSanitizer.throwOnNull(FIELD_SCHEME, value);
        // TODO With proper compare
        // if ( this._scheme == value ) return this;
        this._scheme = value;
        setDirty(FIELD_SCHEME);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Auth safeSetScheme(String value) {
        if (value != null) {
            this.setScheme(value);
        }
        return this;
    }

    public String getScheme() {
        return _scheme;
    }


}