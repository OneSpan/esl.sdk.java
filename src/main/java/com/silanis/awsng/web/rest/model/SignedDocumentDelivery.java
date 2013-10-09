package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class SignedDocumentDelivery extends Model
      implements java.io.Serializable, ISignedDocumentDelivery
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_DESTINATIONS = "destinations";
    @JsonIgnore
    public static final String FIELD_EXCLUDEDDOCUMENTS = "excludedDocuments";
    @JsonIgnore
    public static final String FIELD_FILEPREFIX = "filePrefix";
    @JsonIgnore
    public static final String FIELD_FILESUFFIX = "fileSuffix";
    
    // Empty Constructor
    public SignedDocumentDelivery ( ) {}
    
    // Fields
    protected List<External> _destinations = new ArrayList<External>();
    protected List<Document> _excludedDocuments = new ArrayList<Document>();
    protected String _filePrefix = null;
    protected String _fileSuffix = null;
    
    // Accessors
        
    
    public SignedDocumentDelivery setDestinations( List<External> value ){
        SchemaSanitizer.throwOnNull(FIELD_DESTINATIONS,value);
        // TODO With proper compare
        // if ( this._destinations == value ) return this;
        this._destinations = value;
        setDirty(FIELD_DESTINATIONS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignedDocumentDelivery safeSetDestinations( List<External> value ){
        if ( value != null ) { this.setDestinations( value ); }
        return this;
    }
    public List<External> getDestinations(){
        return _destinations;
    }
    // List adder
    public SignedDocumentDelivery addDestination( External value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._destinations.add(value);
        setDirty(FIELD_DESTINATIONS);
        return this;
    }
    
        
    
    public SignedDocumentDelivery setExcludedDocuments( List<Document> value ){
        SchemaSanitizer.throwOnNull(FIELD_EXCLUDEDDOCUMENTS,value);
        // TODO With proper compare
        // if ( this._excludedDocuments == value ) return this;
        this._excludedDocuments = value;
        setDirty(FIELD_EXCLUDEDDOCUMENTS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignedDocumentDelivery safeSetExcludedDocuments( List<Document> value ){
        if ( value != null ) { this.setExcludedDocuments( value ); }
        return this;
    }
    public List<Document> getExcludedDocuments(){
        return _excludedDocuments;
    }
    // List adder
    public SignedDocumentDelivery addExcludedDocument( Document value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._excludedDocuments.add(value);
        setDirty(FIELD_EXCLUDEDDOCUMENTS);
        return this;
    }
    
        
    
    public SignedDocumentDelivery setFilePrefix( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._filePrefix == value ) return this;
        this._filePrefix = value;
        setDirty(FIELD_FILEPREFIX);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignedDocumentDelivery safeSetFilePrefix( String value ){
        if ( value != null ) { this.setFilePrefix( value ); }
        return this;
    }
    public String getFilePrefix(){
        return _filePrefix;
    }
    
        
    
    public SignedDocumentDelivery setFileSuffix( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._fileSuffix == value ) return this;
        this._fileSuffix = value;
        setDirty(FIELD_FILESUFFIX);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignedDocumentDelivery safeSetFileSuffix( String value ){
        if ( value != null ) { this.setFileSuffix( value ); }
        return this;
    }
    public String getFileSuffix(){
        return _fileSuffix;
    }
    
    
}