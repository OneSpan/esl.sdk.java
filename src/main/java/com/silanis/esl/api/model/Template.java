package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.Map;
import com.silanis.esl.api.util.JsonDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Template extends BaseTemplate
      implements java.io.Serializable, ITemplate
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
    public Template ( ) {}
    
    // Fields
    
    // Accessors
        
    
    @Override
    public Template setAutocomplete( Boolean value ){
        super.setAutocomplete(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetAutocomplete( Boolean value ){
        if ( value != null ) { this.setAutocomplete( value ); }
        return this;
    }
    
        
    
    @Override
    public Template setConsent( String value ){
        super.setConsent(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetConsent( String value ){
        if ( value != null ) { this.setConsent( value ); }
        return this;
    }
    
        
    
    @Override
    public Template setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    @Override
    public Template setDescription( String value ){
        super.setDescription(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetDescription( String value ){
        if ( value != null ) { this.setDescription( value ); }
        return this;
    }
    
        
    
    @Override
    public Template setDocuments( List<Document> value ){
        super.setDocuments(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetDocuments( List<Document> value ){
        if ( value != null ) { this.setDocuments( value ); }
        return this;
    }
    // List adder
    @Override
    public Template addDocument( Document value ){
        super.addDocument(value);
        return this;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Override
    public Template setDue( java.util.Date value ){
        super.setDue(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetDue( java.util.Date value ){
        if ( value != null ) { this.setDue( value ); }
        return this;
    }
    
        
    
    @Override
    public Template setEmailMessage( String value ){
        super.setEmailMessage(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetEmailMessage( String value ){
        if ( value != null ) { this.setEmailMessage( value ); }
        return this;
    }
    
        
    
    @Override
    public Template setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    @Override
    public Template setLanguage( String value ){
        super.setLanguage(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetLanguage( String value ){
        if ( value != null ) { this.setLanguage( value ); }
        return this;
    }
    
        
    
    @Override
    public Template setLimits( PackageArtifactsLimits value ){
        super.setLimits(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetLimits( PackageArtifactsLimits value ){
        if ( value != null ) { this.setLimits( value ); }
        return this;
    }
    
        
    
    @Override
    public Template setMessages( List<Message> value ){
        super.setMessages(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetMessages( List<Message> value ){
        if ( value != null ) { this.setMessages( value ); }
        return this;
    }
    // List adder
    @Override
    public Template addMessage( Message value ){
        super.addMessage(value);
        return this;
    }
    
        
    
    @Override
    public Template setName( String value ){
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
        
    
    @Override
    public Template setRoles( List<Role> value ){
        super.setRoles(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetRoles( List<Role> value ){
        if ( value != null ) { this.setRoles( value ); }
        return this;
    }
    // List adder
    @Override
    public Template addRole( Role value ){
        super.addRole(value);
        return this;
    }
    
        
    
    @Override
    public Template setSender( Sender value ){
        super.setSender(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetSender( Sender value ){
        if ( value != null ) { this.setSender( value ); }
        return this;
    }
    
        
    
    @Override
    public Template setSettings( PackageSettings value ){
        super.setSettings(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetSettings( PackageSettings value ){
        if ( value != null ) { this.setSettings( value ); }
        return this;
    }
    
        
    
    @Override
    public Template setSignedDocumentDelivery( SignedDocumentDelivery value ){
        super.setSignedDocumentDelivery(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetSignedDocumentDelivery( SignedDocumentDelivery value ){
        if ( value != null ) { this.setSignedDocumentDelivery( value ); }
        return this;
    }
    
        
    
    @Override
    public Template setStatus( PackageStatus value ){
        super.setStatus(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetStatus( PackageStatus value ){
        if ( value != null ) { this.setStatus( value ); }
        return this;
    }
    
        
    
    @Override
    public Template setType( BasePackageType value ){
        super.setType(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetType( BasePackageType value ){
        if ( value != null ) { this.setType( value ); }
        return this;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Override
    public Template setUpdated( java.util.Date value ){
        super.setUpdated(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetUpdated( java.util.Date value ){
        if ( value != null ) { this.setUpdated( value ); }
        return this;
    }
    
        
    
    @Override
    public Template setVisibility( Visibility value ){
        super.setVisibility(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Template safeSetVisibility( Visibility value ){
        if ( value != null ) { this.setVisibility( value ); }
        return this;
    }
    
    
}