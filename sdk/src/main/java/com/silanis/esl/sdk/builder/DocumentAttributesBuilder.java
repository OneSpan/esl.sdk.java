package com.silanis.esl.sdk.builder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by schoi on 28/06/17.
 */

/**
 * <p>DocumentAttributesBuilder is a convenient class used to create and customize a document's attributes.</p>
 */
public class DocumentAttributesBuilder {

    private Map<String, Object> data = new HashMap<String, Object>();

    private DocumentAttributesBuilder() {
    }

    /**
     * Creates a new document attributes builder.
     *
     * @return the document attributes builder itself
     */
    public static DocumentAttributesBuilder newDocumentAttributes() {
        return new DocumentAttributesBuilder();
    }

    /**
     * The constructor of the document attributes builder and set attribute map.
     *
     * @param data the map of attributes
     */
    public DocumentAttributesBuilder(Map<String, Object> data) {
        this.data = data;
    }

    /**
     * Adds an attribute to the document's attribute map.
     *
     * @param name  the name of the attribute
     * @param value the value of the attribute
     * @return the document attributes builder itself
     */
    public DocumentAttributesBuilder addAttribute(String name, Object value) {
        data.put(name, value);
        return this;
    }

    /**
     * Builds an actual document attributes.
     *
     * @return the document attributes map
     */
    public Map<String, Object> build() {
        return data;
    }
}
