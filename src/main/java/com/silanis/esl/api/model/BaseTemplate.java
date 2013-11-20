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
public class BaseTemplate extends BasePackage
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
    
    // Empty Constructor
    public BaseTemplate ( ) {}
    
    // Fields
    protected Visibility _visibility = Visibility.ACCOUNT;
    
    // Accessors
        
    
    @Override
    public BaseTemplate setAutocomplete( Boolean value ){
        super.setAutocomplete(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetAutocomplete( Boolean value ){
        if ( value != null ) { this.setAutocomplete( value ); }
        return this;
    }
    
        
    
    @Override
    public BaseTemplate setConsent( String value ){
        super.setConsent(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetConsent( String value ){
        if ( value != null ) { this.setConsent( value ); }
        return this;
    }
    
        
    
    @Override
    public BaseTemplate setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    @Override
    public BaseTemplate setDescription( String value ){
        super.setDescription(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetDescription( String value ){
        if ( value != null ) { this.setDescription( value ); }
        return this;
    }
    
        
    
    @Override
    public BaseTemplate setDocuments( List<Document> value ){
        super.setDocuments(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetDocuments( List<Document> value ){
        if ( value != null ) { this.setDocuments( value ); }
        return this;
    }
    // List adder
    @Override
    public BaseTemplate addDocument( Document value ){
        super.addDocument(value);
        return this;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Override
    public BaseTemplate setDue( java.util.Date value ){
        super.setDue(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetDue( java.util.Date value ){
        if ( value != null ) { this.setDue( value ); }
        return this;
    }
    
        
    
    @Override
    public BaseTemplate setEmailMessage( String value ){
        super.setEmailMessage(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetEmailMessage( String value ){
        if ( value != null ) { this.setEmailMessage( value ); }
        return this;
    }
    
        
    
    @Override
    public BaseTemplate setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    @Override
    public BaseTemplate setLanguage( String value ){
        super.setLanguage(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetLanguage( String value ){
        if ( value != null ) { this.setLanguage( value ); }
        return this;
    }
    
        
    
    @Override
    public BaseTemplate setLimits( PackageArtifactsLimits value ){
        super.setLimits(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetLimits( PackageArtifactsLimits value ){
        if ( value != null ) { this.setLimits( value ); }
        return this;
    }
    
        
    
    @Override
    public BaseTemplate setMessages( List<Message> value ){
        super.setMessages(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetMessages( List<Message> value ){
        if ( value != null ) { this.setMessages( value ); }
        return this;
    }
    // List adder
    @Override
    public BaseTemplate addMessage( Message value ){
        super.addMessage(value);
        return this;
    }
    
        
    
    @Override
    public BaseTemplate setName( String value ){
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
        
    
    @Override
    public BaseTemplate setRoles( List<Role> value ){
        super.setRoles(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetRoles( List<Role> value ){
        if ( value != null ) { this.setRoles( value ); }
        return this;
    }
    // List adder
    @Override
    public BaseTemplate addRole( Role value ){
        super.addRole(value);
        return this;
    }
    
        
    
    @Override
    public BaseTemplate setSender( Sender value ){
        super.setSender(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetSender( Sender value ){
        if ( value != null ) { this.setSender( value ); }
        return this;
    }
    
        
    
    @Override
    public BaseTemplate setSettings( PackageSettings value ){
        super.setSettings(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetSettings( PackageSettings value ){
        if ( value != null ) { this.setSettings( value ); }
        return this;
    }
    
        
    
    @Override
    public BaseTemplate setSignedDocumentDelivery( SignedDocumentDelivery value ){
        super.setSignedDocumentDelivery(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetSignedDocumentDelivery( SignedDocumentDelivery value ){
        if ( value != null ) { this.setSignedDocumentDelivery( value ); }
        return this;
    }
    
        
    
    @Override
    public BaseTemplate setStatus( PackageStatus value ){
        // TODO: Figure how to do refinements of validation rules
        super.setStatus(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetStatus( PackageStatus value ){
        if ( value != null ) { this.setStatus( value ); }
        return this;
    }
    
        
    
    @Override
    public BaseTemplate setType( BasePackageType value ){
        super.setType(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetType( BasePackageType value ){
        if ( value != null ) { this.setType( value ); }
        return this;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Override
    public BaseTemplate setUpdated( java.util.Date value ){
        super.setUpdated(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetUpdated( java.util.Date value ){
        if ( value != null ) { this.setUpdated( value ); }
        return this;
    }
    
        
    
    public BaseTemplate setVisibility( Visibility value ){
        SchemaSanitizer.throwOnNull(FIELD_VISIBILITY,value);
        // TODO With proper compare
        // if ( this._visibility == value ) return this;
        this._visibility = value;
        setDirty(FIELD_VISIBILITY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseTemplate safeSetVisibility( Visibility value ){
        if ( value != null ) { this.setVisibility( value ); }
        return this;
    }
    public Visibility getVisibility(){
        return _visibility;
    }
    
    
}