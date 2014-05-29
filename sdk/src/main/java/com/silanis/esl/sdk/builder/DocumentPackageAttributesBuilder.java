package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.DocumentPackageAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * User: jessica
 * Date: 28/10/13
 * Time: 4:20 PM
 */

/**
 * <p>DocumentPackageAttributesBuilder is a convenient class used to create and customize a package's attributes.</p>
 */
public class DocumentPackageAttributesBuilder {

    private Map<String, Object> map = new HashMap<String, Object>();

    private DocumentPackageAttributesBuilder() {}

    /**
     * Creates a new document package attributes builder.
     *
     * @return the document package attributes builder itself
     */
    public static DocumentPackageAttributesBuilder newDocumentPackageAttributes() {
        return new DocumentPackageAttributesBuilder();
    }

    /**
     * The constructor of the document package attributes builder and set attribute map.
     *
     * @param map the map of attributes
     */
    public DocumentPackageAttributesBuilder( Map<String,Object> map ) {
        this.map = map;
    }

    /**
     * Adds an attribute to the package's attribute map.
     *
     * @param name the name of the attribute @size(min="1", max="255")
     * @param value the value of the attribute @size(min="1", max="255")
     * @return the document package attributes builder itself
     */
    public DocumentPackageAttributesBuilder withAttribute( String name, Object value ) {
        map.put( name, value );
        return this;
    }

    /**
     * Builds an actual document package attributes.
     *
     * @return the document package attributes
     */
    public DocumentPackageAttributes build() {
        DocumentPackageAttributes result = new DocumentPackageAttributes();
        result.setContents( map );
        return result;
    }

}
