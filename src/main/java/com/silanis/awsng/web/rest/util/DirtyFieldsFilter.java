package com.silanis.awsng.web.rest.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.silanis.awsng.web.rest.model.Model;

import java.util.Set;

public class DirtyFieldsFilter implements BeanPropertyFilter {

    public DirtyFieldsFilter(boolean filterDirtyFields) {
        this.filterDirtyFields = filterDirtyFields;
    }

    private boolean filterDirtyFields = false;

    @Override
    public void serializeAsField(Object paramObject,
                                 JsonGenerator paramJsonGenerator,
                                 SerializerProvider paramSerializerProvider,
                                 BeanPropertyWriter paramBeanPropertyWriter) throws Exception {
        if (paramObject instanceof Model) {
            boolean ser = true;

            if (filterDirtyFields) {
                Set<String> dirtyFields = ((Model) paramObject).getDirtyFields();

                ser = dirtyFields.contains(paramBeanPropertyWriter.getName());
            }

            if (ser) {
                paramBeanPropertyWriter.serializeAsField(paramObject, paramJsonGenerator, paramSerializerProvider);
            }
        }
    }

}
