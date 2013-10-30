package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.DocumentPackageAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * User: jessica
 * Date: 28/10/13
 * Time: 4:20 PM
 */
public class DocumentPackageAttributesBuilder {

    private Map<String, Object> map = new HashMap<String, Object>();

    private DocumentPackageAttributesBuilder() {}

    public static DocumentPackageAttributesBuilder newDocumentPackageAttributes() {
        return new DocumentPackageAttributesBuilder();
    }

    public DocumentPackageAttributesBuilder( Map<String,Object> map ) {
        this.map = map;
    }

    public DocumentPackageAttributesBuilder withAttribute( String name, Object value ) {
        map.put( name, value );
        return this;
    }

    public DocumentPackageAttributes build() {
        DocumentPackageAttributes result = new DocumentPackageAttributes();
        result.setContents( map );
        return result;
    }

}
