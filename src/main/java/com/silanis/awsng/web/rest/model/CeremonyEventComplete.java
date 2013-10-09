package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class CeremonyEventComplete extends Model
      implements java.io.Serializable, ICeremonyEventComplete
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_DIALOG = "dialog";
    @JsonIgnore
    public static final String FIELD_REDIRECT = "redirect";
    
    // Empty Constructor
    public CeremonyEventComplete ( ) {}
    
    // Fields
    protected Boolean _dialog = true;
    protected String _redirect = "";
    
    // Accessors
        
    
    public CeremonyEventComplete setDialog( Boolean value ){
        // TODO With proper compare
        // if ( this._dialog == value ) return this;
        this._dialog = value;
        setDirty(FIELD_DIALOG);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonyEventComplete safeSetDialog( Boolean value ){
        if ( value != null ) { this.setDialog( value ); }
        return this;
    }
    public Boolean getDialog(){
        return _dialog;
    }
    @JsonIgnore
    public boolean evalDialog(){
        return _dialog == null ? false : _dialog.booleanValue();
    }
    
        
    
    public CeremonyEventComplete setRedirect( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._redirect == value ) return this;
        this._redirect = value;
        setDirty(FIELD_REDIRECT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonyEventComplete safeSetRedirect( String value ){
        if ( value != null ) { this.setRedirect( value ); }
        return this;
    }
    public String getRedirect(){
        return _redirect;
    }
    
    
}