package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.GroupId;
import com.silanis.esl.sdk.builder.GroupBuilder;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * User: jessica
 * Date: 02/12/13
 * Time: 10:46 AM
 *
 * Test GroupConverter.
 */
public class GroupConverterTest implements ConverterTest{

    private com.silanis.esl.sdk.Group sdkGroup1 = null;
    private com.silanis.esl.sdk.Group sdkGroup2 = null;
    private com.silanis.esl.api.model.Group apiGroup1 = null;
    private com.silanis.esl.api.model.Group apiGroup2 = null;
    private GroupConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkGroup1 = null;
        converter = new GroupConverter(sdkGroup1);
        assertThat( "Converter didn't return a null api object for a null sdk object", converter.toAPIGroup(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiGroup1 = null;
        converter = new GroupConverter(apiGroup1);
        assertThat( "Converter didn't return a null sdk object for a null api object", converter.toSDKGroup(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkGroup1 = null;
        converter = new GroupConverter(sdkGroup1);
        assertThat( "Converter didn't return a null sdk object for a null sdk object", converter.toSDKGroup(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiGroup1 = null;
        converter = new GroupConverter(apiGroup1);
        assertThat( "Converter didn't return a null api object for a null api object", converter.toAPIGroup(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkGroup1 = createTypicalSDKGroup();
        sdkGroup2 = new GroupConverter(sdkGroup1).toSDKGroup();
        assertThat( "Converter returned a null sdk object for a non null sdk object", sdkGroup2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null sdk object it was given", sdkGroup2, is( equalTo( sdkGroup1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiGroup1 = createTypicalAPIGroup();
        apiGroup2 = new GroupConverter(apiGroup1).toAPIGroup();

        assertThat( "Converter returned a null api object for a non null api object", apiGroup2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null api object it was given", apiGroup2, is( equalTo( apiGroup1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiGroup1 = createTypicalAPIGroup();
        sdkGroup1 = new GroupConverter(apiGroup1).toSDKGroup();

        assertThat("Converter returned a null api object for a non null sdk object", apiGroup1, is( notNullValue() ) );
        assertThat("ID was not correctly set", apiGroup1.getId(), is(equalTo(sdkGroup1.getId().toString())));
        assertThat("Name was not correctly set", apiGroup1.getName(), is(equalTo(sdkGroup1.getName())));
        assertThat("Email was not correctly set", apiGroup1.getEmail(), is(equalTo(sdkGroup1.getEmail())));
        assertThat("Email member flag was not correctly set", apiGroup1.getEmailMembers(), is(equalTo(sdkGroup1.getEmailMembers())));
    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkGroup1 = createTypicalSDKGroup();
        apiGroup1 = new GroupConverter(sdkGroup1).toAPIGroup();

        assertThat("Converter returned a null api object for a non null sdk object", apiGroup1, is( notNullValue() ) );
        assertThat("ID was not correctly set", apiGroup1.getId(), is(equalTo(sdkGroup1.getId().toString())));
        assertThat("Name was not correctly set", apiGroup1.getName(), is(equalTo(sdkGroup1.getName())));
        assertThat("Create date was not correctly set", apiGroup1.getCreated(), is(equalTo(sdkGroup1.getCreated())));
        assertThat("Update date was not correctly set", apiGroup1.getUpdated(), is(equalTo(sdkGroup1.getUpdated())));
        assertThat("Email was not correctly set", apiGroup1.getEmail(), is(equalTo(sdkGroup1.getEmail())));
        assertThat("Email member flag was not correctly set", apiGroup1.getEmailMembers(), is(equalTo(sdkGroup1.getEmailMembers())));
    }

    /**
     * Create an SDK Group.
     *
     * @return SDK Group.
     */
    private com.silanis.esl.sdk.Group createTypicalSDKGroup() {
        com.silanis.esl.sdk.Group sdkGroup = GroupBuilder.newGroup("Group name")
                .withEmail("test@hotmail.com")
                .withId(new GroupId("1"))
                .withIndividualMemberEmailing()
                .build();
        return sdkGroup;
    }

    /**
     * Create an API Group.
     *
     * @return API Group.
     */
    private com.silanis.esl.api.model.Group createTypicalAPIGroup() {
        com.silanis.esl.api.model.Group apiGroup = new com.silanis.esl.api.model.Group();

        apiGroup.setId("3");
        apiGroup.setName("Group name");
        apiGroup.setCreated(new Date());
        apiGroup.setUpdated(new Date());
        apiGroup.setEmail("test@hotmail.com");
        apiGroup.setEmailMembers(true);
        return apiGroup;
    }    
}
