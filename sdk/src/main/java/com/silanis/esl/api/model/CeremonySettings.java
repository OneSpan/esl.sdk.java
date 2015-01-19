package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown=true)
public class CeremonySettings extends ViewSettings
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_DECLINEBUTTON = "declineButton";
    @JsonIgnore
    public static final String FIELD_DISABLEDOWNLOADFORUNCOMPLETEDPACKAGE = "disableDownloadForUncompletedPackage";
    @JsonIgnore
    public static final String FIELD_DISABLEFIRSTINPERSONAFFIDAVIT = "disableFirstInPersonAffidavit";
    @JsonIgnore
    public static final String FIELD_DISABLEINPERSONAFFIDAVIT = "disableInPersonAffidavit";
    @JsonIgnore
    public static final String FIELD_DISABLESECONDINPERSONAFFIDAVIT = "disableSecondInPersonAffidavit";
    @JsonIgnore
    public static final String FIELD_DOCUMENTTOOLBAROPTIONS = "documentToolbarOptions";
    @JsonIgnore
    public static final String FIELD_EVENTS = "events";
    @JsonIgnore
    public static final String FIELD_HANDOVER = "handOver";
    @JsonIgnore
    public static final String FIELD_HIDECAPTURETEXT = "hideCaptureText";
    @JsonIgnore
    public static final String FIELD_HIDELANGUAGEDROPDOWN = "hideLanguageDropdown";
    @JsonIgnore
    public static final String FIELD_HIDEPACKAGEOWNERINPERSON = "hidePackageOwnerInPerson";
    @JsonIgnore
    public static final String FIELD_HIDEWATERMARK = "hideWatermark";
    @JsonIgnore
    public static final String FIELD_INPERSON = "inPerson";
    @JsonIgnore
    public static final String FIELD_LAYOUT = "layout";
    @JsonIgnore
    public static final String FIELD_MAXAUTHFAILSALLOWED = "maxAuthFailsAllowed";
    @JsonIgnore
    public static final String FIELD_OPTOUTBUTTON = "optOutButton";
    @JsonIgnore
    public static final String FIELD_OPTOUTREASONS = "optOutReasons";
    @JsonIgnore
    public static final String FIELD_STYLE = "style";
    
    // Empty Constructor
    public CeremonySettings ( ) {}
    
    // Fields
    protected Boolean _declineButton = false;
    protected Boolean _disableDownloadForUncompletedPackage = false;
    protected Boolean _disableFirstInPersonAffidavit = false;
    protected Boolean _disableInPersonAffidavit = false;
    protected Boolean _disableSecondInPersonAffidavit = false;
    protected DocumentToolbarOptions _documentToolbarOptions = null;
    protected CeremonyEvents _events = null;
    protected Link _handOver = null;
    protected Boolean _hideCaptureText = false;
    protected Boolean _hideLanguageDropdown = false;
    protected Boolean _hidePackageOwnerInPerson = false;
    protected Boolean _hideWatermark = false;
    protected Boolean _inPerson = true;
    protected Integer _maxAuthFailsAllowed = null;
    protected Boolean _optOutButton = false;
    protected List<String> _optOutReasons = new ArrayList<String>();
    
    // Accessors
        
    
    public CeremonySettings setDeclineButton( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DECLINEBUTTON,value);
        // TODO With proper compare
        // if ( this._declineButton == value ) return this;
        this._declineButton = value;
        setDirty(FIELD_DECLINEBUTTON);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonySettings safeSetDeclineButton( Boolean value ){
        if ( value != null ) { this.setDeclineButton( value ); }
        return this;
    }
    public Boolean getDeclineButton(){
        return _declineButton;
    }
    @JsonIgnore
    public boolean evalDeclineButton(){
        return _declineButton == null ? false : _declineButton.booleanValue();
    }
    
        
    
    public CeremonySettings setDisableDownloadForUncompletedPackage( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DISABLEDOWNLOADFORUNCOMPLETEDPACKAGE,value);
        // TODO With proper compare
        // if ( this._disableDownloadForUncompletedPackage == value ) return this;
        this._disableDownloadForUncompletedPackage = value;
        setDirty(FIELD_DISABLEDOWNLOADFORUNCOMPLETEDPACKAGE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonySettings safeSetDisableDownloadForUncompletedPackage( Boolean value ){
        if ( value != null ) { this.setDisableDownloadForUncompletedPackage( value ); }
        return this;
    }
    public Boolean getDisableDownloadForUncompletedPackage(){
        return _disableDownloadForUncompletedPackage;
    }
    @JsonIgnore
    public boolean evalDisableDownloadForUncompletedPackage(){
        return _disableDownloadForUncompletedPackage == null ? false : _disableDownloadForUncompletedPackage.booleanValue();
    }
    
        
    
    public CeremonySettings setDisableFirstInPersonAffidavit( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DISABLEFIRSTINPERSONAFFIDAVIT,value);
        // TODO With proper compare
        // if ( this._disableFirstInPersonAffidavit == value ) return this;
        this._disableFirstInPersonAffidavit = value;
        setDirty(FIELD_DISABLEFIRSTINPERSONAFFIDAVIT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonySettings safeSetDisableFirstInPersonAffidavit( Boolean value ){
        if ( value != null ) { this.setDisableFirstInPersonAffidavit( value ); }
        return this;
    }
    public Boolean getDisableFirstInPersonAffidavit(){
        return _disableFirstInPersonAffidavit;
    }
    @JsonIgnore
    public boolean evalDisableFirstInPersonAffidavit(){
        return _disableFirstInPersonAffidavit == null ? false : _disableFirstInPersonAffidavit.booleanValue();
    }
    
        
    
    public CeremonySettings setDisableInPersonAffidavit( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DISABLEINPERSONAFFIDAVIT,value);
        // TODO With proper compare
        // if ( this._disableInPersonAffidavit == value ) return this;
        this._disableInPersonAffidavit = value;
        setDirty(FIELD_DISABLEINPERSONAFFIDAVIT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonySettings safeSetDisableInPersonAffidavit( Boolean value ){
        if ( value != null ) { this.setDisableInPersonAffidavit( value ); }
        return this;
    }
    public Boolean getDisableInPersonAffidavit(){
        return _disableInPersonAffidavit;
    }
    @JsonIgnore
    public boolean evalDisableInPersonAffidavit(){
        return _disableInPersonAffidavit == null ? false : _disableInPersonAffidavit.booleanValue();
    }
    
        
    
    public CeremonySettings setDisableSecondInPersonAffidavit( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DISABLESECONDINPERSONAFFIDAVIT,value);
        // TODO With proper compare
        // if ( this._disableSecondInPersonAffidavit == value ) return this;
        this._disableSecondInPersonAffidavit = value;
        setDirty(FIELD_DISABLESECONDINPERSONAFFIDAVIT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonySettings safeSetDisableSecondInPersonAffidavit( Boolean value ){
        if ( value != null ) { this.setDisableSecondInPersonAffidavit( value ); }
        return this;
    }
    public Boolean getDisableSecondInPersonAffidavit(){
        return _disableSecondInPersonAffidavit;
    }
    @JsonIgnore
    public boolean evalDisableSecondInPersonAffidavit(){
        return _disableSecondInPersonAffidavit == null ? false : _disableSecondInPersonAffidavit.booleanValue();
    }
    
        
    
    public CeremonySettings setDocumentToolbarOptions( DocumentToolbarOptions value ){
        // TODO With proper compare
        // if ( this._documentToolbarOptions == value ) return this;
        this._documentToolbarOptions = value;
        setDirty(FIELD_DOCUMENTTOOLBAROPTIONS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonySettings safeSetDocumentToolbarOptions( DocumentToolbarOptions value ){
        if ( value != null ) { this.setDocumentToolbarOptions( value ); }
        return this;
    }
    public DocumentToolbarOptions getDocumentToolbarOptions(){
        return _documentToolbarOptions;
    }
    
        
    
    public CeremonySettings setEvents( CeremonyEvents value ){
        // TODO With proper compare
        // if ( this._events == value ) return this;
        this._events = value;
        setDirty(FIELD_EVENTS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonySettings safeSetEvents( CeremonyEvents value ){
        if ( value != null ) { this.setEvents( value ); }
        return this;
    }
    public CeremonyEvents getEvents(){
        return _events;
    }
    
        
    
    public CeremonySettings setHandOver( Link value ){
        // TODO With proper compare
        // if ( this._handOver == value ) return this;
        this._handOver = value;
        setDirty(FIELD_HANDOVER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonySettings safeSetHandOver( Link value ){
        if ( value != null ) { this.setHandOver( value ); }
        return this;
    }
    public Link getHandOver(){
        return _handOver;
    }
    
        
    
    public CeremonySettings setHideCaptureText( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_HIDECAPTURETEXT,value);
        // TODO With proper compare
        // if ( this._hideCaptureText == value ) return this;
        this._hideCaptureText = value;
        setDirty(FIELD_HIDECAPTURETEXT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonySettings safeSetHideCaptureText( Boolean value ){
        if ( value != null ) { this.setHideCaptureText( value ); }
        return this;
    }
    public Boolean getHideCaptureText(){
        return _hideCaptureText;
    }
    @JsonIgnore
    public boolean evalHideCaptureText(){
        return _hideCaptureText == null ? false : _hideCaptureText.booleanValue();
    }
    
        
    
    public CeremonySettings setHideLanguageDropdown( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_HIDELANGUAGEDROPDOWN,value);
        // TODO With proper compare
        // if ( this._hideLanguageDropdown == value ) return this;
        this._hideLanguageDropdown = value;
        setDirty(FIELD_HIDELANGUAGEDROPDOWN);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonySettings safeSetHideLanguageDropdown( Boolean value ){
        if ( value != null ) { this.setHideLanguageDropdown( value ); }
        return this;
    }
    public Boolean getHideLanguageDropdown(){
        return _hideLanguageDropdown;
    }
    @JsonIgnore
    public boolean evalHideLanguageDropdown(){
        return _hideLanguageDropdown == null ? false : _hideLanguageDropdown.booleanValue();
    }
    
        
    
    public CeremonySettings setHidePackageOwnerInPerson( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_HIDEPACKAGEOWNERINPERSON,value);
        // TODO With proper compare
        // if ( this._hidePackageOwnerInPerson == value ) return this;
        this._hidePackageOwnerInPerson = value;
        setDirty(FIELD_HIDEPACKAGEOWNERINPERSON);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonySettings safeSetHidePackageOwnerInPerson( Boolean value ){
        if ( value != null ) { this.setHidePackageOwnerInPerson( value ); }
        return this;
    }
    public Boolean getHidePackageOwnerInPerson(){
        return _hidePackageOwnerInPerson;
    }
    @JsonIgnore
    public boolean evalHidePackageOwnerInPerson(){
        return _hidePackageOwnerInPerson == null ? false : _hidePackageOwnerInPerson.booleanValue();
    }
    
        
    
    public CeremonySettings setHideWatermark( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_HIDEWATERMARK,value);
        // TODO With proper compare
        // if ( this._hideWatermark == value ) return this;
        this._hideWatermark = value;
        setDirty(FIELD_HIDEWATERMARK);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonySettings safeSetHideWatermark( Boolean value ){
        if ( value != null ) { this.setHideWatermark( value ); }
        return this;
    }
    public Boolean getHideWatermark(){
        return _hideWatermark;
    }
    @JsonIgnore
    public boolean evalHideWatermark(){
        return _hideWatermark == null ? false : _hideWatermark.booleanValue();
    }
    
        
    
    public CeremonySettings setInPerson( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_INPERSON,value);
        // TODO With proper compare
        // if ( this._inPerson == value ) return this;
        this._inPerson = value;
        setDirty(FIELD_INPERSON);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonySettings safeSetInPerson( Boolean value ){
        if ( value != null ) { this.setInPerson( value ); }
        return this;
    }
    public Boolean getInPerson(){
        return _inPerson;
    }
    @JsonIgnore
    public boolean evalInPerson(){
        return _inPerson == null ? false : _inPerson.booleanValue();
    }
    
        
    
    @Override
    public CeremonySettings setLayout( LayoutOptions value ){
        super.setLayout(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonySettings safeSetLayout( LayoutOptions value ){
        if ( value != null ) { this.setLayout( value ); }
        return this;
    }
    
        
    
    public CeremonySettings setMaxAuthFailsAllowed( Integer value ){
        // TODO With proper compare
        // if ( this._maxAuthFailsAllowed == value ) return this;
        this._maxAuthFailsAllowed = value;
        setDirty(FIELD_MAXAUTHFAILSALLOWED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonySettings safeSetMaxAuthFailsAllowed( Integer value ){
        if ( value != null ) { this.setMaxAuthFailsAllowed( value ); }
        return this;
    }
    public Integer getMaxAuthFailsAllowed(){
        return _maxAuthFailsAllowed;
    }
    
        
    
    public CeremonySettings setOptOutButton( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_OPTOUTBUTTON,value);
        // TODO With proper compare
        // if ( this._optOutButton == value ) return this;
        this._optOutButton = value;
        setDirty(FIELD_OPTOUTBUTTON);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonySettings safeSetOptOutButton( Boolean value ){
        if ( value != null ) { this.setOptOutButton( value ); }
        return this;
    }
    public Boolean getOptOutButton(){
        return _optOutButton;
    }
    @JsonIgnore
    public boolean evalOptOutButton(){
        return _optOutButton == null ? false : _optOutButton.booleanValue();
    }
    
        
    
    public CeremonySettings setOptOutReasons( List<String> value ){
        SchemaSanitizer.throwOnNull(FIELD_OPTOUTREASONS,value);
        // TODO With proper compare
        // if ( this._optOutReasons == value ) return this;
        this._optOutReasons = value;
        setDirty(FIELD_OPTOUTREASONS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonySettings safeSetOptOutReasons( List<String> value ){
        if ( value != null ) { this.setOptOutReasons( value ); }
        return this;
    }
    public List<String> getOptOutReasons(){
        return _optOutReasons;
    }
    // List adder
    public CeremonySettings addOptOutReason( String value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._optOutReasons.add(value);
        setDirty(FIELD_OPTOUTREASONS);
        return this;
    }
    
        
    
    @Override
    public CeremonySettings setStyle( LayoutStyle value ){
        super.setStyle(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonySettings safeSetStyle( LayoutStyle value ){
        if ( value != null ) { this.setStyle( value ); }
        return this;
    }
    
    
}