package com.silanis.esl.api.model;
//

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Handover extends Model
      implements java.io.Serializable
{

    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_HREF = "href";
    @JsonIgnore
    public static final String FIELD_TEXT = "text";
    @JsonIgnore
    public static final String FIELD_TITLE = "title";

    // Empty Constructor
    public Handover( ) {}
    
    // Fields
    protected String _href = "";
    protected String _text = "";
    protected String _title = "";
    
    // Accessors
        
    
    public Handover setHref(String value ){
        SchemaSanitizer.throwOnNull(FIELD_HREF,value);
        value = SchemaSanitizer.trim(value);
        this._href = value;
        setDirty(FIELD_HREF);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Handover safeSetHref(String value ){
        if ( value != null ) { this.setHref( value ); }
        return this;
    }
    public String getHref(){
        return _href;
    }
    
        
    
    public Handover setText(String value ){
        SchemaSanitizer.throwOnNull(FIELD_TEXT,value);
        value = SchemaSanitizer.trim(value);
        this._text = value;
        setDirty(FIELD_TEXT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Handover safeSetText(String value ){
        if ( value != null ) { this.setText( value ); }
        return this;
    }
    public String getText(){
        return _text;
    }
    
        
    
    public Handover setTitle(String value ){
        value = SchemaSanitizer.trim(value);
        this._title = value;
        setDirty(FIELD_TITLE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Handover safeSetTitle(String value ){
        if ( value != null ) { this.setTitle( value ); }
        return this;
    }
    public String getTitle(){
        return _title;
    }
    
    
}