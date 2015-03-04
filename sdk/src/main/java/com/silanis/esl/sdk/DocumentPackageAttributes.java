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
    }

    public void append( String name, Object value ) {
        if (null == contents) {
            contents = new HashMap<String,Object>();
        }
        contents.put(name, value);
    }

    public Map<String, Object> getContents() {
        return contents;
    }

    public void setContents(Map<String, Object> contents) {
        this.contents = contents;
    }

    public Map<String,Object> toMap() {
        return contents;
    }
}
