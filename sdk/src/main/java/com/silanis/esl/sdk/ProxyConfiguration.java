package com.silanis.esl.sdk;

/**
 * Created by whou on 05/12/14.
 */
public class ProxyConfiguration {

    private String httpHost;
    private int httpPort;
    private String httpsHost;
    private int httpsPort;
    private String userName;
    private String password;
    private Scheme scheme;
    private boolean credentials;

    public ProxyConfiguration(){
        httpHost = null;
        httpPort = 0;
        httpsHost = null;
        httpsPort = 0;
        userName = null;
        password = null;
        scheme = null;
        credentials = false;
    }

    public String getHttpHost() {
        return httpHost;
    }
    public void setHttpHost(String httpHost) {
        this.httpHost = httpHost;
    }

    public int getHttpPort() {
        return httpPort;
    }
    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

    public String getHttpsHost() {
        return httpsHost;
    }
    public void setHttpsHost(String httpsHost) {
        this.httpsHost = httpsHost;
    }

    public int getHttpsPort() {
        return httpsPort;
    }
    public void setHttpsPort(int httpsPort) {
        this.httpsPort = httpsPort;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getScheme() {
        return scheme.getValue();
    }

    public boolean hasCredentials() {
        return credentials;
    }
    public void setCredentials(boolean credentials) {
        this.credentials = credentials;
    }

    public String getHost() {
        if(isHttp()){
            return httpHost;
        }
        else{
            return httpsHost;
        }
    }

    public int getPort() {
        if(isHttp()){
            return httpPort;
        }
        else{
            return httpsPort;
        }
    }

    private enum Scheme{
        HTTP("http"),HTTPS("https");

        private String value;
        Scheme(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public void setHttpScheme() {
        scheme = Scheme.HTTP;
    }

    public void setHttpsScheme() {
        scheme = Scheme.HTTPS;
    }

    private boolean isHttp() {
        return scheme == Scheme.HTTP;
    }
}
