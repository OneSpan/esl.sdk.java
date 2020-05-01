package com.silanis.esl.sdk.internal.converter;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ProviderConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.Provider sdkProvider = null;
    private com.silanis.esl.api.model.Provider apiProvider = null;
    private ProviderConverter converter;

    private static final String PROV_ID = "provider_id";
    private static final String PROV_NAME = "provider_name";
    private static final String PROV_PROVIDES = "provider_provides";
    private static final Map<String, Object> PROV_DATA = ImmutableMap.<String, Object>of("provider_0_data_0_key", "provider_0_data_0_value");
    



    @Test
    public void convertNullSDKToAPI() {
        sdkProvider = null;
        converter = new ProviderConverter(sdkProvider);

        assertThat(converter.toAPIProvider(), nullValue());
    }


    @Test
    public void convertNullAPIToSDK() {
        apiProvider = null;
        converter = new ProviderConverter(apiProvider);

        assertThat( converter.toSDKProvider(), nullValue());
    }

    @Test
    public void convertNullSDKToSDK() {
        sdkProvider = null;
        converter = new ProviderConverter(sdkProvider);

        assertThat( converter.toSDKProvider(), nullValue());
    }


    @Test
    public void convertNullAPIToAPI() {
        apiProvider = null;
        converter = new ProviderConverter(apiProvider);

        assertThat(converter.toAPIProvider(), nullValue());
    }

    @Test
    public void convertSDKToSDK() {
        sdkProvider = createTypicalSDKProvider();
        converter = new ProviderConverter( sdkProvider );

        com.silanis.esl.sdk.Provider provider = converter.toSDKProvider();

        assertThat(provider, is( notNullValue() ) );
        assertThat(provider, is( equalTo( provider ) ) );
    }

    @Test
    public void convertAPIToAPI() {
        apiProvider = createTypicalAPIProvider();
        converter = new ProviderConverter( apiProvider );

        com.silanis.esl.api.model.Provider provider = converter.toAPIProvider();

        assertThat( provider, is( notNullValue() ) );
        assertThat( provider, is( equalTo( provider ) ) );
    }

    @Test
    public void convertAPIToSDK() {
        apiProvider = createTypicalAPIProvider();
        converter = new ProviderConverter( apiProvider );

        com.silanis.esl.sdk.Provider provider = converter.toSDKProvider();

        assertThat(provider, is( notNullValue() ) );
        assertThat(provider.getName(), is(equalTo(PROV_NAME)));
    }

    @Test
    public void convertSDKToAPI() {
        sdkProvider = createTypicalSDKProvider();
        converter = new ProviderConverter( sdkProvider );
        com.silanis.esl.api.model.Provider provider = converter.toAPIProvider();

        assertThat(provider, is( notNullValue() ) );
        assertThat(provider.getName(), is(equalTo(PROV_NAME)));
    }

    private com.silanis.esl.sdk.Provider createTypicalSDKProvider() {

        com.silanis.esl.sdk.Provider provider = new com.silanis.esl.sdk.Provider();
        provider.setId(PROV_ID);
        provider.setName(PROV_NAME);
        provider.setData(PROV_DATA);
        provider.setProvides(PROV_PROVIDES);

        return provider;
    }

    private com.silanis.esl.api.model.Provider createTypicalAPIProvider() {
        com.silanis.esl.api.model.Provider provider = new com.silanis.esl.api.model.Provider();

        provider.setId(PROV_ID);
        provider.setName(PROV_NAME);
        provider.setData(PROV_DATA);
        provider.setProvides(PROV_PROVIDES);

        return provider;
    }
}
