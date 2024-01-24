package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Sets;
import com.silanis.esl.api.util.SchemaSanitizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Document extends Entity
        implements java.io.Serializable {

    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_APPROVALS = "approvals";
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_DESCRIPTION = "description";
    @JsonIgnore
    public static final String FIELD_EXTERNAL = "external";
    @JsonIgnore
    public static final String FIELD_EXTRACT = "extract";
    @JsonIgnore
    public static final String FIELD_EXTRACTION_TYPES = "extractionTypes";
    @JsonIgnore
    public static final String FIELD_FIELDS = "fields";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_INDEX = "index";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_PAGES = "pages";
    @JsonIgnore
    public static final String FIELD_SIZE = "size";
    @JsonIgnore
    public static final String FIELD_STATUS = "status";
    @JsonIgnore
    public static final String FIELD_SIGNED_HASH = "signedHash";
    @JsonIgnore
    public static final String FIELD_SIGNER_VERIFICATION_TOKEN = "signerVerificationToken";
    @JsonIgnore
    public static final String FIELD_TAGGED = "tagged";
    @JsonIgnore
    public static final String FIELD_EXTERNAL_SIGNED = "externalSigned";
    @JsonIgnore
    public static final String FIELD_BASE64_CONTENT = "base64Content";

    // Empty Constructor
    public Document() {
    }

    private String status = "";

    // Fields
    protected List<Approval> _approvals = new ArrayList<Approval>();
    protected String _description = "";
    protected External _external = null;
    protected Boolean _extract = false;
    protected Set<String> _extractionTypes = Sets.newHashSet();
    protected List<Field> _fields = new ArrayList<Field>();
    protected Integer _index = 0;
    protected List<Page> _pages = new ArrayList<Page>();
    protected Integer _size = 0;
    protected String _signedHash;
    protected String _signerVerificationToken;
    protected Boolean _tagged = false;
    protected Boolean _externalSigned = false;
    protected String _base64Content;

    // Accessors


    public Document setStatus(String value) {
        value = SchemaSanitizer.trim(value);
        this.status = value;
        setDirty(FIELD_STATUS);
        return this;
    }

    public String getStatus() {
        return status;
    }


    public Document setApprovals(List<Approval> value) {
        SchemaSanitizer.throwOnNull(FIELD_APPROVALS, value);
        // TODO With proper compare
        // if ( this._approvals == value ) return this;
        this._approvals = value;
        setDirty(FIELD_APPROVALS);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Document safeSetApprovals(List<Approval> value) {
        if (value != null) {
            this.setApprovals(value);
        }
        return this;
    }

    public List<Approval> getApprovals() {
        return _approvals;
    }

    // List adder
    public Document addApproval(Approval value) {
        if (value == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        this._approvals.add(value);
        setDirty(FIELD_APPROVALS);
        return this;
    }


    @Override
    public Document setData(Map<String, Object> value) {
        super.setData(value);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Document safeSetData(Map<String, Object> value) {
        if (value != null) {
            this.setData(value);
        }
        return this;
    }


    public Document setDescription(String value) {
        SchemaSanitizer.throwOnNull(FIELD_DESCRIPTION, value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._description == value ) return this;
        this._description = value;
        setDirty(FIELD_DESCRIPTION);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Document safeSetDescription(String value) {
        if (value != null) {
            this.setDescription(value);
        }
        return this;
    }

    public String getDescription() {
        return _description;
    }


    public Document setExternal(External value) {
        // TODO With proper compare
        // if ( this._external == value ) return this;
        this._external = value;
        setDirty(FIELD_EXTERNAL);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Document safeSetExternal(External value) {
        if (value != null) {
            this.setExternal(value);
        }
        return this;
    }

    public External getExternal() {
        return _external;
    }


    public Document setExtract(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_EXTRACT, value);
        // TODO With proper compare
        // if ( this._extract == value ) return this;
        this._extract = value;
        setDirty(FIELD_EXTRACT);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Document safeSetExtract(Boolean value) {
        if (value != null) {
            this.setExtract(value);
        }
        return this;
    }

    public Boolean getExtract() {
        return _extract;
    }

    @JsonIgnore
    public boolean evalExtract() {
        return _extract == null ? false : _extract.booleanValue();
    }


    public Document setExtractionTypes(Set<String> value) {
        SchemaSanitizer.throwOnNull(FIELD_EXTRACTION_TYPES, value);
        // TODO With proper compare
        // if ( this._extractionTypes == value ) return this;
        this._extractionTypes = value;
        setDirty(FIELD_EXTRACTION_TYPES);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Document safeSetExtractionTypes(Set<String> value) {
        if (value != null) {
            this.setExtractionTypes(value);
        }
        return this;
    }

    public Set<String> getExtractionTypes() {
        return _extractionTypes;
    }


    public Document setFields(List<Field> value) {
        SchemaSanitizer.throwOnNull(FIELD_FIELDS, value);
        // TODO With proper compare
        // if ( this._fields == value ) return this;
        this._fields = value;
        setDirty(FIELD_FIELDS);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Document safeSetFields(List<Field> value) {
        if (value != null) {
            this.setFields(value);
        }
        return this;
    }

    public List<Field> getFields() {
        return _fields;
    }

    // List adder
    public Document addField(Field value) {
        if (value == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        this._fields.add(value);
        setDirty(FIELD_FIELDS);
        return this;
    }


    @Override
    public Document setId(String value) {
        super.setId(value);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Document safeSetId(String value) {
        if (value != null) {
            this.setId(value);
        }
        return this;
    }


    public Document setIndex(Integer value) {
        SchemaSanitizer.throwOnNull(FIELD_INDEX, value);
        // TODO With proper compare
        // if ( this._index == value ) return this;
        this._index = value;
        setDirty(FIELD_INDEX);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Document safeSetIndex(Integer value) {
        if (value != null) {
            this.setIndex(value);
        }
        return this;
    }

    public Integer getIndex() {
        return _index;
    }


    @Override
    public Document setName(String value) {
        super.setName(value);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Document safeSetName(String value) {
        if (value != null) {
            this.setName(value);
        }
        return this;
    }


    public Document setPages(List<Page> value) {
        SchemaSanitizer.throwOnNull(FIELD_PAGES, value);
        // TODO With proper compare
        // if ( this._pages == value ) return this;
        this._pages = value;
        setDirty(FIELD_PAGES);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Document safeSetPages(List<Page> value) {
        if (value != null) {
            this.setPages(value);
        }
        return this;
    }

    public List<Page> getPages() {
        return _pages;
    }

    // List adder
    public Document addPage(Page value) {
        if (value == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        this._pages.add(value);
        setDirty(FIELD_PAGES);
        return this;
    }


    public Document setSize(Integer value) {
        SchemaSanitizer.throwOnNull(FIELD_SIZE, value);
        // TODO With proper compare
        // if ( this._size == value ) return this;
        this._size = value;
        setDirty(FIELD_SIZE);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Document safeSetSize(Integer value) {
        if (value != null) {
            this.setSize(value);
        }
        return this;
    }

    public Integer getSize() {
        return _size;
    }


    public Document setSignedHash(String value) {
        this._signedHash = value;
        setDirty(FIELD_SIGNED_HASH);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Document safeSetSignedHash(String value) {
        if (value != null) {
            this.setSignedHash(value);
        }
        return this;
    }

    public String getSignedHash() {
        return _signedHash;
    }


    public Document setSignerVerificationToken(String value) {
        this._signerVerificationToken = value;
        setDirty(FIELD_SIGNER_VERIFICATION_TOKEN);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Document safeSetSignerVerificationToken(String value) {
        if (value != null) {
            this.setSignerVerificationToken(value);
        }
        return this;
    }

    public String getSignerVerificationToken() {
        return _signerVerificationToken;
    }


    public Document setTagged(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_TAGGED, value);
        this._tagged = value;
        setDirty(FIELD_TAGGED);
        return this;
    }

    @JsonIgnore
    public Document safeSetTagged(Boolean value) {
        if (value != null) {
            this.setTagged(value);
        }
        return this;
    }

    public Boolean getTagged() {
        return _tagged;
    }

    @JsonIgnore
    public boolean evalTagged() {
        return _tagged == null ? false : _tagged.booleanValue();
    }

    public Boolean isExternalSigned() { return _externalSigned; }

    public Document setExternalSigned(Boolean externalSigned) {
        SchemaSanitizer.throwOnNull(FIELD_EXTERNAL_SIGNED, externalSigned);
        this._externalSigned = externalSigned;
        setDirty(FIELD_EXTERNAL_SIGNED);
        return this;
    }

    @JsonIgnore
    public Document safeSetExternalSigned(Boolean externalSigned) {
        if (externalSigned != null) {
            this.setExternalSigned(externalSigned);
        }
        return this;
    }

    public String getBase64Content() {
        return _base64Content;
    }

    public Document setBase64Content(String value) {
        this._base64Content = value;
        setDirty(FIELD_BASE64_CONTENT);
        return this;
    }

    @JsonIgnore
    public Document safeSetBase64Content(String value) {
        if (value != null) {
            this.setBase64Content(value);
        }
        return this;
    }
}