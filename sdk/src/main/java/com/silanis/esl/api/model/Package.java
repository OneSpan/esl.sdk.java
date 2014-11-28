package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.esl.api.util.JsonDateDeserializer;
import com.silanis.esl.api.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Package extends BasePackage
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_AUTOCOMPLETE = "autocomplete";
    @JsonIgnore
    public static final String FIELD_COMPLETED = "completed";
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
    public static final String FIELD_TRASHED = "trashed";
    @JsonIgnore
    public static final String FIELD_TYPE = "type";
    @JsonIgnore
    public static final String FIELD_UPDATED = "updated";
    @JsonIgnore
    public static final String FIELD_VISIBILITY = "visibility";
    
    // Empty Constructor
    public Package ( ) {}
    
    // Fields
    protected java.util.Date _completed = null;
    protected Boolean _trashed = false;
    
    // Accessors
        
    
    @Override
    public Package setAutocomplete( Boolean value ){
        super.setAutocomplete(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetAutocomplete( Boolean value ){
        if ( value != null ) { this.setAutocomplete( value ); }
        return this;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Package setCompleted( java.util.Date value ){
        // TODO With proper compare
        // if ( this._completed == value ) return this;
        this._completed = value;
        setDirty(FIELD_COMPLETED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetCompleted( java.util.Date value ){
        if ( value != null ) { this.setCompleted( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getCompleted(){
        return _completed;
    }
    
        
    
    @Override
    public Package setConsent( String value ){
        super.setConsent(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetConsent( String value ){
        if ( value != null ) { this.setConsent( value ); }
        return this;
    }
    
        
    
    @Override
    public Package setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    @Override
    public Package setDescription( String value ){
        super.setDescription(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetDescription( String value ){
        if ( value != null ) { this.setDescription( value ); }
        return this;
    }
    
        
    
    @Override
    public Package setDocuments( List<Document> value ){
        super.setDocuments(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetDocuments( List<Document> value ){
        if ( value != null ) { this.setDocuments( value ); }
        return this;
    }
    // List adder
    @Override
    public Package addDocument( Document value ){
        super.addDocument(value);
        return this;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Override
    public Package setDue( java.util.Date value ){
        super.setDue(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetDue( java.util.Date value ){
        if ( value != null ) { this.setDue( value ); }
        return this;
    }
    
        
    
    @Override
    public Package setEmailMessage( String value ){
        super.setEmailMessage(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetEmailMessage( String value ){
        if ( value != null ) { this.setEmailMessage( value ); }
        return this;
    }
    
        
    
    @Override
    public Package setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    @Override
    public Package setLanguage( String value ){
        super.setLanguage(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetLanguage( String value ){
        if ( value != null ) { this.setLanguage( value ); }
        return this;
    }
    
        
    
    @Override
    public Package setLimits( PackageArtifactsLimits value ){
        super.setLimits(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetLimits( PackageArtifactsLimits value ){
        if ( value != null ) { this.setLimits( value ); }
        return this;
    }
    
        
    
    @Override
    public Package setMessages( List<Message> value ){
        super.setMessages(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetMessages( List<Message> value ){
        if ( value != null ) { this.setMessages( value ); }
        return this;
    }
    // List adder
    @Override
    public Package addMessage( Message value ){
        super.addMessage(value);
        return this;
    }
    
        
    
    @Override
    public Package setName( String value ){
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
        
    
    @Override
    public Package setNotarized( Boolean value ){
        super.setNotarized(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetNotarized( Boolean value ){
        if ( value != null ) { this.setNotarized( value ); }
        return this;
    }
    
        
    
    @Override
    public Package setNotaryRoleId( String value ){
        super.setNotaryRoleId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetNotaryRoleId( String value ){
        if ( value != null ) { this.setNotaryRoleId( value ); }
        return this;
    }
    
        
    
    @Override
    public Package setRoles( List<Role> value ){
        super.setRoles(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetRoles( List<Role> value ){
        if ( value != null ) { this.setRoles( value ); }
        return this;
    }
    // List adder
    @Override
    public Package addRole( Role value ){
        super.addRole(value);
        return this;
    }
    
        
    
    @Override
    public Package setSender( Sender value ){
        super.setSender(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetSender( Sender value ){
        if ( value != null ) { this.setSender( value ); }
        return this;
    }
    
        
    
    @Override
    public Package setSettings( PackageSettings value ){
        super.setSettings(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetSettings( PackageSettings value ){
        if ( value != null ) { this.setSettings( value ); }
        return this;
    }
    
        
    
    @Override
    public Package setSignedDocumentDelivery( SignedDocumentDelivery value ){
        super.setSignedDocumentDelivery(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetSignedDocumentDelivery( SignedDocumentDelivery value ){
        if ( value != null ) { this.setSignedDocumentDelivery( value ); }
        return this;
    }
    
        
    
    @Override
    public Package setStatus( PackageStatus value ){
        super.setStatus(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetStatus( PackageStatus value ){
        if ( value != null ) { this.setStatus( value ); }
        return this;
    }
    
        
    
    public Package setTrashed( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_TRASHED,value);
        // TODO With proper compare
        // if ( this._trashed == value ) return this;
        this._trashed = value;
        setDirty(FIELD_TRASHED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetTrashed( Boolean value ){
        if ( value != null ) { this.setTrashed( value ); }
        return this;
    }
    public Boolean getTrashed(){
        return _trashed;
    }
    @JsonIgnore
    public boolean evalTrashed(){
        return _trashed == null ? false : _trashed.booleanValue();
    }
    
        
    
    @Override
    public Package setType( BasePackageType value ){
        super.setType(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetType( BasePackageType value ){
        if ( value != null ) { this.setType( value ); }
        return this;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Override
    public Package setUpdated( java.util.Date value ){
        super.setUpdated(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetUpdated( java.util.Date value ){
        if ( value != null ) { this.setUpdated( value ); }
        return this;
    }
    
        
    
    @Override
    public Package setVisibility( Visibility value ){
        super.setVisibility(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Package safeSetVisibility( Visibility value ){
        if ( value != null ) { this.setVisibility( value ); }
        return this;
    }
    
    
}