package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.GroupSummaryBuilder;
import org.junit.Test;

import java.util.HashMap;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by schoi on 7/14/16.
 */
public class GroupSummaryConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.GroupSummary sdkGroupSummary = null;
    private com.silanis.esl.api.model.GroupSummary apiGroupSummary = null;
    private GroupSummaryConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkGroupSummary = null;
        converter = new GroupSummaryConverter(sdkGroupSummary);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIGroupSummary(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiGroupSummary = null;
        converter = new GroupSummaryConverter(apiGroupSummary);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKGroupSummary(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkGroupSummary = null;
        converter = new GroupSummaryConverter(sdkGroupSummary);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKGroupSummary(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiGroupSummary = null;
        converter = new GroupSummaryConverter(apiGroupSummary);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIGroupSummary(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkGroupSummary = createTypicalSDKGroupSummary();
        sdkGroupSummary = new GroupSummaryConverter(sdkGroupSummary).toSDKGroupSummary();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkGroupSummary, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkGroupSummary, is(equalTo(sdkGroupSummary)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiGroupSummary = createTypicalAPIGroupSummary();
        apiGroupSummary = new GroupSummaryConverter(apiGroupSummary).toAPIGroupSummary();

        assertThat("Converter returned a null api object for a non null api object", apiGroupSummary, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiGroupSummary, is(equalTo(apiGroupSummary)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiGroupSummary = createTypicalAPIGroupSummary();
        sdkGroupSummary = new GroupSummaryConverter(apiGroupSummary).toSDKGroupSummary();

        assertThat("Converter returned a null sdk object for a non null api object", sdkGroupSummary, is(notNullValue()));
        assertThat("Id was not correctly set", sdkGroupSummary.getId(), is(apiGroupSummary.getId()));
        assertThat("Name was not correctly set", sdkGroupSummary.getName(), is(apiGroupSummary.getName()));
        assertThat("Email was not correctly set", sdkGroupSummary.getEmail(), is(apiGroupSummary.getEmail()));
        assertThat("Data was not correctly set", sdkGroupSummary.getData(), is(apiGroupSummary.getData()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkGroupSummary = createTypicalSDKGroupSummary();
        apiGroupSummary = new GroupSummaryConverter(sdkGroupSummary).toAPIGroupSummary();

        assertThat("Converter returned a null api object for a non null sdk object", apiGroupSummary, is(notNullValue()));
        assertThat("Id was not correctly set", apiGroupSummary.getId(), is(sdkGroupSummary.getId()));
        assertThat("Name was not correctly set", apiGroupSummary.getName(), is(sdkGroupSummary.getName()));
        assertThat("Email was not correctly set", apiGroupSummary.getEmail(), is(sdkGroupSummary.getEmail()));
        assertThat("Data was not correctly set", apiGroupSummary.getData(), is(sdkGroupSummary.getData()));
    }

    private com.silanis.esl.sdk.GroupSummary createTypicalSDKGroupSummary() {
        return GroupSummaryBuilder.newGroupSummary(UUID.randomUUID().toString().replace("-","") + "@e-signlive.com")
                                  .withId("sdkId")
                                  .withName("sdkName")
                                  .withData(new HashMap<String, Object>() {{
                                      put("sdkDataKey", "sdkDataValue");
                                  }})
                                  .build();
    }

    private com.silanis.esl.api.model.GroupSummary createTypicalAPIGroupSummary() {
        com.silanis.esl.api.model.GroupSummary apiGroupSummary = new com.silanis.esl.api.model.GroupSummary();
        apiGroupSummary.setName("apiName");
        apiGroupSummary.setId("apiId");
        apiGroupSummary.setEmail(UUID.randomUUID().toString().replace("-", "") + "@e-signlive.com");
        apiGroupSummary.setData(new HashMap<String, Object>() {{
            put("apiDataKey", "apiDataValue");
        }});

        return apiGroupSummary;
    }
}
