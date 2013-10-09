package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Price extends Model
      implements java.io.Serializable, IPrice
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_AMOUNT = "amount";
    @JsonIgnore
    public static final String FIELD_CURRENCY = "currency";
    
    // Empty Constructor
    public Price ( ) {}
    
    // Fields
    protected Integer _amount = 0;
    protected Currency _currency;
    
    // Accessors
        
    
    public Price setAmount( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_AMOUNT,value);
        // TODO With proper compare
        // if ( this._amount == value ) return this;
        this._amount = value;
        setDirty(FIELD_AMOUNT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Price safeSetAmount( Integer value ){
        if ( value != null ) { this.setAmount( value ); }
        return this;
    }
    public Integer getAmount(){
        return _amount;
    }
    
        
    
    public Price setCurrency( Currency value ){
        SchemaSanitizer.throwOnNull(FIELD_CURRENCY,value);
        // TODO With proper compare
        // if ( this._currency == value ) return this;
        this._currency = value;
        setDirty(FIELD_CURRENCY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Price safeSetCurrency( Currency value ){
        if ( value != null ) { this.setCurrency( value ); }
        return this;
    }
    public Currency getCurrency(){
        return _currency;
    }
    
    
}