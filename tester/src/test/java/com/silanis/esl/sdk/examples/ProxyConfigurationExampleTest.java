package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import java.io.IOException;
import org.junit.Test;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.ProxyAuthenticator;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by whou on 08/12/14.
 */
public class ProxyConfigurationExampleTest {

    private final int httpProxyPort = 8001;
    private final int httpProxyWithCredentialsPort = 8002;


    @Test
    public void verifyResult() throws Exception {
        proxyExecution();
    }

    public void proxyExecution() throws IOException {
        ProxyConfigurationExample example = new ProxyConfigurationExample(Props.get());

        HttpProxyServer httpProxyServer = startHttpProxy(httpProxyPort);
        example.executeHttpProxy();
        DocumentPackage documentPackage1 = example.eslClientWithHttpProxy.getPackage(example.packageId);
        assertThat(documentPackage1, is(notNullValue()));
        httpProxyServer.stop();

        HttpProxyServer httpProxyWithCredentialsServer = startHttpProxyWithCredentials(httpProxyWithCredentialsPort, "httpUser", "httpPwd");
        example.executeHttpProxyWithCredentials();
        DocumentPackage documentPackage2 = example.eslClientWithHttpProxyHasCredentials.getPackage(example.packageId);
        assertThat(documentPackage2, is(notNullValue()));
        httpProxyWithCredentialsServer.stop();
    }

    public HttpProxyServer startHttpProxy(int port) {
        final HttpProxyServer httpProxyServer = DefaultHttpProxyServer.bootstrap().
                withPort(port).
                start();
        return httpProxyServer;
    }

    public HttpProxyServer startHttpProxyWithCredentials(int port, final String acceptedUsername, final String acceptedPassword) {
        final HttpProxyServer httpProxyServer = DefaultHttpProxyServer.bootstrap().
                withPort(port).
                withProxyAuthenticator(new ProxyAuthenticator() {
                    @Override
                    public boolean authenticate(String s1, String s2) {
                        return acceptedUsername.equals(s1) && acceptedPassword.equals(s2);
                    }
                }).
                start();
        return httpProxyServer;
    }

}
