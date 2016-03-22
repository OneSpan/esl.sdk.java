package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonValue;
import com.silanis.esl.api.util.JacksonUtil;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class SessionFields {

    public static final int MAX_LENGTH = 200;
    private static final String FIELD_SEPARATOR = ", ";
    private static final String KEY_VALUE_SEPARATOR = ": ";

    private Map<String, String> fields;

    public SessionFields() {
        this(new LinkedHashMap<String, String>());
    }

    public SessionFields(Map<String, String> fields) {
        this.fields = fields;
    }

    @JsonValue
    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    @JsonAnySetter
    public void addField(String key, String value) {
        fields.put(key, value);
    }

    @Override
    public String toString() {
        final StringBuilder fieldStringBuilder = new StringBuilder();
        final Iterator<Map.Entry<String, String>> it = fields.entrySet().iterator();

        Map.Entry<String, String> entry;
        while (it.hasNext()) {
            entry = it.next();

            fieldStringBuilder.append(entry.getKey()).append(KEY_VALUE_SEPARATOR).append(entry.getValue());

            if (it.hasNext())
                fieldStringBuilder.append(FIELD_SEPARATOR);
        }

        return fieldStringBuilder.toString();
    }

    public String toJson() {
        return JacksonUtil.serialize(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionFields that = (SessionFields) o;

        return !(fields != null ? !fields.equals(that.fields) : that.fields != null);

    }

    @Override
    public int hashCode() {
        return fields != null ? fields.hashCode() : 0;
    }
}
