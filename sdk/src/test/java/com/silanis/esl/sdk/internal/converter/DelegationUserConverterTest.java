package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.DelegationUserBuilder;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 7/14/16.
 */
public class DelegationUserConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.DelegationUser sdkDelegationUser = null;
    private com.silanis.esl.api.model.DelegationUser apiDelegationUser = null;
    private DelegationUserConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkDelegationUser = null;
        converter = new DelegationUserConverter(sdkDelegationUser);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIDelegationUser(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiDelegationUser = null;
        converter = new DelegationUserConverter(apiDelegationUser);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKDelegationUser(), nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkDelegationUser = null;
        converter = new DelegationUserConverter(sdkDelegationUser);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKDelegationUser(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiDelegationUser = null;
        converter = new DelegationUserConverter(apiDelegationUser);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIDelegationUser(), nullValue());
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkDelegationUser = createTypicalSDKDelegationUser();
        sdkDelegationUser = new DelegationUserConverter(sdkDelegationUser).toSDKDelegationUser();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkDelegationUser, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkDelegationUser, is(sdkDelegationUser));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiDelegationUser = createTypicalAPIDelegationUser();
        apiDelegationUser = new DelegationUserConverter(apiDelegationUser).toAPIDelegationUser();

        assertThat("Converter returned a null api object for a non null api object", apiDelegationUser, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiDelegationUser, is(apiDelegationUser));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiDelegationUser = createTypicalAPIDelegationUser();
        sdkDelegationUser = new DelegationUserConverter(apiDelegationUser).toSDKDelegationUser();

        assertThat("Converter returned a null sdk object for a non null api object", sdkDelegationUser, notNullValue());
        assertThat("Name was not correctly set", sdkDelegationUser.getName(), is(apiDelegationUser.getName()));
        assertThat("Email was not correctly set", sdkDelegationUser.getEmail(), is(apiDelegationUser.getEmail()));
        assertThat("Id was not correctly set", sdkDelegationUser.getId(), is(apiDelegationUser.getId()));
        assertThat("FirstName was not correctly set", sdkDelegationUser.getFirstName(), is(apiDelegationUser.getFirstName()));
        assertThat("LastName was not correctly set", sdkDelegationUser.getLastName(), is(apiDelegationUser.getLastName()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkDelegationUser = createTypicalSDKDelegationUser();
        apiDelegationUser = new DelegationUserConverter(sdkDelegationUser).toAPIDelegationUser();

        assertThat("Converter returned a null api object for a non null sdk object", apiDelegationUser, notNullValue());
        assertThat("Name was not correctly set", apiDelegationUser.getName(), is(sdkDelegationUser.getName()));
        assertThat("Email was not correctly set", apiDelegationUser.getEmail(), is(sdkDelegationUser.getEmail()));
        assertThat("Id was not correctly set", apiDelegationUser.getId(), is(sdkDelegationUser.getId()));
        assertThat("FirstName was not correctly set", apiDelegationUser.getFirstName(), is(sdkDelegationUser.getFirstName()));
        assertThat("LastName was not correctly set", apiDelegationUser.getLastName(), is(sdkDelegationUser.getLastName()));
    }

    private com.silanis.esl.sdk.DelegationUser createTypicalSDKDelegationUser() {
        return DelegationUserBuilder.newDelegationUser(UUID.randomUUID().toString().replace("-", "") + "@e-signlive.com")
                .withId("sdkId")
                .withFirstName("sdkFirstName")
                .withLastName("sdkLastName")
                .withName("sdkName")
                .build();
    }

    private com.silanis.esl.api.model.DelegationUser createTypicalAPIDelegationUser() {
        com.silanis.esl.api.model.DelegationUser apiDelegationUser = new com.silanis.esl.api.model.DelegationUser();
        apiDelegationUser.setId("apiId");
        apiDelegationUser.setName("apiName");
        apiDelegationUser.setEmail(UUID.randomUUID().toString().replace("-", "") + "@e-signlive.com");
        apiDelegationUser.setFirstName("apiFirstName");
        apiDelegationUser.setLastName("apiLastName");

        return apiDelegationUser;
    }
}
