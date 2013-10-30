package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.DocumentPackageAttributes;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * User: jessica
 * Date: 30/10/13
 * Time: 9:34 AM
 */
public class DocumentPackageAttributesBuilderTest {

    @Test
    public void buildWithSpecifiedValues() {

        DocumentPackageAttributesBuilder documentPackageAttributesBuilder = new DocumentPackageAttributesBuilder()
                                        .withAttribute("First Name", "Adam")
                                        .withAttribute("Last Name", "Smith");
        DocumentPackageAttributes documentPackageAttributes = documentPackageAttributesBuilder.build();

        assertThat( "Builder returned a non null object", documentPackageAttributes, is( notNullValue() ) );
        assertThat( "First name was set correctly", documentPackageAttributes.getContents().get("First Name").toString(), is( equalTo( "Adam" ) ) );
        assertThat( "Last name was set correctly", documentPackageAttributes.getContents().get("Last Name").toString(), is( equalTo( "Smith" ) ) );
    }

}
