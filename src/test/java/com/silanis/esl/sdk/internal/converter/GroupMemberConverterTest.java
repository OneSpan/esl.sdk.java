package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.MemberType;
import com.silanis.esl.sdk.GroupMemberType;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.GroupMemberBuilder.newGroupMember;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * User: jessica
 * Date: 02/12/13
 * Time: 2:35 PM
 *
 * Test GroupMemberConverter.
 */
public class GroupMemberConverterTest implements ConverterTest{

    private com.silanis.esl.sdk.GroupMember sdkGroupMember1 = null;
    private com.silanis.esl.sdk.GroupMember sdkGroupMember2 = null;
    private com.silanis.esl.api.model.GroupMember apiGroupMember1 = null;
    private com.silanis.esl.api.model.GroupMember apiGroupMember2 = null;
    private GroupMemberConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkGroupMember1 = null;
        converter = new GroupMemberConverter(sdkGroupMember1);
        assertThat( "Converter didn't return a null api object for a null sdk object", converter.toAPIGroupMember(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiGroupMember1 = null;
        converter = new GroupMemberConverter(apiGroupMember1);
        assertThat( "Converter didn't return a null sdk object for a null api object", converter.toSDKGroupMember(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkGroupMember1 = null;
        converter = new GroupMemberConverter(sdkGroupMember1);
        assertThat( "Converter didn't return a null sdk object for a null sdk object", converter.toSDKGroupMember(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiGroupMember1 = null;
        converter = new GroupMemberConverter(apiGroupMember1);
        assertThat( "Converter didn't return a null api object for a null api object", converter.toAPIGroupMember(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkGroupMember1 = createTypicalSDKGroupMember();
        sdkGroupMember2 = new GroupMemberConverter(sdkGroupMember1).toSDKGroupMember();
        assertThat( "Converter returned a null sdk object for a non null sdk object", sdkGroupMember2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null sdk object it was given", sdkGroupMember2, is( equalTo( sdkGroupMember1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiGroupMember1 = createTypicalAPIGroupMember();
        apiGroupMember2 = new GroupMemberConverter(apiGroupMember1).toAPIGroupMember();

        assertThat( "Converter returned a null api object for a non null api object", apiGroupMember2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null api object it was given", apiGroupMember2, is( equalTo( apiGroupMember1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiGroupMember1 = createTypicalAPIGroupMember();
        sdkGroupMember1 = new GroupMemberConverter(apiGroupMember1).toSDKGroupMember();

        assertThat("Converter returned a null api object for a non null sdk object", apiGroupMember1, is( notNullValue() ) );
        assertThat("Member type was not correctly set", apiGroupMember1.getMemberType().name(), is( equalTo(sdkGroupMember1.getGroupMemberType().name()))  );
        assertThat("First name was not correctly set", apiGroupMember1.getFirstName(), is( equalTo(sdkGroupMember1.getFirstName()) ) );
        assertThat("Last name was not correctly set", apiGroupMember1.getLastName(), is( equalTo(sdkGroupMember1.getLastName()) ) );
        assertThat("Email was not correctly set", apiGroupMember1.getEmail(), is( equalTo(sdkGroupMember1.getEmail()) ) );
    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkGroupMember1 = createTypicalSDKGroupMember();
        apiGroupMember1 = new GroupMemberConverter(sdkGroupMember1).toAPIGroupMember();

        assertThat("Converter returned a null api object for a non null sdk object", apiGroupMember1, is( notNullValue() ) );
        assertThat("Member type was not correctly set", apiGroupMember1.getMemberType().name(), is( equalTo(sdkGroupMember1.getGroupMemberType().name()))  );
        assertThat("First name was not correctly set", apiGroupMember1.getFirstName(), is( equalTo(sdkGroupMember1.getFirstName()) ) );
        assertThat("Last name was not correctly set", apiGroupMember1.getLastName(), is( equalTo(sdkGroupMember1.getLastName()) ) );
        assertThat("Email was not correctly set", apiGroupMember1.getEmail(), is( equalTo(sdkGroupMember1.getEmail()) ) );
    }

    /**
     * Create an SDK GroupMember.
     *
     * @return SDK GroupMember.
     */
    private com.silanis.esl.sdk.GroupMember createTypicalSDKGroupMember() {

        com.silanis.esl.sdk.GroupMember sdkGroupMember = newGroupMember("test@hotmail.com")
                .withFirstName("First Name")
                .withLastName("Last Name")
                .as(GroupMemberType.MANAGER)
                .build();
        return sdkGroupMember;
    }

    /**
     * Create an API GroupMember.
     *
     * @return API GroupMember.
     */
    private com.silanis.esl.api.model.GroupMember createTypicalAPIGroupMember() {
        com.silanis.esl.api.model.GroupMember apiGroupMember = new com.silanis.esl.api.model.GroupMember();
        apiGroupMember.setEmail("test@hotmail.com");
        apiGroupMember.setFirstName("First name");
        apiGroupMember.setLastName("Last name");
        apiGroupMember.setMemberType(MemberType.MANAGER);

        return apiGroupMember;
    }    
}
