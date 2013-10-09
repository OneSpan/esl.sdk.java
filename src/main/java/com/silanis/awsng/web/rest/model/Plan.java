package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Plan extends Entity
      implements java.io.Serializable, IPlan
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CONTRACT = "contract";
    @JsonIgnore
    public static final String FIELD_CYCLE = "cycle";
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_DESCRIPTION = "description";
    @JsonIgnore
    public static final String FIELD_FEATURES = "features";
    @JsonIgnore
    public static final String FIELD_FREECYCLES = "freeCycles";
    @JsonIgnore
    public static final String FIELD_GROUP = "group";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_ORIGINAL = "original";
    @JsonIgnore
    public static final String FIELD_PRICE = "price";
    @JsonIgnore
    public static final String FIELD_QUOTAS = "quotas";
    
    // Empty Constructor
    public Plan ( ) {}
    
    // Fields
    protected Cycle _contract = null;
    protected Cycle _cycle = Cycle.DAY;
    protected String _description = "";
    protected Map<String, Object> _features = null;
    protected CycleCount _freeCycles = null;
    protected String _group = "";
    protected String _original = null;
    protected Price _price;
    protected List<Quota> _quotas = new ArrayList<Quota>();
    
    // Accessors
        
    
    public Plan setContract( Cycle value ){
        // TODO With proper compare
        // if ( this._contract == value ) return this;
        this._contract = value;
        setDirty(FIELD_CONTRACT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Plan safeSetContract( Cycle value ){
        if ( value != null ) { this.setContract( value ); }
        return this;
    }
    public Cycle getContract(){
        return _contract;
    }
    
        
    
    public Plan setCycle( Cycle value ){
        SchemaSanitizer.throwOnNull(FIELD_CYCLE,value);
        // TODO With proper compare
        // if ( this._cycle == value ) return this;
        this._cycle = value;
        setDirty(FIELD_CYCLE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Plan safeSetCycle( Cycle value ){
        if ( value != null ) { this.setCycle( value ); }
        return this;
    }
    public Cycle getCycle(){
        return _cycle;
    }
    
        
    
    @Override
    public Plan setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Plan safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    public Plan setDescription( String value ){
        SchemaSanitizer.throwOnNull(FIELD_DESCRIPTION,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._description == value ) return this;
        this._description = value;
        setDirty(FIELD_DESCRIPTION);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Plan safeSetDescription( String value ){
        if ( value != null ) { this.setDescription( value ); }
        return this;
    }
    public String getDescription(){
        return _description;
    }
    
        
    
    public Plan setFeatures( Map<String, Object> value ){
        // TODO With proper compare
        // if ( this._features == value ) return this;
        this._features = value;
        setDirty(FIELD_FEATURES);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Plan safeSetFeatures( Map<String, Object> value ){
        if ( value != null ) { this.setFeatures( value ); }
        return this;
    }
    public Map<String, Object> getFeatures(){
        return _features;
    }
    
        
    
    public Plan setFreeCycles( CycleCount value ){
        // TODO With proper compare
        // if ( this._freeCycles == value ) return this;
        this._freeCycles = value;
        setDirty(FIELD_FREECYCLES);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Plan safeSetFreeCycles( CycleCount value ){
        if ( value != null ) { this.setFreeCycles( value ); }
        return this;
    }
    public CycleCount getFreeCycles(){
        return _freeCycles;
    }
    
        
    
    public Plan setGroup( String value ){
        SchemaSanitizer.throwOnNull(FIELD_GROUP,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._group == value ) return this;
        this._group = value;
        setDirty(FIELD_GROUP);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Plan safeSetGroup( String value ){
        if ( value != null ) { this.setGroup( value ); }
        return this;
    }
    public String getGroup(){
        return _group;
    }
    
        
    
    @Override
    public Plan setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Plan safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    @Override
    public Plan setName( String value ){
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Plan safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
        
    
    public Plan setOriginal( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._original == value ) return this;
        this._original = value;
        setDirty(FIELD_ORIGINAL);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Plan safeSetOriginal( String value ){
        if ( value != null ) { this.setOriginal( value ); }
        return this;
    }
    public String getOriginal(){
        return _original;
    }
    
        
    
    public Plan setPrice( Price value ){
        SchemaSanitizer.throwOnNull(FIELD_PRICE,value);
        // TODO With proper compare
        // if ( this._price == value ) return this;
        this._price = value;
        setDirty(FIELD_PRICE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Plan safeSetPrice( Price value ){
        if ( value != null ) { this.setPrice( value ); }
        return this;
    }
    public Price getPrice(){
        return _price;
    }
    
        
    
    public Plan setQuotas( List<Quota> value ){
        SchemaSanitizer.throwOnNull(FIELD_QUOTAS,value);
        // TODO With proper compare
        // if ( this._quotas == value ) return this;
        this._quotas = value;
        setDirty(FIELD_QUOTAS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Plan safeSetQuotas( List<Quota> value ){
        if ( value != null ) { this.setQuotas( value ); }
        return this;
    }
    public List<Quota> getQuotas(){
        return _quotas;
    }
    // List adder
    public Plan addQuota( Quota value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._quotas.add(value);
        setDirty(FIELD_QUOTAS);
        return this;
    }
    
    
}