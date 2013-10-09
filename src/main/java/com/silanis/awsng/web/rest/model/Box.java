package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Box extends Model
      implements java.io.Serializable, IBox
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_HEIGHT = "height";
    @JsonIgnore
    public static final String FIELD_LEFT = "left";
    @JsonIgnore
    public static final String FIELD_TOP = "top";
    @JsonIgnore
    public static final String FIELD_WIDTH = "width";
    
    // Empty Constructor
    public Box ( ) {}
    
    // Fields
    protected Double _height = 0.0;
    protected Double _left = 0.0;
    protected Double _top = 0.0;
    protected Double _width = 0.0;
    
    // Accessors
        
    
    public Box setHeight( Double value ){
        // TODO With proper compare
        // if ( this._height == value ) return this;
        this._height = value;
        setDirty(FIELD_HEIGHT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Box safeSetHeight( Double value ){
        if ( value != null ) { this.setHeight( value ); }
        return this;
    }
    public Double getHeight(){
        return _height;
    }
    
        
    
    public Box setLeft( Double value ){
        // TODO With proper compare
        // if ( this._left == value ) return this;
        this._left = value;
        setDirty(FIELD_LEFT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Box safeSetLeft( Double value ){
        if ( value != null ) { this.setLeft( value ); }
        return this;
    }
    public Double getLeft(){
        return _left;
    }
    
        
    
    public Box setTop( Double value ){
        // TODO With proper compare
        // if ( this._top == value ) return this;
        this._top = value;
        setDirty(FIELD_TOP);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Box safeSetTop( Double value ){
        if ( value != null ) { this.setTop( value ); }
        return this;
    }
    public Double getTop(){
        return _top;
    }
    
        
    
    public Box setWidth( Double value ){
        // TODO With proper compare
        // if ( this._width == value ) return this;
        this._width = value;
        setDirty(FIELD_WIDTH);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Box safeSetWidth( Double value ){
        if ( value != null ) { this.setWidth( value ); }
        return this;
    }
    public Double getWidth(){
        return _width;
    }
    
    
}