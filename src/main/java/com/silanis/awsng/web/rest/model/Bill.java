package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Bill extends Model
      implements java.io.Serializable, IBill
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_ADDRESS = "address";
    @JsonIgnore
    public static final String FIELD_CREDITCARD = "creditCard";
    @JsonIgnore
    public static final String FIELD_PHONE = "phone";
    @JsonIgnore
    public static final String FIELD_PLAN = "plan";
    @JsonIgnore
    public static final String FIELD_SENDERQUANTITY = "senderQuantity";
    
    // Empty Constructor
    public Bill ( ) {}
    
    // Fields
    protected Address _address = null;
    protected CreditCard _creditCard = null;
    protected String _phone = "";
    protected String _plan = "";
    protected Integer _senderQuantity = 1;
    
    // Accessors
        
    
    public Bill setAddress( Address value ){
        // TODO With proper compare
        // if ( this._address == value ) return this;
        this._address = value;
        setDirty(FIELD_ADDRESS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Bill safeSetAddress( Address value ){
        if ( value != null ) { this.setAddress( value ); }
        return this;
    }
    public Address getAddress(){
        return _address;
    }
    
        
    
    public Bill setCreditCard( CreditCard value ){
        // TODO With proper compare
        // if ( this._creditCard == value ) return this;
        this._creditCard = value;
        setDirty(FIELD_CREDITCARD);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Bill safeSetCreditCard( CreditCard value ){
        if ( value != null ) { this.setCreditCard( value ); }
        return this;
    }
    public CreditCard getCreditCard(){
        return _creditCard;
    }
    
        
    
    public Bill setPhone( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._phone == value ) return this;
        this._phone = value;
        setDirty(FIELD_PHONE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Bill safeSetPhone( String value ){
        if ( value != null ) { this.setPhone( value ); }
        return this;
    }
    public String getPhone(){
        return _phone;
    }
    
        
    
    public Bill setPlan( String value ){
        SchemaSanitizer.throwOnNull(FIELD_PLAN,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._plan == value ) return this;
        this._plan = value;
        setDirty(FIELD_PLAN);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Bill safeSetPlan( String value ){
        if ( value != null ) { this.setPlan( value ); }
        return this;
    }
    public String getPlan(){
        return _plan;
    }
    
        
    
    public Bill setSenderQuantity( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_SENDERQUANTITY,value);
        // TODO With proper compare
        // if ( this._senderQuantity == value ) return this;
        this._senderQuantity = value;
        setDirty(FIELD_SENDERQUANTITY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Bill safeSetSenderQuantity( Integer value ){
        if ( value != null ) { this.setSenderQuantity( value ); }
        return this;
    }
    public Integer getSenderQuantity(){
        return _senderQuantity;
    }
    
    
}