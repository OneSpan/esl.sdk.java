package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Provider;

import java.util.Map;

public class ProviderBuilder {
    private String provides;
    private Map<String, Object> data;
    private String id;
    private String name;

    private ProviderBuilder() {}

    public static ProviderBuilder newProvider() {
        return new ProviderBuilder();
    }
    
    public ProviderBuilder withId(String value ) {
        id = value;
        return this;
    }

    public ProviderBuilder withName(String value) {
        name = value;
        return this;
    }

    public ProviderBuilder withData(Map<String, Object> value) {
        data = value;
        return this;
    }

    public ProviderBuilder withProvides(String value) {
        provides = value;
        return this;
    }

    public Provider build() {
        Provider provider = new Provider();
        provider.setData(data);
        provider.setName(name);
        provider.setId(id);
        provider.setProvides(provides);
        return provider;
    }
}
