package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silanis.esl.api.util.JsonDateDeserializer;
import com.silanis.esl.api.util.SchemaSanitizer;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AttachmentFile extends Model implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_INSERT_DATE = "insertDate";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_PREVIEW = "preview";

    protected Integer id;
    protected Date insertDate;
    protected String name;
    protected boolean preview;


    public AttachmentFile setId(Integer value) {
        SchemaSanitizer.throwOnNull(FIELD_ID, value);

        if (value.equals(this.id)) return this;

        this.id = value;
        setDirty(FIELD_ID);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public AttachmentFile setInsertDate(long value) {
        SchemaSanitizer.throwOnNull(FIELD_INSERT_DATE, value);

        Date newDate = new Date(value);
        if (newDate.equals(this.insertDate)) return this;

        this.insertDate = newDate;
        setDirty(FIELD_INSERT_DATE);
        return this;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public AttachmentFile setName(String value) {
        SchemaSanitizer.throwOnNull(FIELD_NAME, value);

        if (value.equals(this.name)) return this;

        this.name = value;
        setDirty(FIELD_NAME);
        return this;
    }

    public String getName() {
        return name;
    }

    public AttachmentFile setPreview(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_PREVIEW, value);

        if (value.equals(this.preview)) return this;

        this.preview = value;
        setDirty(FIELD_PREVIEW);
        return this;
    }

    public boolean isPreview() {
        return preview;
    }

    @Override
    @JsonIgnore
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttachmentFile that = (AttachmentFile) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (insertDate != null ? !insertDate.equals(that.insertDate) : that.insertDate != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    @JsonIgnore
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (insertDate != null ? insertDate.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
