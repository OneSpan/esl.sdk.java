package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.JsonDateDeserializer;
import com.silanis.awsng.web.rest.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Transaction extends Model
      implements java.io.Serializable, ITransaction
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CREATED = "created";
    @JsonIgnore
    public static final String FIELD_CREDITCARD = "creditCard";
    @JsonIgnore
    public static final String FIELD_PRICE = "price";
    
    // Empty Constructor
    public Transaction ( ) {}
    
    // Fields
    protected java.util.Date _created;
    protected CreditCard _creditCard;
    protected Price _price;
    
    // Accessors
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Transaction setCreated( java.util.Date value ){
        SchemaSanitizer.throwOnNull(FIELD_CREATED,value);
        // TODO With proper compare
        // if ( this._created == value ) return this;
        this._created = value;
        setDirty(FIELD_CREATED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Transaction safeSetCreated( java.util.Date value ){
        if ( value != null ) { this.setCreated( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getCreated(){
        return _created;
    }
    
        
    
    public Transaction setCreditCard( CreditCard value ){
        SchemaSanitizer.throwOnNull(FIELD_CREDITCARD,value);
        // TODO With proper compare
        // if ( this._creditCard == value ) return this;
        this._creditCard = value;
        setDirty(FIELD_CREDITCARD);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Transaction safeSetCreditCard( CreditCard value ){
        if ( value != null ) { this.setCreditCard( value ); }
        return this;
    }
    public CreditCard getCreditCard(){
        return _creditCard;
    }
    
        
    
    public Transaction setPrice( Price value ){
        SchemaSanitizer.throwOnNull(FIELD_PRICE,value);
        // TODO With proper compare
        // if ( this._price == value ) return this;
        this._price = value;
        setDirty(FIELD_PRICE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Transaction safeSetPrice( Price value ){
        if ( value != null ) { this.setPrice( value ); }
        return this;
    }
    public Price getPrice(){
        return _price;
    }
    
    
}