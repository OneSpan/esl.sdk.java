package com.silanis.esl.sdk.internal;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.io.Streams;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * Created by mpoitras on 22/04/14.
 */
public class UnauthenticatedRestClient {

    private final Support support = new Support();
    private final ResponseHandler<String> jsonHandler = new JsonHandler();

    public String get(String path) throws IOException, RequestException {
        support.logRequest("GET", path);
        HttpGet get = new HttpGet( path );
        get.addHeader(new BasicHeader(RestClient.HEADER_KEY_ACCEPT, RestClient.ESL_ACCEPT_TYPE_APPLICATION_JSON));
        return execute(get, jsonHandler);
    }

    private static <T> T execute(HttpUriRequest request, ResponseHandler<T> handler) throws IOException, RequestException {
        //Disabling all checks that SSL certificate is valid. We are actually calling eSignLive anyways.
        //Our client library should implicitly trust our eSignLive server. This also allows testing against
        //server with Self-signed certificates.

        CloseableHttpClient client = null;
        try {
            client = buildHttpClient();
        } catch (HttpException e) {
            throw new RequestException(request.getRequestLine().getMethod(),
                    request.getRequestLine().getUri(),
                    500,
                    "No SSL Socket Factory",
                    "Could not build request because of SSL socket Factory");
        }

        try {
            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() >= 400) {
                String errorDetails = Streams.toString(response.getEntity().getContent());
                throw new RequestException(request.getRequestLine().getMethod(),
                                                 request.getRequestLine().getUri(),
                                                 response.getStatusLine().getStatusCode(),
                                                 response.getStatusLine().getReasonPhrase(),
                                                 errorDetails);
            } else if (response.getStatusLine().getStatusCode() == 204 ) {
                return null;
            } else {
                InputStream bodyContent = response.getEntity().getContent();

                return handler.extract(bodyContent);
            }
        }
        finally {
            client.getConnectionManager().shutdown();
        }
    }

    private static CloseableHttpClient buildHttpClient() throws HttpException {
        final HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setSSLSocketFactory(buildSSLSocketFactory());

        return httpClientBuilder.build();
    }

    private static SSLConnectionSocketFactory buildSSLSocketFactory() throws HttpException {

        //Disabling all checks that SSL certificate is valid. We are actually calling eSignLive anyways.
        //Our client library should implicitly trust our eSignLive server. This also allows testing against
        //server with Self-signed certificates.
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null,
                    new TrustManager[]{new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(
                                X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(
                                X509Certificate[] certs, String authType) {
                        }
                    }}, new SecureRandom());
            return new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (KeyManagementException e) {
            throw new HttpException("Problem configuring SSL Socket factory", e);
        } catch (NoSuchAlgorithmException e) {
            throw new HttpException("Problem configuring SSL Socket factory", e);
        }
    }

    private static interface ResponseHandler<T> {
        T extract(InputStream input);
    }

    private class JsonHandler implements ResponseHandler<String> {

        public String extract(InputStream input) {
            try {

                String responseBody = Streams.toString(input);

                support.logResponse(responseBody);
                return responseBody;
            } catch (UnsupportedEncodingException e) {
                throw new EslException("", e);
            }
        }
    }
}
