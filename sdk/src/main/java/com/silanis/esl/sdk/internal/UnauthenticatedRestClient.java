package com.silanis.esl.sdk.internal;

import com.silanis.esl.sdk.ProxyConfiguration;
import com.silanis.esl.sdk.io.Streams;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mpoitras on 22/04/14.
 */
public class UnauthenticatedRestClient extends Client {

    private final ResponseHandler<String> jsonHandler = new JsonHandler();

    public UnauthenticatedRestClient() {
    }

    public UnauthenticatedRestClient(ProxyConfiguration proxyConfiguration) {
        this(false, proxyConfiguration, false);
    }

    public UnauthenticatedRestClient(boolean allowAllSSLCertificates, ProxyConfiguration proxyConfiguration, boolean useSystemProperties) {
        this.allowAllSSLCertificates = allowAllSSLCertificates;
        this.proxyConfiguration = proxyConfiguration;
        this.useSystemProperties = useSystemProperties;
    }

    public String get(String path) throws IOException, RequestException {
        support.logRequest("GET", path);
        HttpGet get = new HttpGet(path);
        get.addHeader(new BasicHeader(RestClient.HEADER_KEY_ACCEPT, RestClient.ESL_ACCEPT_TYPE_APPLICATION_JSON));
        return execute(get, jsonHandler);
    }

    private <T> T execute(HttpUriRequest request, ResponseHandler<T> handler) throws IOException, RequestException {
        //Disabling all checks that SSL certificate is valid. We are actually calling OneSpan Sign anyways.
        //Our client library should implicitly trust our OneSpan Sign server. This also allows testing against
        //server with Self-signed certificates.

        CloseableHttpClient client;
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
            } else if (response.getStatusLine().getStatusCode() == 204) {
                return null;
            } else {
                InputStream bodyContent = response.getEntity().getContent();

                return handler.extract(bodyContent);
            }
        } finally {
            client.getConnectionManager().shutdown();
        }
    }

}
