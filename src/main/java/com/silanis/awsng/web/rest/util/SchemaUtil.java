package com.silanis.awsng.web.rest.util;

public class SchemaUtil {

    public static final String trim(String str) {
        if (str == null) {
            return null;
        }
        return str.trim();
    }

    public static final String sanitize(String str) {
        // Use HTMLSanitizer
        return str;
    }

    public static void notANull (String key, Object value) {
        if (null == value) { throw new IllegalArgumentException(key + " cannot be null"); }
    }

}
