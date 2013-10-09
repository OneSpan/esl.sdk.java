package com.silanis.awsng.web.rest.util;

import java.util.Arrays;

public class SchemaSanitizer {

    /**
    *  Make sure the object is not  null.
    */
    public static void throwOnNull (String key, Object value) {
       if (null == value) { throw new IllegalArgumentException(key + " cannot be null"); }
    }

    public static String sanitize(String html) {
       return html;
    }

    public static String trim(String str) {
       return str == null ? null : str.trim();
    }

    // Constrains must be able to sustain string base indexOf the value. If your
    // format is date,time too bad
    public static void throwIfOutsideConstraints( String key, String value, String constraints ) {
        // Please llok at the real implementation ... |:Lo

        // if ( -1 == Arrays.indexOf(constraints.split(","), value)) {
        //     throw new IllegalArgumentException(key + " is constrained to " + constraints );
        // }
    }

}
