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
public class PackageCompletionReport extends Model
      implements java.io.Serializable, IPackageCompletionReport
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_DOCUMENTS = "documents";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_SIGNERS = "signers";
    @JsonIgnore
    public static final String FIELD_STATUS = "status";
    @JsonIgnore
    public static final String FIELD_TRASHED = "trashed";
    @JsonIgnore
    public static final String FIELD_UPDATED = "updated";
    
    // Empty Constructor
    public PackageCompletionReport ( ) {}
    
    // Fields
    protected List<DocumentsCompletionReport> _documents = new ArrayList<DocumentsCompletionReport>();
    protected String _id = "";
    protected String _name = "";
    protected List<SignersCompletionReport> _signers = new ArrayList<SignersCompletionReport>();
    protected PackageStatus _status = PackageStatus.DRAFT;
    protected Boolean _trashed = false;
    protected java.util.Date _updated;
    
    // Accessors
        
    
    public PackageCompletionReport setDocuments( List<DocumentsCompletionReport> value ){
        SchemaSanitizer.throwOnNull(FIELD_DOCUMENTS,value);
        // TODO With proper compare
        // if ( this._documents == value ) return this;
        this._documents = value;
        setDirty(FIELD_DOCUMENTS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public PackageCompletionReport safeSetDocuments( List<DocumentsCompletionReport> value ){
        if ( value != null ) { this.setDocuments( value ); }
        return this;
    }
    public List<DocumentsCompletionReport> getDocuments(){
        return _documents;
    }
    // List adder
    public PackageCompletionReport addDocument( DocumentsCompletionReport value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._documents.add(value);
        setDirty(FIELD_DOCUMENTS);
        return this;
    }
    
        
    
    public PackageCompletionReport setId( String value ){
        SchemaSanitizer.throwOnNull(FIELD_ID,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._id == value ) return this;
        this._id = value;
        setDirty(FIELD_ID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public PackageCompletionReport safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    public String getId(){
        return _id;
    }
    
        
    
    public PackageCompletionReport setName( String value ){
        SchemaSanitizer.throwOnNull(FIELD_NAME,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._name == value ) return this;
        this._name = value;
        setDirty(FIELD_NAME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public PackageCompletionReport safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    public String getName(){
        return _name;
    }
    
        
    
    public PackageCompletionReport setSigners( List<SignersCompletionReport> value ){
        SchemaSanitizer.throwOnNull(FIELD_SIGNERS,value);
        // TODO With proper compare
        // if ( this._signers == value ) return this;
        this._signers = value;
        setDirty(FIELD_SIGNERS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public PackageCompletionReport safeSetSigners( List<SignersCompletionReport> value ){
        if ( value != null ) { this.setSigners( value ); }
        return this;
    }
    public List<SignersCompletionReport> getSigners(){
        return _signers;
    }
    // List adder
    public PackageCompletionReport addSigner( SignersCompletionReport value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._signers.add(value);
        setDirty(FIELD_SIGNERS);
        return this;
    }
    
        
    
    public PackageCompletionReport setStatus( PackageStatus value ){
        SchemaSanitizer.throwOnNull(FIELD_STATUS,value);
        // TODO With proper compare
        // if ( this._status == value ) return this;
        this._status = value;
        setDirty(FIELD_STATUS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public PackageCompletionReport safeSetStatus( PackageStatus value ){
        if ( value != null ) { this.setStatus( value ); }
        return this;
    }
    public PackageStatus getStatus(){
        return _status;
    }
    
        
    
    public PackageCompletionReport setTrashed( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_TRASHED,value);
        // TODO With proper compare
        // if ( this._trashed == value ) return this;
        this._trashed = value;
        setDirty(FIELD_TRASHED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public PackageCompletionReport safeSetTrashed( Boolean value ){
        if ( value != null ) { this.setTrashed( value ); }
        return this;
    }
    public Boolean getTrashed(){
        return _trashed;
    }
    @JsonIgnore
    public boolean evalTrashed(){
        return _trashed == null ? false : _trashed.booleanValue();
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public PackageCompletionReport setUpdated( java.util.Date value ){
        SchemaSanitizer.throwOnNull(FIELD_UPDATED,value);
        // TODO With proper compare
        // if ( this._updated == value ) return this;
        this._updated = value;
        setDirty(FIELD_UPDATED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public PackageCompletionReport safeSetUpdated( java.util.Date value ){
        if ( value != null ) { this.setUpdated( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getUpdated(){
        return _updated;
    }
    
    
}