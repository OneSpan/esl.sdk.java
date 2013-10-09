package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class DocumentToolbarOptions extends Model
      implements java.io.Serializable, IDocumentToolbarOptions
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_DOWNLOADBUTTON = "downloadButton";
    
    // Empty Constructor
    public DocumentToolbarOptions ( ) {}
    
    // Fields
    protected Boolean _downloadButton = true;
    
    // Accessors
        
    
    public DocumentToolbarOptions setDownloadButton( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DOWNLOADBUTTON,value);
        // TODO With proper compare
        // if ( this._downloadButton == value ) return this;
        this._downloadButton = value;
        setDirty(FIELD_DOWNLOADBUTTON);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public DocumentToolbarOptions safeSetDownloadButton( Boolean value ){
        if ( value != null ) { this.setDownloadButton( value ); }
        return this;
    }
    public Boolean getDownloadButton(){
        return _downloadButton;
    }
    @JsonIgnore
    public boolean evalDownloadButton(){
        return _downloadButton == null ? false : _downloadButton.booleanValue();
    }
    
    
}