package com.silanis.esl.sdk.builder;



/**
 * Created by whou on 03/12/14.
 */
public class Proxy {

    private final String proxyURL;
    private final int proxyPort;
    private final String proxyUserName;
    private final String proxyPassword;
    private final String proxyScheme;

    public Proxy(String proxyURL, int proxyPort, String proxyScheme, String proxyUserName, String proxyPassword) {
        this.proxyURL = proxyURL;
        this.proxyPort = proxyPort;
        this.proxyUserName = proxyUserName;
        this.proxyPassword = proxyPassword;
        this.proxyScheme = proxyScheme;
    }

    public Proxy(){

        proxyURL = null;
        proxyPort = 0;
        proxyUserName = null;
        proxyPassword = null;
        proxyScheme = null;
    }

    public String getProxyURL() {
        return proxyURL;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public String getProxyUserName() {
        return proxyUserName;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public String getProxyScheme() {
        return proxyScheme;
    }

}
