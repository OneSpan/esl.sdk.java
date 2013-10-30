package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.DocumentPackageAttributes;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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

        DocumentPackageAttributesBuilder documentPackageAttributesBuilder = DocumentPackageAttributesBuilder.newDocumentPackageAttributes()
                                        .withAttribute( "First Name", "Adam" )
                                        .withAttribute( "Last Name", "Smith" );
        DocumentPackageAttributes documentPackageAttributes = documentPackageAttributesBuilder.build();

        assertThat( "Builder returned a non null object", documentPackageAttributes, is( notNullValue() ) );
        assertThat( "First name was set correctly", documentPackageAttributes.getContents().get("First Name").toString(), is( equalTo( "Adam" ) ) );
        assertThat( "Last name was set correctly", documentPackageAttributes.getContents().get("Last Name").toString(), is( equalTo( "Smith" ) ) );
    }

    @Test
    public void buildFromMap() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put( "key1", "value1" );
        map.put( "key2", "value2" );
        map.put( "key3", "value3" );

        DocumentPackageAttributesBuilder builder = new DocumentPackageAttributesBuilder( map );
        DocumentPackageAttributes result = builder.build();

        assertThat( "Builder returned a null value", result, is( notNullValue() ) );
        assertThat( "built object has the wrong number of attributes", map.keySet().size(), is( equalTo( map.keySet().size() ) ) );
        for ( String key : map.keySet() ) {
            assertThat( "attribute's value has changed", result.getContents().get( key), is( equalTo( map.get( key ) ) ) );
        }
    }

}
