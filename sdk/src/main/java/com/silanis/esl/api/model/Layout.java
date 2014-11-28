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
public class Layout extends BaseTemplate
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
    
    // Empty Constructor
    public Layout ( ) {}
    
    // Fields
    
    // Accessors
        
    
    @Override
    public Layout setAutocomplete( Boolean value ){
        super.setAutocomplete(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetAutocomplete( Boolean value ){
        if ( value != null ) { this.setAutocomplete( value ); }
        return this;
    }
    
        
    
    @Override
    public Layout setConsent( String value ){
        super.setConsent(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetConsent( String value ){
        if ( value != null ) { this.setConsent( value ); }
        return this;
    }
    
        
    
    @Override
    public Layout setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    @Override
    public Layout setDescription( String value ){
        super.setDescription(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetDescription( String value ){
        if ( value != null ) { this.setDescription( value ); }
        return this;
    }
    
        
    
    @Override
    public Layout setDocuments( List<Document> value ){
        // TODO: Figure how to do refinements of validation rules
        super.setDocuments(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetDocuments( List<Document> value ){
        if ( value != null ) { this.setDocuments( value ); }
        return this;
    }
    // List adder
    @Override
    public Layout addDocument( Document value ){
        super.addDocument(value);
        return this;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Override
    public Layout setDue( java.util.Date value ){
        super.setDue(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetDue( java.util.Date value ){
        if ( value != null ) { this.setDue( value ); }
        return this;
    }
    
        
    
    @Override
    public Layout setEmailMessage( String value ){
        super.setEmailMessage(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetEmailMessage( String value ){
        if ( value != null ) { this.setEmailMessage( value ); }
        return this;
    }
    
        
    
    @Override
    public Layout setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    @Override
    public Layout setLanguage( String value ){
        super.setLanguage(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetLanguage( String value ){
        if ( value != null ) { this.setLanguage( value ); }
        return this;
    }
    
        
    
    @Override
    public Layout setLimits( PackageArtifactsLimits value ){
        super.setLimits(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetLimits( PackageArtifactsLimits value ){
        if ( value != null ) { this.setLimits( value ); }
        return this;
    }
    
        
    
    @Override
    public Layout setMessages( List<Message> value ){
        super.setMessages(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetMessages( List<Message> value ){
        if ( value != null ) { this.setMessages( value ); }
        return this;
    }
    // List adder
    @Override
    public Layout addMessage( Message value ){
        super.addMessage(value);
        return this;
    }
    
        
    
    @Override
    public Layout setName( String value ){
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
        
    
    @Override
    public Layout setNotarized( Boolean value ){
        super.setNotarized(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetNotarized( Boolean value ){
        if ( value != null ) { this.setNotarized( value ); }
        return this;
    }
    
        
    
    @Override
    public Layout setNotaryRoleId( String value ){
        super.setNotaryRoleId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetNotaryRoleId( String value ){
        if ( value != null ) { this.setNotaryRoleId( value ); }
        return this;
    }
    
        
    
    @Override
    public Layout setRoles( List<Role> value ){
        super.setRoles(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetRoles( List<Role> value ){
        if ( value != null ) { this.setRoles( value ); }
        return this;
    }
    // List adder
    @Override
    public Layout addRole( Role value ){
        super.addRole(value);
        return this;
    }
    
        
    
    @Override
    public Layout setSender( Sender value ){
        super.setSender(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetSender( Sender value ){
        if ( value != null ) { this.setSender( value ); }
        return this;
    }
    
        
    
    @Override
    public Layout setSettings( PackageSettings value ){
        super.setSettings(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetSettings( PackageSettings value ){
        if ( value != null ) { this.setSettings( value ); }
        return this;
    }
    
        
    
    @Override
    public Layout setSignedDocumentDelivery( SignedDocumentDelivery value ){
        super.setSignedDocumentDelivery(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetSignedDocumentDelivery( SignedDocumentDelivery value ){
        if ( value != null ) { this.setSignedDocumentDelivery( value ); }
        return this;
    }
    
        
    
    @Override
    public Layout setStatus( PackageStatus value ){
        super.setStatus(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetStatus( PackageStatus value ){
        if ( value != null ) { this.setStatus( value ); }
        return this;
    }
    
        
    
    @Override
    public Layout setType( BasePackageType value ){
        super.setType(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetType( BasePackageType value ){
        if ( value != null ) { this.setType( value ); }
        return this;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Override
    public Layout setUpdated( java.util.Date value ){
        super.setUpdated(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetUpdated( java.util.Date value ){
        if ( value != null ) { this.setUpdated( value ); }
        return this;
    }
    
        
    
    @Override
    public Layout setVisibility( Visibility value ){
        super.setVisibility(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Layout safeSetVisibility( Visibility value ){
        if ( value != null ) { this.setVisibility( value ); }
        return this;
    }
    
    
}