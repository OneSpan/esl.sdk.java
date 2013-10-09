package com.silanis.awsng.web.rest;

import com.fasterxml.jackson.core.type.TypeReference;

import static java.lang.String.format;

/**
 * @author ehardy
 */
public class JsonDeserializationException extends RuntimeException {
    public JsonDeserializationException(Class<?> targetType, Throwable cause) {
        super(format("Failed to deserialize json string to target type %s", targetType.getName()), cause);
    }

    public <T> JsonDeserializationException(TypeReference<T> reference, Throwable cause) {
        super(format("Failed to deserialize json string to target type %s", reference.getType()), cause);
    }
}
