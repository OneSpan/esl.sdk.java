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
public class Signer extends User
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_ADDRESS = "address";
    @JsonIgnore
    public static final String FIELD_AUTH = "auth";
    @JsonIgnore
    public static final String FIELD_COMPANY = "company";
    @JsonIgnore
    public static final String FIELD_CREATED = "created";
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_DELIVERY = "delivery";
    @JsonIgnore
    public static final String FIELD_EMAIL = "email";
    @JsonIgnore
    public static final String FIELD_EXTERNAL = "external";
    @JsonIgnore
    public static final String FIELD_FIRSTNAME = "firstName";
    @JsonIgnore
    public static final String FIELD_GROUP = "group";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_LANGUAGE = "language";
    @JsonIgnore
    public static final String FIELD_LASTNAME = "lastName";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_PHONE = "phone";
    @JsonIgnore
    public static final String FIELD_PROFESSIONALIDENTITYFIELDS = "professionalIdentityFields";
    @JsonIgnore
    public static final String FIELD_SIGNATURE = "signature";
    @JsonIgnore
    public static final String FIELD_TITLE = "title";
    @JsonIgnore
    public static final String FIELD_UPDATED = "updated";
    @JsonIgnore
    public static final String FIELD_USERCUSTOMFIELDS = "userCustomFields";
    
    // Empty Constructor
    public Signer ( ) {}
    
    // Fields
    protected Auth _auth;
    protected Delivery _delivery;
    protected Group _group = null;
    
    // Accessors
        
    
    @Override
    public Signer setAddress( Address value ){
        super.setAddress(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetAddress( Address value ){
        if ( value != null ) { this.setAddress( value ); }
        return this;
    }
    
        
    
    public Signer setAuth( Auth value ){
        SchemaSanitizer.throwOnNull(FIELD_AUTH,value);
        // TODO With proper compare
        // if ( this._auth == value ) return this;
        this._auth = value;
        setDirty(FIELD_AUTH);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetAuth( Auth value ){
        if ( value != null ) { this.setAuth( value ); }
        return this;
    }
    public Auth getAuth(){
        return _auth;
    }
    
        
    
    @Override
    public Signer setCompany( String value ){
        super.setCompany(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetCompany( String value ){
        if ( value != null ) { this.setCompany( value ); }
        return this;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Override
    public Signer setCreated( java.util.Date value ){
        super.setCreated(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetCreated( java.util.Date value ){
        if ( value != null ) { this.setCreated( value ); }
        return this;
    }
    
        
    
    @Override
    public Signer setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    public Signer setDelivery( Delivery value ){
        SchemaSanitizer.throwOnNull(FIELD_DELIVERY,value);
        // TODO With proper compare
        // if ( this._delivery == value ) return this;
        this._delivery = value;
        setDirty(FIELD_DELIVERY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetDelivery( Delivery value ){
        if ( value != null ) { this.setDelivery( value ); }
        return this;
    }
    public Delivery getDelivery(){
        return _delivery;
    }
    
        
    
    @Override
    public Signer setEmail( String value ){
        super.setEmail(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetEmail( String value ){
        if ( value != null ) { this.setEmail( value ); }
        return this;
    }
    
        
    
    @Override
    public Signer setExternal( External value ){
        super.setExternal(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetExternal( External value ){
        if ( value != null ) { this.setExternal( value ); }
        return this;
    }
    
        
    
    @Override
    public Signer setFirstName( String value ){
        super.setFirstName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetFirstName( String value ){
        if ( value != null ) { this.setFirstName( value ); }
        return this;
    }
    
        
    
    public Signer setGroup( Group value ){
        // TODO With proper compare
        // if ( this._group == value ) return this;
        this._group = value;
        setDirty(FIELD_GROUP);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetGroup( Group value ){
        if ( value != null ) { this.setGroup( value ); }
        return this;
    }
    public Group getGroup(){
        return _group;
    }
    
        
    
    @Override
    public Signer setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    @Override
    public Signer setLanguage( String value ){
        super.setLanguage(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetLanguage( String value ){
        if ( value != null ) { this.setLanguage( value ); }
        return this;
    }
    
        
    
    @Override
    public Signer setLastName( String value ){
        super.setLastName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetLastName( String value ){
        if ( value != null ) { this.setLastName( value ); }
        return this;
    }
    
        
    
    @Override
    public Signer setName( String value ){
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
        
    
    @Override
    public Signer setPhone( String value ){
        super.setPhone(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetPhone( String value ){
        if ( value != null ) { this.setPhone( value ); }
        return this;
    }
    
        
    
    @Override
    public Signer setProfessionalIdentityFields( List<ProfessionalIdentityField> value ){
        super.setProfessionalIdentityFields(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetProfessionalIdentityFields( List<ProfessionalIdentityField> value ){
        if ( value != null ) { this.setProfessionalIdentityFields( value ); }
        return this;
    }
    // List adder
    @Override
    public Signer addProfessionalIdentityField( ProfessionalIdentityField value ){
        super.addProfessionalIdentityField(value);
        return this;
    }
    
        
    
    @Override
    public Signer setSignature( SignatureStyle value ){
        super.setSignature(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetSignature( SignatureStyle value ){
        if ( value != null ) { this.setSignature( value ); }
        return this;
    }
    
        
    
    @Override
    public Signer setTitle( String value ){
        super.setTitle(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetTitle( String value ){
        if ( value != null ) { this.setTitle( value ); }
        return this;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Override
    public Signer setUpdated( java.util.Date value ){
        super.setUpdated(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetUpdated( java.util.Date value ){
        if ( value != null ) { this.setUpdated( value ); }
        return this;
    }
    
        
    
    @Override
    public Signer setUserCustomFields( List<UserCustomField> value ){
        super.setUserCustomFields(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Signer safeSetUserCustomFields( List<UserCustomField> value ){
        if ( value != null ) { this.setUserCustomFields( value ); }
        return this;
    }
    // List adder
    @Override
    public Signer addUserCustomField( UserCustomField value ){
        super.addUserCustomField(value);
        return this;
    }
    
    
}