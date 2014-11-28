package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class GlobalActionsOptions extends Model
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CONFIRM = "confirm";
    @JsonIgnore
    public static final String FIELD_DOWNLOAD = "download";
    @JsonIgnore
    public static final String FIELD_HIDEEVIDENCESUMMARY = "hideEvidenceSummary";
    @JsonIgnore
    public static final String FIELD_SAVEASLAYOUT = "saveAsLayout";
    
    // Empty Constructor
    public GlobalActionsOptions ( ) {}
    
    // Fields
    protected Boolean _confirm = true;
    protected Boolean _download = true;
    protected Boolean _hideEvidenceSummary = false;
    protected Boolean _saveAsLayout = true;
    
    // Accessors
        
    
    public GlobalActionsOptions setConfirm( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_CONFIRM,value);
        // TODO With proper compare
        // if ( this._confirm == value ) return this;
        this._confirm = value;
        setDirty(FIELD_CONFIRM);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public GlobalActionsOptions safeSetConfirm( Boolean value ){
        if ( value != null ) { this.setConfirm( value ); }
        return this;
    }
    public Boolean getConfirm(){
        return _confirm;
    }
    @JsonIgnore
    public boolean evalConfirm(){
        return _confirm == null ? false : _confirm.booleanValue();
    }
    
        
    
    public GlobalActionsOptions setDownload( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DOWNLOAD,value);
        // TODO With proper compare
        // if ( this._download == value ) return this;
        this._download = value;
        setDirty(FIELD_DOWNLOAD);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public GlobalActionsOptions safeSetDownload( Boolean value ){
        if ( value != null ) { this.setDownload( value ); }
        return this;
    }
    public Boolean getDownload(){
        return _download;
    }
    @JsonIgnore
    public boolean evalDownload(){
        return _download == null ? false : _download.booleanValue();
    }
    
        
    
    public GlobalActionsOptions setHideEvidenceSummary( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_HIDEEVIDENCESUMMARY,value);
        // TODO With proper compare
        // if ( this._hideEvidenceSummary == value ) return this;
        this._hideEvidenceSummary = value;
        setDirty(FIELD_HIDEEVIDENCESUMMARY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public GlobalActionsOptions safeSetHideEvidenceSummary( Boolean value ){
        if ( value != null ) { this.setHideEvidenceSummary( value ); }
        return this;
    }
    public Boolean getHideEvidenceSummary(){
        return _hideEvidenceSummary;
    }
    @JsonIgnore
    public boolean evalHideEvidenceSummary(){
        return _hideEvidenceSummary == null ? false : _hideEvidenceSummary.booleanValue();
    }
    
        
    
    public GlobalActionsOptions setSaveAsLayout( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_SAVEASLAYOUT,value);
        // TODO With proper compare
        // if ( this._saveAsLayout == value ) return this;
        this._saveAsLayout = value;
        setDirty(FIELD_SAVEASLAYOUT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public GlobalActionsOptions safeSetSaveAsLayout( Boolean value ){
        if ( value != null ) { this.setSaveAsLayout( value ); }
        return this;
    }
    public Boolean getSaveAsLayout(){
        return _saveAsLayout;
    }
    @JsonIgnore
    public boolean evalSaveAsLayout(){
        return _saveAsLayout == null ? false : _saveAsLayout.booleanValue();
    }
    
    
}