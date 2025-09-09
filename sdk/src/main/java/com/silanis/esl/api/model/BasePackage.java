package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.esl.api.util.JsonDateDeserializer;
import com.silanis.esl.api.util.JsonDateSerializer;
import com.silanis.esl.api.util.SchemaSanitizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown=true)
public class BasePackage extends Entity
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_AUTOCOMPLETE = "autocomplete";
    @JsonIgnore
    public static final String FIELD_CONSENT = "consent";
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_DESCRIPTION = "description";
    @JsonIgnore
    public static final String FIELD_DOCUMENTS = "documents";
    @JsonIgnore
    public static final String FIELD_DUE = "due";
    @JsonIgnore
    public static final String FIELD_EMAILMESSAGE = "emailMessage";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_LANGUAGE = "language";
    @JsonIgnore
    public static final String FIELD_LIMITS = "limits";
    @JsonIgnore
    public static final String FIELD_MESSAGES = "messages";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_NOTARIZED = "notarized";
    @JsonIgnore
    public static final String FIELD_NOTARYROLEID = "notaryRoleId";
    @JsonIgnore
    public static final String FIELD_ROLES = "roles";
    @JsonIgnore
    public static final String FIELD_SENDER = "sender";
    @JsonIgnore
    public static final String FIELD_SETTINGS = "settings";
    @JsonIgnore
    public static final String FIELD_SIGNEDDOCUMENTDELIVERY = "signedDocumentDelivery";
    @JsonIgnore
    public static final String FIELD_STATUS = "status";
    @JsonIgnore
    public static final String FIELD_TYPE = "type";
    @JsonIgnore
    public static final String FIELD_UPDATED = "updated";
    @JsonIgnore
    public static final String FIELD_VISIBILITY = "visibility";
    @JsonIgnore
    public static final String FIELD_CREATED = "created";
    @JsonIgnore
    public static final String FIELD_TIMEZONE_ID = "timezoneId";
    @JsonIgnore
    private static final String FIELD_CONDITIONS = "conditions";
    @JsonIgnore
    private static final String FIELD_ALERTS = "alerts";
    
    // Empty Constructor
    public BasePackage ( ) {}
    
    // Fields
    protected Boolean _autocomplete = false;
    protected String _consent = null;
    protected String _description = "";
    protected List<Document> _documents = new ArrayList<Document>();
    protected java.util.Date _due = null;
    protected String _emailMessage = "";
    protected String _language = "";
    protected PackageArtifactsLimits _limits = null;
    protected List<Message> _messages = new ArrayList<Message>();
    protected Boolean _notarized = null;
    protected String _notaryRoleId = "";
    protected List<Role> _roles = new ArrayList<Role>();
    protected Sender _sender;
    protected PackageSettings _settings = null;
    protected SignedDocumentDelivery _signedDocumentDelivery = null;
    protected String _status = "DRAFT";
    protected String _type = "PACKAGE";
    protected java.util.Date _updated;
    protected String _visibility = "ACCOUNT";
    protected java.util.Date _created;
    protected String _timezoneId;
    protected List<FieldCondition> conditions;
    protected List<SystemAlert> alerts;
    // Accessors
        
    
    public BasePackage setAutocomplete( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_AUTOCOMPLETE,value);
        // TODO With proper compare
        // if ( this._autocomplete == value ) return this;
        this._autocomplete = value;
        setDirty(FIELD_AUTOCOMPLETE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetAutocomplete( Boolean value ){
        if ( value != null ) { this.setAutocomplete( value ); }
        return this;
    }
    public Boolean getAutocomplete(){
        return _autocomplete;
    }
    @JsonIgnore
    public boolean evalAutocomplete(){
        return _autocomplete == null ? false : _autocomplete.booleanValue();
    }
    
        
    
    public BasePackage setConsent( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._consent == value ) return this;
        this._consent = value;
        setDirty(FIELD_CONSENT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetConsent( String value ){
        if ( value != null ) { this.setConsent( value ); }
        return this;
    }
    public String getConsent(){
        return _consent;
    }
    
        
    
    @Override
    public BasePackage setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    public BasePackage setDescription( String value ){
        SchemaSanitizer.throwOnNull(FIELD_DESCRIPTION,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._description == value ) return this;
        this._description = value;
        setDirty(FIELD_DESCRIPTION);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetDescription( String value ){
        if ( value != null ) { this.setDescription(value); }
        return this;
    }
    public String getDescription(){
        return _description;
    }
    
        
    
    public BasePackage setDocuments( List<Document> value ){
        SchemaSanitizer.throwOnNull(FIELD_DOCUMENTS,value);
        // TODO With proper compare
        // if ( this._documents == value ) return this;
        this._documents = value;
        setDirty(FIELD_DOCUMENTS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetDocuments( List<Document> value ){
        if ( value != null ) { this.setDocuments( value ); }
        return this;
    }
    public List<Document> getDocuments(){
        return _documents;
    }
    // List adder
    public BasePackage addDocument( Document value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._documents.add(value);
        setDirty(FIELD_DOCUMENTS);
        return this;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public BasePackage setDue( java.util.Date value ){
        // TODO With proper compare
        // if ( this._due == value ) return this;
        this._due = value;
        setDirty(FIELD_DUE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetDue( java.util.Date value ){
        if ( value != null ) { this.setDue( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getDue(){
        return _due;
    }
    
        
    
    public BasePackage setEmailMessage( String value ){
        SchemaSanitizer.throwOnNull(FIELD_EMAILMESSAGE,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._emailMessage == value ) return this;
        this._emailMessage = value;
        setDirty(FIELD_EMAILMESSAGE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetEmailMessage( String value ){
        if ( value != null ) { this.setEmailMessage( value ); }
        return this;
    }
    public String getEmailMessage(){
        return _emailMessage;
    }
    
        
    
    @Override
    public BasePackage setId( String value ){
        // TODO: Figure how to do refinements of validation rules
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    public BasePackage setLanguage( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._language == value ) return this;
        this._language = value;
        setDirty(FIELD_LANGUAGE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetLanguage( String value ){
        if ( value != null ) { this.setLanguage(value); }
        return this;
    }
    public String getLanguage(){
        return _language;
    }
    
        
    
    public BasePackage setLimits( PackageArtifactsLimits value ){
        // TODO With proper compare
        // if ( this._limits == value ) return this;
        this._limits = value;
        setDirty(FIELD_LIMITS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetLimits( PackageArtifactsLimits value ){
        if ( value != null ) { this.setLimits( value ); }
        return this;
    }
    public PackageArtifactsLimits getLimits(){
        return _limits;
    }
    
        
    
    public BasePackage setMessages( List<Message> value ){
        SchemaSanitizer.throwOnNull(FIELD_MESSAGES,value);
        // TODO With proper compare
        // if ( this._messages == value ) return this;
        this._messages = value;
        setDirty(FIELD_MESSAGES);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetMessages( List<Message> value ){
        if ( value != null ) { this.setMessages( value ); }
        return this;
    }
    public List<Message> getMessages(){
        return _messages;
    }
    // List adder
    public BasePackage addMessage( Message value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._messages.add(value);
        setDirty(FIELD_MESSAGES);
        return this;
    }
    
        
    
    @Override
    public BasePackage setName( String value ){
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
        
    
    public BasePackage setNotarized( Boolean value ){
        // TODO With proper compare
        // if ( this._notarized == value ) return this;
        this._notarized = value;
        setDirty(FIELD_NOTARIZED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetNotarized( Boolean value ){
        if ( value != null ) { this.setNotarized(value); }
        return this;
    }
    public Boolean getNotarized(){
        return _notarized;
    }
    
        
    
    public BasePackage setNotaryRoleId( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._notaryRoleId == value ) return this;
        this._notaryRoleId = value;
        setDirty(FIELD_NOTARYROLEID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetNotaryRoleId( String value ){
        if ( value != null ) { this.setNotaryRoleId( value ); }
        return this;
    }
    public String getNotaryRoleId(){
        return _notaryRoleId;
    }
    
        
    
    public BasePackage setRoles( List<Role> value ){
        SchemaSanitizer.throwOnNull(FIELD_ROLES,value);
        // TODO With proper compare
        // if ( this._roles == value ) return this;
        this._roles = value;
        setDirty(FIELD_ROLES);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetRoles( List<Role> value ){
        if ( value != null ) { this.setRoles( value ); }
        return this;
    }
    public List<Role> getRoles(){
        return _roles;
    }
    // List adder
    public BasePackage addRole( Role value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._roles.add(value);
        setDirty(FIELD_ROLES);
        return this;
    }
    
        
    
    public BasePackage setSender( Sender value ){
        SchemaSanitizer.throwOnNull(FIELD_SENDER,value);
        // TODO With proper compare
        // if ( this._sender == value ) return this;
        this._sender = value;
        setDirty(FIELD_SENDER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetSender( Sender value ){
        if ( value != null ) { this.setSender( value ); }
        return this;
    }
    public Sender getSender(){
        return _sender;
    }
    
        
    
    public BasePackage setSettings( PackageSettings value ){
        // TODO With proper compare
        // if ( this._settings == value ) return this;
        this._settings = value;
        setDirty(FIELD_SETTINGS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetSettings( PackageSettings value ){
        if ( value != null ) { this.setSettings( value ); }
        return this;
    }
    public PackageSettings getSettings(){
        return _settings;
    }
    
        
    
    public BasePackage setSignedDocumentDelivery( SignedDocumentDelivery value ){
        // TODO With proper compare
        // if ( this._signedDocumentDelivery == value ) return this;
        this._signedDocumentDelivery = value;
        setDirty(FIELD_SIGNEDDOCUMENTDELIVERY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetSignedDocumentDelivery( SignedDocumentDelivery value ){
        if ( value != null ) { this.setSignedDocumentDelivery( value ); }
        return this;
    }
    public SignedDocumentDelivery getSignedDocumentDelivery(){
        return _signedDocumentDelivery;
    }
    
        
    
    public BasePackage setStatus( String value ){
        SchemaSanitizer.throwOnNull(FIELD_STATUS,value);
        // TODO With proper compare
        // if ( this._status == value ) return this;
        this._status = value;
        setDirty(FIELD_STATUS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetStatus( String value ){
        if ( value != null ) { this.setStatus( value ); }
        return this;
    }
    public String getStatus(){
        return _status;
    }
    
        
    
    public BasePackage setType( String value ){
        SchemaSanitizer.throwOnNull(FIELD_TYPE,value);
        // TODO With proper compare
        // if ( this._type == value ) return this;
        this._type = value;
        setDirty(FIELD_TYPE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetType( String value ){
        if ( value != null ) { this.setType( value ); }
        return this;
    }
    public String getType(){
        return _type;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public BasePackage setUpdated( java.util.Date value ){
        SchemaSanitizer.throwOnNull(FIELD_UPDATED,value);
        // TODO With proper compare
        // if ( this._updated == value ) return this;
        this._updated = value;
        setDirty(FIELD_UPDATED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetUpdated( java.util.Date value ){
        if ( value != null ) { this.setUpdated( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getUpdated(){
        return _updated;
    }
    
        
    
    public BasePackage setVisibility( String value ){
        SchemaSanitizer.throwOnNull(FIELD_VISIBILITY,value);
        // TODO With proper compare
        // if ( this._visibility == value ) return this;
        this._visibility = value;
        setDirty(FIELD_VISIBILITY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetVisibility( String value ){
        if ( value != null ) { this.setVisibility( value ); }
        return this;
    }
    public String getVisibility(){
        return _visibility;
    }


    @JsonDeserialize(using = JsonDateDeserializer.class)
    public BasePackage setCreated( java.util.Date value ){
        SchemaSanitizer.throwOnNull(FIELD_CREATED, value);
        this._created = value;
        setDirty(FIELD_CREATED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetCreated( java.util.Date value ){
        if ( value != null ) { this.setCreated( value ); }
        return this;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getCreated(){
        return _created;
    }

    public BasePackage setTimezoneId( String value ){
        this._timezoneId = value;
        setDirty(FIELD_TIMEZONE_ID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BasePackage safeSetTimezoneId( String value ){
        if ( value != null ) { this.setTimezoneId( value ); }
        return this;
    }

    public String getTimezoneId(){
        return _timezoneId;
    }

    public BasePackage setConditions(List<FieldCondition> value) {

        this.conditions = value;
        setDirty(FIELD_CONDITIONS);
        return this;
    }

    public List<FieldCondition> getConditions() {
        return conditions;
    }

    public BasePackage setAlerts(List<SystemAlert> value) {

        this.alerts = value;
        setDirty(FIELD_ALERTS);
        return this;
    }

    public List<SystemAlert> getAlerts() {
        return alerts;
    }
}
