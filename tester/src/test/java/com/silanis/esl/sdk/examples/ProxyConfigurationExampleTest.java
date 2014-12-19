package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import io.netty.handler.codec.http.HttpMethod;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.MitmManager;
import org.littleshoot.proxy.ProxyAuthenticator;
import org.littleshoot.proxy.extras.SelfSignedMitmManager;
import org.littleshoot.proxy.extras.SelfSignedSslEngineSource;
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
    private final int httpsProxyPort = 80;
    private final int httpsProxyWithCredentialsPort = 8004;

    protected AtomicInteger bytesReceivedFromClient;
    protected AtomicInteger requestsReceivedFromClient;
    protected AtomicInteger bytesSentToServer;
    protected AtomicInteger requestsSentToServer;
    protected AtomicInteger bytesReceivedFromServer;
    protected AtomicInteger responsesReceivedFromServer;
    protected AtomicInteger bytesSentToClient;
    protected AtomicInteger responsesSentToClient;
    protected AtomicInteger clientConnects;
    protected AtomicInteger clientSSLHandshakeSuccesses;
    protected AtomicInteger clientDisconnects;

    private Set<HttpMethod> requestPreMethodsSeen = new HashSet<HttpMethod>();
    private Set<HttpMethod> requestPostMethodsSeen = new HashSet<HttpMethod>();
    private StringBuilder responsePreBody = new StringBuilder();
    private StringBuilder responsePostBody = new StringBuilder();
    private Set<HttpMethod> responsePreOriginalRequestMethodsSeen = new HashSet<HttpMethod>();
    private Set<HttpMethod> responsePostOriginalRequestMethodsSeen = new HashSet<HttpMethod>();

    @Test
    public void verifyResult() throws Exception {

//        regularProxyExecution();
        simpleProxyExecution();
    }

    public void regularProxyExecution() throws IOException {
        DocumentPackage documentPackage1, documentPackage2, documentPackage3, documentPackage4;

        ProxyConfigurationExample example = new ProxyConfigurationExample(Props.get());

        HttpProxyServer httpProxyServer, httpProxyHasCredentialsServer, httpsProxyServer, httpsProxyHasCredentialsServer;

//        httpProxyServer = httpProxy(httpProxyPort);
//        example.executeHttpProxy();
//        documentPackage1 = example.eslClientWithHttpProxy.getPackage(example.packageId);
//        assertThat(documentPackage1, is(notNullValue()));
//        httpProxyServer.stop();
//
//        httpProxyHasCredentialsServer = httpProxyHasCredentials(httpProxyWithCredentialsPort, "httpUser", "httpPwd");
//        example.executeHttpProxyWithCredentials();
//        documentPackage2 = example.eslClientWithHttpProxyHasCredentials.getPackage(example.packageId);
//        assertThat(documentPackage2, is(notNullValue()));
//        httpProxyHasCredentialsServer.stop();

        //httpsProxyServer = httpsProxy(httpsProxyPort);
        example.executeHttpsProxy();
        documentPackage3 = example.eslClientWithHttpsProxy.getPackage(example.packageId);
        assertThat(documentPackage3, is(notNullValue()));
        //httpsProxyServer.stop();
//
//        documentPackage4 = example.eslClientWithHttpsProxyHasCredentials.getPackage(example.packageId4);
//        assertThat(documentPackage4, is(notNullValue()));

    }

    public static HttpProxyServer httpProxy(int port) {
        final HttpProxyServer httpProxyServer = DefaultHttpProxyServer.bootstrap().
                withPort(port).
                start();
        return httpProxyServer;
    }

    public static HttpProxyServer httpProxyHasCredentials(int port, final String acceptedUsername, final String acceptedPassword) {
        final HttpProxyServer httpProxyServer = DefaultHttpProxyServer.bootstrap().
                withPort(port).
                withProxyAuthenticator(new ProxyAuthenticator() {
                    @Override
                    public boolean authenticate(String s, String s2) {
                        if (acceptedUsername.equals(s) && acceptedPassword.equals(s2)) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }).
                start();
        return httpProxyServer;
    }

    public static HttpProxyServer httpsProxy(int port) {
//        CertificateMng certificateMng =  new CertificateMng();
        final HttpProxyServer httpProxyServer = DefaultHttpProxyServer.bootstrap().
//                withManInTheMiddle(certificateMng).
//                withSslEngineSource(certificateMng.selfSignedSslEngineSource).
                withManInTheMiddle(new SelfSignedMitmManager()).
                withPort(port).
                start();
        return httpProxyServer;
    }

    public static class CertificateMng implements MitmManager {
        SelfSignedSslEngineSource selfSignedSslEngineSource = new SelfSignedSslEngineSource(true);

        public SSLEngine serverSslEngine() {
            return selfSignedSslEngineSource.newSslEngine();
        }

        public SSLEngine clientSslEngineFor(SSLSession serverSslSession) {
            return selfSignedSslEngineSource.newSslEngine();
        }
    }

    public void simpleProxyExecution() throws Exception {

        HttpProxyServer proxyServer;
        CloseableHttpResponse response = null;
        //CloseableHttpClient httpclient = HttpClients.createDefault();

        CloseableHttpClient httpclient = null;
        try {
            httpclient = buildHttpClient();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            HttpHost target = new HttpHost("esl.silanis.com", 80, "https");

            HttpHost proxy = new HttpHost("10.0.4.40", httpsProxyPort, "https");

            RequestConfig config = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();
            HttpGet request = new HttpGet("/");

            request.setConfig(config);

            //proxyServer = DefaultHttpProxyServer.bootstrap().withPort(8000).start();

//            proxyServer = DefaultHttpProxyServer.bootstrap().plusActivityTracker(
//                    new ActivityTracker() {
//                        @Override
//                        public void bytesReceivedFromClient(
//                                FlowContext flowContext,
//                                int numberOfBytes) {
//                            bytesReceivedFromClient.addAndGet(numberOfBytes);
//                        }
//
//                        @Override
//                        public void requestReceivedFromClient(
//                                FlowContext flowContext,
//                                HttpRequest httpRequest) {
//                            requestsReceivedFromClient.incrementAndGet();
//                        }
//
//                        @Override
//                        public void bytesSentToServer(FullFlowContext flowContext,
//                                                      int numberOfBytes) {
//                            bytesSentToServer.addAndGet(numberOfBytes);
//                        }
//
//                        @Override
//                        public void requestSentToServer(
//                                FullFlowContext flowContext,
//                                HttpRequest httpRequest) {
//                            requestsSentToServer.incrementAndGet();
//                        }
//
//                        @Override
//                        public void bytesReceivedFromServer(
//                                FullFlowContext flowContext,
//                                int numberOfBytes) {
//                            bytesReceivedFromServer.addAndGet(numberOfBytes);
//                        }
//
//                        @Override
//                        public void responseReceivedFromServer(
//                                FullFlowContext flowContext,
//                                io.netty.handler.codec.http.HttpResponse httpResponse) {
//                            responsesReceivedFromServer.incrementAndGet();
//                        }
//
//                        @Override
//                        public void bytesSentToClient(FlowContext flowContext,
//                                                      int numberOfBytes) {
//                            bytesSentToClient.addAndGet(numberOfBytes);
//                        }
//
//                        @Override
//                        public void responseSentToClient(
//                                FlowContext flowContext,
//                                io.netty.handler.codec.http.HttpResponse httpResponse) {
//                            responsesSentToClient.incrementAndGet();
//                        }
//
//                        @Override
//                        public void clientConnected(InetSocketAddress clientAddress) {
//                            clientConnects.incrementAndGet();
//                        }
//
//                        @Override
//                        public void clientSSLHandshakeSucceeded(
//                                InetSocketAddress clientAddress,
//                                SSLSession sslSession) {
//                            clientSSLHandshakeSuccesses.incrementAndGet();
//                        }
//
//                        @Override
//                        public void clientDisconnected(
//                                InetSocketAddress clientAddress,
//                                SSLSession sslSession) {
//                            clientDisconnects.incrementAndGet();
//                        }
//                    }).
//                    withChainProxyManager(new ChainedProxyManager() {
//                        @Override
//                        public void lookupChainedProxies(HttpRequest httpRequest,
//                                                         Queue<ChainedProxy> chainedProxies) {
//                        }
//                    }).
//                    withManInTheMiddle(new SelfSignedMitmManager()).
//                    withFiltersSource(new HttpFiltersSourceAdapter() {
//                        @Override
//                        public HttpFilters filterRequest(HttpRequest originalRequest) {
//                            return new HttpFiltersAdapter(originalRequest) {
////                                @Override
//                                public HttpResponse clientToProxyRequest(
//                                        HttpObject httpObject) {
//                                    if (httpObject instanceof HttpRequest) {
//                                        requestPreMethodsSeen
//                                                .add(((HttpRequest) httpObject)
//                                                        .getMethod());
//                                    }
//                                    return null;
//                                }
//
////                                @Override
//                                public HttpResponse proxyToServerRequest(
//                                        HttpObject httpObject) {
//                                    if (httpObject instanceof HttpRequest) {
//                                        requestPostMethodsSeen
//                                                .add(((HttpRequest) httpObject)
//                                                        .getMethod());
//                                    }
//                                    return null;
//                                }
//
////                                @Override
//                                public HttpObject serverToProxyResponse(
//                                        HttpObject httpObject) {
//                                    if (httpObject instanceof HttpResponse) {
//                                        responsePreOriginalRequestMethodsSeen
//                                                .add(originalRequest.getMethod());
//                                    } else if (httpObject instanceof HttpContent) {
//                                        responsePreBody.append(((HttpContent) httpObject)
//                                                .content().toString(
//                                                        Charset.forName("UTF-8")));
//                                    }
//                                    return httpObject;
//                                }
//
////                                @Override
//                                public HttpObject proxyToClientResponse(
//                                        HttpObject httpObject) {
//                                    if (httpObject instanceof HttpResponse) {
//                                        responsePostOriginalRequestMethodsSeen
//                                                .add(originalRequest.getMethod());
//                                    } else if (httpObject instanceof HttpContent) {
//                                        responsePostBody.append(((HttpContent) httpObject)
//                                                .content().toString(
//                                                        Charset.forName("UTF-8")));
//                                    }
//                                    return httpObject;
//                                }
//                            };
//                        }
//                    }).
//                    withPort(httpsProxyPort).
//                    start();

            System.out.println("Executing request " + request.getRequestLine() + " to " + target + " via " + proxy);

            try {
                response = httpclient.execute(target, request);
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                EntityUtils.consume(response.getEntity());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                response.close();
//                proxyServer.stop();
            }
        }
        finally {
            httpclient.close();
        }
    }

    private DefaultHttpClient buildHttpClient() throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        SSLSocketFactory sf = new SSLSocketFactory(
                new TrustSelfSignedStrategy(), new X509HostnameVerifier() {
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }

            public void verify(String host, String[] cns,
                               String[] subjectAlts)
                    throws SSLException {
            }

            public void verify(String host, X509Certificate cert)
                    throws SSLException {
            }

            public void verify(String host, SSLSocket ssl)
                    throws IOException {
            }
        });
        Scheme scheme = new Scheme("https", 8003, sf);
        httpClient.getConnectionManager().getSchemeRegistry().register(scheme);
        return httpClient;
    }
}
