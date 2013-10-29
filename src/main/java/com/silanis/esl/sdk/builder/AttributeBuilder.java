package com.silanis.esl.sdk.builder;

import java.util.HashMap;
import java.util.Map;

/**
 * User: jessica
 * Date: 28/10/13
 * Time: 4:20 PM
 */
public class AttributeBuilder {

    private final String KEY = "\"RES_data\"";

    private String jsonString;

    public AttributeBuilder withJsonString(String jsonString) {

        this.jsonString = jsonString;
        return this;
    }

    public Map<String, Object> build() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(KEY, jsonString);

        return data;
    }

}
