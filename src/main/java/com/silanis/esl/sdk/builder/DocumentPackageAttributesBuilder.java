package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.DocumentPackageAttributes;

/**
 * User: jessica
 * Date: 28/10/13
 * Time: 4:20 PM
 */
public class DocumentPackageAttributesBuilder {

    private DocumentPackageAttributes attributes = new DocumentPackageAttributes();

    public DocumentPackageAttributesBuilder withAttribute( String name, Object value ) {
        this.attributes.append(name, value);
        return this;
    }

    public DocumentPackageAttributes build() {
        return attributes;
    }

}
