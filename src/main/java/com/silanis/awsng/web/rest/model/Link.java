package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Link extends Model
      implements java.io.Serializable, ILink
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_HREF = "href";
    @JsonIgnore
    public static final String FIELD_TEXT = "text";
    @JsonIgnore
    public static final String FIELD_TITLE = "title";
    
    // Empty Constructor
    public Link ( ) {}
    
    // Fields
    protected String _href = "";
    protected String _text = "";
    protected String _title = "";
    
    // Accessors
        
    
    public Link setHref( String value ){
        SchemaSanitizer.throwOnNull(FIELD_HREF,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._href == value ) return this;
        this._href = value;
        setDirty(FIELD_HREF);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Link safeSetHref( String value ){
        if ( value != null ) { this.setHref( value ); }
        return this;
    }
    public String getHref(){
        return _href;
    }
    
        
    
    public Link setText( String value ){
        SchemaSanitizer.throwOnNull(FIELD_TEXT,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._text == value ) return this;
        this._text = value;
        setDirty(FIELD_TEXT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Link safeSetText( String value ){
        if ( value != null ) { this.setText( value ); }
        return this;
    }
    public String getText(){
        return _text;
    }
    
        
    
    public Link setTitle( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._title == value ) return this;
        this._title = value;
        setDirty(FIELD_TITLE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Link safeSetTitle( String value ){
        if ( value != null ) { this.setTitle( value ); }
        return this;
    }
    public String getTitle(){
        return _title;
    }
    
    
}