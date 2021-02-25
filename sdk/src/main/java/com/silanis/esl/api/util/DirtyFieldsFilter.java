package com.silanis.esl.api.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.silanis.esl.api.model.Model;

import java.util.Set;

public class DirtyFieldsFilter implements PropertyFilter {
    private final boolean filterDirtyFields;

    public DirtyFieldsFilter(boolean filterDirtyFields) {
        this.filterDirtyFields = filterDirtyFields;
    }

    @Override
    public void serializeAsField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, PropertyWriter paramPropertyWriter) throws Exception {
        if (paramObject instanceof Model) {
            boolean ser = true;

            if (filterDirtyFields) {
                Set<String> dirtyFields = ((Model) paramObject).getDirtyFields();

                ser = dirtyFields.contains(paramPropertyWriter.getName());
            }

            if (ser) {
                paramPropertyWriter.serializeAsField(paramObject, paramJsonGenerator, paramSerializerProvider);
            }
        }
    }

    @Override
    public void serializeAsElement(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, PropertyWriter paramPropertyWriter) throws Exception {
        if (paramObject instanceof Model) {
            boolean ser = true;

            if (filterDirtyFields) {
                Set<String> dirtyFields = ((Model) paramObject).getDirtyFields();

                ser = dirtyFields.contains(paramPropertyWriter.getName());
            }

            if (ser) {
                paramPropertyWriter.serializeAsElement(paramObject, paramJsonGenerator, paramSerializerProvider);
            }
        }
    }

    @Override
    public void depositSchemaProperty(PropertyWriter propertyWriter, ObjectNode objectNode, SerializerProvider serializerProvider) {

    }

    @Override
    public void depositSchemaProperty(PropertyWriter propertyWriter, JsonObjectFormatVisitor jsonObjectFormatVisitor, SerializerProvider serializerProvider) {

    }
}
