package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.GroupMemberType;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * User: jessica
 * Date: 02/12/13
 * Time: 2:14 PM
 * 
 * Test GroupMemberTypeConverter.
 */
public class GroupMemberTypeConverterTest implements ConverterTest{

    private com.silanis.esl.sdk.GroupMemberType sdkGroupMemberType1 = null;
    private com.silanis.esl.sdk.GroupMemberType sdkGroupMemberType2 = null;
    private String apiGroupMemberType1 = null;
    private String apiGroupMemberType2 = null;
    private GroupMemberTypeConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkGroupMemberType1 = null;
        converter = new GroupMemberTypeConverter(sdkGroupMemberType1);
        assertThat( "Converter didn't return a null api object for a null sdk object", converter.toAPIGroupMemberType(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiGroupMemberType1 = null;
        converter = new GroupMemberTypeConverter(apiGroupMemberType1);
        assertThat( "Converter didn't return a null sdk object for a null api object", converter.toSDKGroupMemberType(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkGroupMemberType1 = null;
        converter = new GroupMemberTypeConverter(sdkGroupMemberType1);
        assertThat( "Converter didn't return a null sdk object for a null sdk object", converter.toSDKGroupMemberType(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiGroupMemberType1 = null;
        converter = new GroupMemberTypeConverter(apiGroupMemberType1);
        assertThat( "Converter didn't return a null api object for a null api object", converter.toAPIGroupMemberType(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkGroupMemberType1 = createTypicalSDKGroupMemberType();
        sdkGroupMemberType2 = new GroupMemberTypeConverter(sdkGroupMemberType1).toSDKGroupMemberType();
        assertThat( "Converter returned a null sdk object for a non null sdk object", sdkGroupMemberType2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null sdk object it was given", sdkGroupMemberType2, is( equalTo( sdkGroupMemberType1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiGroupMemberType1 = createTypicalAPIGroupMemberType();
        apiGroupMemberType2 = new GroupMemberTypeConverter(apiGroupMemberType1).toAPIGroupMemberType();

        assertThat( "Converter returned a null api object for a non null api object", apiGroupMemberType2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null api object it was given", apiGroupMemberType2, is( equalTo( apiGroupMemberType1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiGroupMemberType1 = "MANAGER";
        sdkGroupMemberType1 = new GroupMemberTypeConverter(apiGroupMemberType1).toSDKGroupMemberType();
        assertThat("Converter returned a null api object for a non null sdk object", apiGroupMemberType1, is( notNullValue() ) );
        assertThat("Member type was not correctly set", apiGroupMemberType1, is( equalTo(sdkGroupMemberType1.toString()) ) );

        apiGroupMemberType1 = "REGULAR";
        sdkGroupMemberType1 = new GroupMemberTypeConverter(apiGroupMemberType1).toSDKGroupMemberType();
        assertThat("Converter returned a null api object for a non null sdk object", apiGroupMemberType1, is( notNullValue() ) );
        assertThat("Member type was not correctly set", apiGroupMemberType1, is( equalTo(sdkGroupMemberType1.toString()) ) );

        apiGroupMemberType1 = "UNKNOWN";
        sdkGroupMemberType1 = new GroupMemberTypeConverter(apiGroupMemberType1).toSDKGroupMemberType();
        assertThat("Converter returned a null api object for a non null sdk object", apiGroupMemberType1, is( notNullValue() ) );
        assertThat("Member type was not correctly set", apiGroupMemberType1, is( equalTo(sdkGroupMemberType1.toString()) ) );

    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkGroupMemberType1 = GroupMemberType.MANAGER;
        apiGroupMemberType1 = new GroupMemberTypeConverter(sdkGroupMemberType1).toAPIGroupMemberType();
        assertThat("Converter returned a null api object for a non null sdk object", apiGroupMemberType1, is( notNullValue() ) );
        assertThat("Member type was not correctly set", apiGroupMemberType1, is( equalTo(sdkGroupMemberType1.toString()) ) );

        sdkGroupMemberType1 = GroupMemberType.REGULAR;
        apiGroupMemberType1 = new GroupMemberTypeConverter(sdkGroupMemberType1).toAPIGroupMemberType();
        assertThat("Converter returned a null api object for a non null sdk object", apiGroupMemberType1, is( notNullValue() ) );
        assertThat("Member type was not correctly set", apiGroupMemberType1, is( equalTo(sdkGroupMemberType1.toString()) ) );

        sdkGroupMemberType1 = GroupMemberType.UNRECOGNIZED("UNKNOWN");
        apiGroupMemberType1 = new GroupMemberTypeConverter(sdkGroupMemberType1).toAPIGroupMemberType();
        assertThat("Converter returned a null api object for a non null sdk object", apiGroupMemberType1, is( notNullValue() ) );
        assertThat("Member type was not correctly set", apiGroupMemberType1, is( equalTo(sdkGroupMemberType1.toString()) ) );
    }

    /**
     * Create an SDK GroupMemberType.
     *
     * @return SDK GroupMemberType.
     */
    private com.silanis.esl.sdk.GroupMemberType createTypicalSDKGroupMemberType() {

        com.silanis.esl.sdk.GroupMemberType sdkGroupMemberType = GroupMemberType.MANAGER;
        return sdkGroupMemberType;
    }

    /**
     * Create an API GroupMemberType.
     *
     * @return API GroupMemberType.
     */
    private String createTypicalAPIGroupMemberType() {
        String apiGroupMemberType = "MANAGER";
        return apiGroupMemberType;
    }    
}
