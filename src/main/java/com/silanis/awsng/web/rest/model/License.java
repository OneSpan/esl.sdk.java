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
public class License extends Model
      implements java.io.Serializable, ILicense
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CREATED = "created";
    @JsonIgnore
    public static final String FIELD_PAIDUNTIL = "paidUntil";
    @JsonIgnore
    public static final String FIELD_PLAN = "plan";
    @JsonIgnore
    public static final String FIELD_STATUS = "status";
    @JsonIgnore
    public static final String FIELD_TRANSACTIONS = "transactions";
    
    // Empty Constructor
    public License ( ) {}
    
    // Fields
    protected java.util.Date _created;
    protected java.util.Date _paidUntil = null;
    protected Plan _plan;
    protected LicenseStatus _status = LicenseStatus.ACTIVE;
    protected List<Transaction> _transactions = new ArrayList<Transaction>();
    
    // Accessors
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public License setCreated( java.util.Date value ){
        SchemaSanitizer.throwOnNull(FIELD_CREATED,value);
        // TODO With proper compare
        // if ( this._created == value ) return this;
        this._created = value;
        setDirty(FIELD_CREATED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public License safeSetCreated( java.util.Date value ){
        if ( value != null ) { this.setCreated( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getCreated(){
        return _created;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public License setPaidUntil( java.util.Date value ){
        // TODO With proper compare
        // if ( this._paidUntil == value ) return this;
        this._paidUntil = value;
        setDirty(FIELD_PAIDUNTIL);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public License safeSetPaidUntil( java.util.Date value ){
        if ( value != null ) { this.setPaidUntil( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getPaidUntil(){
        return _paidUntil;
    }
    
        
    
    public License setPlan( Plan value ){
        SchemaSanitizer.throwOnNull(FIELD_PLAN,value);
        // TODO With proper compare
        // if ( this._plan == value ) return this;
        this._plan = value;
        setDirty(FIELD_PLAN);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public License safeSetPlan( Plan value ){
        if ( value != null ) { this.setPlan( value ); }
        return this;
    }
    public Plan getPlan(){
        return _plan;
    }
    
        
    
    public License setStatus( LicenseStatus value ){
        SchemaSanitizer.throwOnNull(FIELD_STATUS,value);
        // TODO With proper compare
        // if ( this._status == value ) return this;
        this._status = value;
        setDirty(FIELD_STATUS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public License safeSetStatus( LicenseStatus value ){
        if ( value != null ) { this.setStatus( value ); }
        return this;
    }
    public LicenseStatus getStatus(){
        return _status;
    }
    
        
    
    public License setTransactions( List<Transaction> value ){
        SchemaSanitizer.throwOnNull(FIELD_TRANSACTIONS,value);
        // TODO With proper compare
        // if ( this._transactions == value ) return this;
        this._transactions = value;
        setDirty(FIELD_TRANSACTIONS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public License safeSetTransactions( List<Transaction> value ){
        if ( value != null ) { this.setTransactions( value ); }
        return this;
    }
    public List<Transaction> getTransactions(){
        return _transactions;
    }
    // List adder
    public License addTransaction( Transaction value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._transactions.add(value);
        setDirty(FIELD_TRANSACTIONS);
        return this;
    }
    
    
}