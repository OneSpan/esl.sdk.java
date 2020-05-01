package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Provider;

import java.util.Map;

public class ProviderBuilder {
    private String provides;
    private Map<String, Object> data;
    private String id;
    private String name;

    private ProviderBuilder() {
    }

    public static ProviderBuilder newProvider() {
        return new ProviderBuilder();
    }

    public ProviderBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ProviderBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProviderBuilder withData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public ProviderBuilder withProvides(String provides) {
        this.provides = provides;
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
