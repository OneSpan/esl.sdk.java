package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

/**
 * User: jessica
 * Date: 29/10/13
 * Time: 3:15 PM
 */
public class DocumentPackageAttributes {

    private Map<String,Object> contents = new HashMap<String,Object>();

    public DocumentPackageAttributes() {
        // empty constructor
    }

    public DocumentPackageAttributes(Map<String, Object> contents) {
        this.contents = contents;
    }

    public void append( String name, Object value ) {
        this.contents.put(name, value);
    }

    public Map<String, Object> getContents() {
        return contents;
    }

    public void setContents(Map<String, Object> contents) {
        this.contents = contents;
    }
}
