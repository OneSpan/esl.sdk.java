package com.silanis.esl.sdk.internal;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.ProxyConfiguration;
import com.silanis.esl.sdk.io.DownloadedFile;
import com.silanis.esl.sdk.io.Streams;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * Created by schoi on 24/04/19.
 */
public abstract class Client {
    protected final Support support = new Support();
    protected boolean allowAllSSLCertificates;
    protected boolean useSystemProperties;

    protected ProxyConfiguration proxyConfiguration;

    protected CloseableHttpClient buildHttpClient() throws HttpException {
        final HttpClientBuilder httpClientBuilder = HttpClients.custom();
        if (allowAllSSLCertificates) {
            httpClientBuilder.setSSLSocketFactory(buildSSLSocketFactory());
        }

        if (useSystemProperties) {
            httpClientBuilder.useSystemProperties();
        }

        if (proxyConfiguration != null) {
            if (proxyConfiguration.hasCredentials()) {
                httpClientBuilder.setDefaultCredentialsProvider(buildCredentialsConfiguration(proxyConfiguration));
            }
            httpClientBuilder.setDefaultRequestConfig(buildProxyConfiguration(proxyConfiguration));
            return httpClientBuilder.build();
        } else {
            return httpClientBuilder.build();
        }
    }

    private RequestConfig buildProxyConfiguration(ProxyConfiguration proxyConfiguration) {
        return RequestConfig.custom()
                .setProxy(new HttpHost(proxyConfiguration.getHost(), proxyConfiguration.getPort(), proxyConfiguration.getScheme()))
                .build();
    }

    private CredentialsProvider buildCredentialsConfiguration(ProxyConfiguration proxyConfiguration) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope(proxyConfiguration.getHost(), proxyConfiguration.getPort()),
                new UsernamePasswordCredentials(proxyConfiguration.getUserName(), proxyConfiguration.getPassword()));
        return credentialsProvider;
    }

    protected SSLConnectionSocketFactory buildSSLSocketFactory() throws HttpException {
        try {
            // Use the default SSL context, which validates certificates
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, new SecureRandom());

            // Use the default hostname verifier
            return new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            throw new HttpException("Problem configuring TLS Socket factory", e);
        }
    }


    protected interface ResponseHandler<T> {
        T extract(InputStream input);
    }

    protected class BytesHandler implements ResponseHandler<DownloadedFile> {

        public DownloadedFile extract(InputStream input) {
            return new DownloadedFile("", Streams.toByteArray(input));
        }
    }

    protected class JsonHandler implements ResponseHandler<String> {

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