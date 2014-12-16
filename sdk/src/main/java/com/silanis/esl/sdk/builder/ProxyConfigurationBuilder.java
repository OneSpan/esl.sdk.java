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

    private ProxyConfigurationBuilder() {}

    public static ProxyConfigurationBuilder newProxyConfiguration() {
        return new ProxyConfigurationBuilder();
    }

    public ProxyConfigurationBuilder withHttpHost( String httpHost ) {
        this.httpHost = httpHost;
        return this;
    }

    public ProxyConfigurationBuilder withHttpPort( int httpPort ) {
        this.httpPort = httpPort;
        return this;
    }

    public ProxyConfigurationBuilder withHttpsHost( String httpsHost ) {
        this.httpsHost = httpsHost;
        return this;
    }

    public ProxyConfigurationBuilder withHttpsPort( int httpsPort ) {
        this.httpsPort = httpsPort;
        return this;
    }

    public ProxyConfigurationBuilder withCredentials( String userName, String password ) {
        this.userName = userName;
        this.password = password;
        return this;
    }

    public ProxyConfiguration build() {
        validate();
        ProxyConfiguration result = new ProxyConfiguration();
        if ( httpHost != null && httpPort != 0) {
            result.setHttpHost(httpHost);
            result.setHttpPort(httpPort);
            result.setHttpScheme();
        }
        else if (httpsHost != null && httpsPort != 0) {
            result.setHttpsHost(httpsHost);
            result.setHttpsPort(httpsPort);
            result.setHttpsScheme();
        }
        if (userName != null && password != null) {
            result.setUserName(userName);
            result.setPassword(password);
            result.setCredentials(true);
        }
        return result;
    }

    private void validate() {
        if (httpHost!= null && !httpHost.matches("\\b(http?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")) {
            throw new BuilderException( "The proxy should be a correct http URL." );
        }
        if (httpsHost!= null && !httpsHost.matches("\\b(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")) {
            throw new BuilderException( "The proxy should be a correct https URL." );
        }
        if ( (httpHost != null && httpPort != 0) && ( httpsHost != null || httpsPort != 0 )
                || ( (httpsHost != null && httpsPort != 0) && ( httpHost != null || httpPort != 0 ) ) ) {
            throw new BuilderException( "Only can set 1 sort of proxy." );
        }
        if ( (httpHost != null && httpPort == 0)
                || (httpHost == null && httpPort != 0)
                || (httpsHost != null && httpsPort == 0)
                || (httpsHost == null && httpsPort != 0)) {
            throw new BuilderException( "At lease 1 sort of proxy need to set." );
        }
    }

}
