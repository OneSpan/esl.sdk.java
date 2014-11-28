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
public class User extends Entity
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_ADDRESS = "address";
    @JsonIgnore
    public static final String FIELD_COMPANY = "company";
    @JsonIgnore
    public static final String FIELD_CREATED = "created";
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_EMAIL = "email";
    @JsonIgnore
    public static final String FIELD_EXTERNAL = "external";
    @JsonIgnore
    public static final String FIELD_FIRSTNAME = "firstName";
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
    public static final String FIELD_SPECIALTYPES = "specialTypes";
    @JsonIgnore
    public static final String FIELD_TITLE = "title";
    @JsonIgnore
    public static final String FIELD_UPDATED = "updated";
    @JsonIgnore
    public static final String FIELD_USERCUSTOMFIELDS = "userCustomFields";
    
    // Empty Constructor
    public User ( ) {}
    
    // Fields
    protected Address _address = null;
    protected String _company = "";
    protected java.util.Date _created;
    protected String _email = "";
    protected External _external = null;
    protected String _firstName = "";
    protected String _language = "";
    protected String _lastName = "";
    protected String _phone = "";
    protected List<ProfessionalIdentityField> _professionalIdentityFields = new ArrayList<ProfessionalIdentityField>();
    protected SignatureStyle _signature = null;
    protected List<String> _specialTypes = new ArrayList<String>();
    protected String _title = "";
    protected java.util.Date _updated;
    protected List<UserCustomField> _userCustomFields = new ArrayList<UserCustomField>();
    
    // Accessors
        
    
    public User setAddress( Address value ){
        // TODO With proper compare
        // if ( this._address == value ) return this;
        this._address = value;
        setDirty(FIELD_ADDRESS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public User safeSetAddress( Address value ){
        if ( value != null ) { this.setAddress( value ); }
        return this;
    }
    public Address getAddress(){
        return _address;
    }
    
        
    
    public User setCompany( String value ){
        SchemaSanitizer.throwOnNull(FIELD_COMPANY,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._company == value ) return this;
        this._company = value;
        setDirty(FIELD_COMPANY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public User safeSetCompany( String value ){
        if ( value != null ) { this.setCompany( value ); }
        return this;
    }
    public String getCompany(){
        return _company;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public User setCreated( java.util.Date value ){
        SchemaSanitizer.throwOnNull(FIELD_CREATED,value);
        // TODO With proper compare
        // if ( this._created == value ) return this;
        this._created = value;
        setDirty(FIELD_CREATED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public User safeSetCreated( java.util.Date value ){
        if ( value != null ) { this.setCreated( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getCreated(){
        return _created;
    }
    
        
    
    @Override
    public User setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public User safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    public User setEmail( String value ){
        SchemaSanitizer.throwOnNull(FIELD_EMAIL,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._email == value ) return this;
        this._email = value;
        setDirty(FIELD_EMAIL);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public User safeSetEmail( String value ){
        if ( value != null ) { this.setEmail( value ); }
        return this;
    }
    public String getEmail(){
        return _email;
    }
    
        
    
    public User setExternal( External value ){
        // TODO With proper compare
        // if ( this._external == value ) return this;
        this._external = value;
        setDirty(FIELD_EXTERNAL);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public User safeSetExternal( External value ){
        if ( value != null ) { this.setExternal( value ); }
        return this;
    }
    public External getExternal(){
        return _external;
    }
    
        
    
    public User setFirstName( String value ){
        SchemaSanitizer.throwOnNull(FIELD_FIRSTNAME,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._firstName == value ) return this;
        this._firstName = value;
        setDirty(FIELD_FIRSTNAME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public User safeSetFirstName( String value ){
        if ( value != null ) { this.setFirstName( value ); }
        return this;
    }
    public String getFirstName(){
        return _firstName;
    }
    
        
    
    @Override
    public User setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public User safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    public User setLanguage( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._language == value ) return this;
        this._language = value;
        setDirty(FIELD_LANGUAGE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public User safeSetLanguage( String value ){
        if ( value != null ) { this.setLanguage( value ); }
        return this;
    }
    public String getLanguage(){
        return _language;
    }
    
        
    
    public User setLastName( String value ){
        SchemaSanitizer.throwOnNull(FIELD_LASTNAME,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._lastName == value ) return this;
        this._lastName = value;
        setDirty(FIELD_LASTNAME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public User safeSetLastName( String value ){
        if ( value != null ) { this.setLastName( value ); }
        return this;
    }
    public String getLastName(){
        return _lastName;
    }
    
        
    
    @Override
    public User setName( String value ){
        // TODO: Figure how to do refinements of validation rules
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public User safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
        
    
    public User setPhone( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._phone == value ) return this;
        this._phone = value;
        setDirty(FIELD_PHONE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public User safeSetPhone( String value ){
        if ( value != null ) { this.setPhone( value ); }
        return this;
    }
    public String getPhone(){
        return _phone;
    }
    
        
    
    public User setProfessionalIdentityFields( List<ProfessionalIdentityField> value ){
        SchemaSanitizer.throwOnNull(FIELD_PROFESSIONALIDENTITYFIELDS,value);
        // TODO With proper compare
        // if ( this._professionalIdentityFields == value ) return this;
        this._professionalIdentityFields = value;
        setDirty(FIELD_PROFESSIONALIDENTITYFIELDS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public User safeSetProfessionalIdentityFields( List<ProfessionalIdentityField> value ){
        if ( value != null ) { this.setProfessionalIdentityFields( value ); }
        return this;
    }
    public List<ProfessionalIdentityField> getProfessionalIdentityFields(){
        return _professionalIdentityFields;
    }
    // List adder
    public User addProfessionalIdentityField( ProfessionalIdentityField value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._professionalIdentityFields.add(value);
        setDirty(FIELD_PROFESSIONALIDENTITYFIELDS);
        return this;
    }
    
        
    
    public User setSignature( SignatureStyle value ){
        // TODO With proper compare
        // if ( this._signature == value ) return this;
        this._signature = value;
        setDirty(FIELD_SIGNATURE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public User safeSetSignature( SignatureStyle value ){
        if ( value != null ) { this.setSignature( value ); }
        return this;
    }
    public SignatureStyle getSignature(){
        return _signature;
    }
    
        
    
    public User setSpecialTypes( List<String> value ){
        SchemaSanitizer.throwOnNull(FIELD_SPECIALTYPES,value);
        // TODO With proper compare
        // if ( this._specialTypes == value ) return this;
        this._specialTypes = value;
        setDirty(FIELD_SPECIALTYPES);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public User safeSetSpecialTypes( List<String> value ){
        if ( value != null ) { this.setSpecialTypes( value ); }
        return this;
    }
    public List<String> getSpecialTypes(){
        return _specialTypes;
    }
    // List adder
    public User addSpecialType( String value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._specialTypes.add(value);
        setDirty(FIELD_SPECIALTYPES);
        return this;
    }
    
        
    
    public User setTitle( String value ){
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._title == value ) return this;
        this._title = value;
        setDirty(FIELD_TITLE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public User safeSetTitle( String value ){
        if ( value != null ) { this.setTitle( value ); }
        return this;
    }
    public String getTitle(){
        return _title;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public User setUpdated( java.util.Date value ){
        SchemaSanitizer.throwOnNull(FIELD_UPDATED,value);
        // TODO With proper compare
        // if ( this._updated == value ) return this;
        this._updated = value;
        setDirty(FIELD_UPDATED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public User safeSetUpdated( java.util.Date value ){
        if ( value != null ) { this.setUpdated( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getUpdated(){
        return _updated;
    }
    
        
    
    public User setUserCustomFields( List<UserCustomField> value ){
        SchemaSanitizer.throwOnNull(FIELD_USERCUSTOMFIELDS,value);
        // TODO With proper compare
        // if ( this._userCustomFields == value ) return this;
        this._userCustomFields = value;
        setDirty(FIELD_USERCUSTOMFIELDS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public User safeSetUserCustomFields( List<UserCustomField> value ){
        if ( value != null ) { this.setUserCustomFields( value ); }
        return this;
    }
    public List<UserCustomField> getUserCustomFields(){
        return _userCustomFields;
    }
    // List adder
    public User addUserCustomField( UserCustomField value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._userCustomFields.add(value);
        setDirty(FIELD_USERCUSTOMFIELDS);
        return this;
    }
    
    
}