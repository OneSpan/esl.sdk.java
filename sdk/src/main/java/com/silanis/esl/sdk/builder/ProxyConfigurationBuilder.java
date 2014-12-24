package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.ProxyConfiguration;

/**
 * Created by whou on 03/12/14.
 */

public class ProxyConfigurationBuilder {

    private String httpHost;
    private int httpPort;
    private String httpsHost;
    private int httpsPort;
    private String userName;
    private String password;

    private ProxyConfigurationBuilder() {
    }

    public static ProxyConfigurationBuilder newProxyConfiguration() {
        return new ProxyConfigurationBuilder();
    }

    public ProxyConfigurationBuilder withHttpHost(String httpHost) {
        this.httpHost = httpHost;
        return this;
    }

    public ProxyConfigurationBuilder withHttpPort(int httpPort) {
        this.httpPort = httpPort;
        return this;
    }

    public ProxyConfigurationBuilder withHttpsHost(String httpsHost) {
        this.httpsHost = httpsHost;
        return this;
    }

    public ProxyConfigurationBuilder withHttpsPort(int httpsPort) {
        this.httpsPort = httpsPort;
        return this;
    }

    public ProxyConfigurationBuilder withCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
        return this;
    }

    public ProxyConfiguration build() {
        validate();
        ProxyConfiguration result = new ProxyConfiguration();
        if (isHttpProxy()) {
            result.setHttpHost(httpHost);
            result.setHttpPort(httpPort);
            result.setHttpScheme();
        } else if (isHttpsProxy()) {
            result.setHttpsHost(httpsHost);
            result.setHttpsPort(httpsPort);
            result.setHttpsScheme();
        }
        if (isCredentialsNotNull()) {
            result.setUserName(userName);
            result.setPassword(password);
            result.setCredentials(true);
        }
        return result;
    }

    private void validate() {
        if ((isHttpProxy()) && (httpsHost != null || httpsPort != 0)
                || ((isHttpsProxy()) && (httpHost != null || httpPort != 0))) {
            throw new BuilderException("Cannot set up both the http and https proxy, Use either http or https.");
        }
        if ((httpHost != null && httpPort == 0)
                || (httpHost == null && httpPort != 0)
                || (httpsHost != null && httpsPort == 0)
                || (httpsHost == null && httpsPort != 0)) {
            throw new BuilderException("Neither http nor https proxy is setup.");
        }
    }

    private boolean isCredentialsNotNull() {
        return userName != null && password != null;
    }

    private boolean isHttpsProxy() {
        return httpsHost != null && httpsPort != 0;
    }

    private boolean isHttpProxy() {
        return httpHost != null && httpPort != 0;
    }

}
