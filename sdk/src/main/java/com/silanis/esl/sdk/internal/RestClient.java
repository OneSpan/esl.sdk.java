package com.silanis.esl.sdk.internal;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.ProxyConfiguration;
import com.silanis.esl.sdk.io.Streams;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Collection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

public class RestClient {

    public static final String CHARSET_UTF_8 = "UTF-8";

    public static final String ESL_API_VERSION = "10.6";
    public static final String ESL_API_VERSION_HEADER = "esl-api-version=" + ESL_API_VERSION;

    public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
    public static final String ESL_CONTENT_TYPE_APPLICATION_JSON = CONTENT_TYPE_APPLICATION_JSON + "; " + ESL_API_VERSION_HEADER;

    public static final String HEADER_KEY_ACCEPT = "Accept";
    public static final String ACCEPT_TYPE_APPLICATION_JSON = "application/json";
    public static final String ESL_ACCEPT_TYPE_APPLICATION_JSON = ACCEPT_TYPE_APPLICATION_JSON + "; " + ESL_API_VERSION_HEADER;

    private final ResponseHandler<byte[]> bytesHandler = new BytesHandler();
    private final ResponseHandler<String> jsonHandler = new JsonHandler();

    private final String apiToken;
    private final Support support = new Support();
    private final boolean allowAllSSLCertificates;

    private ProxyConfiguration proxyConfiguration;

    public RestClient(String apiToken) {
        this(apiToken, false);
    }

    public RestClient(String apiToken, boolean allowAllSSLCertificates) {
        this(apiToken, allowAllSSLCertificates, null);
    }

    public RestClient(String apiToken, ProxyConfiguration proxyConfiguration) {
        this(apiToken, false, proxyConfiguration);
    }

    public RestClient(String apiToken, boolean allowAllSSLCertificates, ProxyConfiguration proxyConfiguration) {
        this.apiToken = apiToken;
        this.allowAllSSLCertificates = allowAllSSLCertificates;
        this.proxyConfiguration = proxyConfiguration;
    }

    public String post(String path, String jsonPayload) throws IOException, HttpException, URISyntaxException, RequestException {
        support.logRequest("POST", path, jsonPayload);

        HttpPost post = new HttpPost(path);
        post.addHeader(buildAcceptHeaderForEslApi());
        if (jsonPayload != null) {
            StringEntity body = new StringEntity(jsonPayload, Charset.forName(CHARSET_UTF_8));

            body.setContentType(ESL_CONTENT_TYPE_APPLICATION_JSON);

            post.setEntity(body);
        }

        return execute(post, jsonHandler);
    }

    public String put(String path, String jsonPayload) throws IOException, RequestException {
        support.logRequest("PUT", path, jsonPayload);

        HttpPut put = new HttpPut(path);
        put.addHeader(buildAcceptHeaderForEslApi());
        StringEntity body = new StringEntity(jsonPayload, Charset.forName("UTF-8"));

        body.setContentType(ESL_CONTENT_TYPE_APPLICATION_JSON);
        put.setEntity(body);

        return execute(put, jsonHandler);
    }

    public String postMultipartFile(String path, String fileName, byte[] fileBytes, String jsonPayload) throws IOException, HttpException, URISyntaxException, RequestException {
        support.logRequest("POST", path, jsonPayload);

        final MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addPart("payload", buildPartForPayload(jsonPayload));
        multipartEntityBuilder.addPart("file", buildPartForFile(fileBytes, fileName));

        HttpPost post = new HttpPost(path);

        post.setEntity(multipartEntityBuilder.build());

        return execute(post, jsonHandler);
    }

    public String postMultipartPackage(String path, Collection<Document> documents, String jsonPayload) throws IOException, HttpException, URISyntaxException, RequestException {
        support.logRequest("POST", path, jsonPayload);

        final MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addPart("payload", buildPartForPayload(jsonPayload));

        for (com.silanis.esl.sdk.Document document : documents) {
            multipartEntityBuilder.addPart("file", buildPartForFile(document));
        }

        HttpPost post = new HttpPost(path);

        post.setEntity(multipartEntityBuilder.build());
        return execute(post, jsonHandler);
    }

    private StringBody buildPartForPayload(String jsonPayload) {
        return new StringBody(jsonPayload, ContentType.create("text/plain", Consts.ASCII));
    }

    private ContentBody buildPartForFile(byte[] content, String fileName) {
        return buildPartForFile(content, fileName, fileName);
    }

    private ByteArrayBody buildPartForFile(Document document) {
        return buildPartForFile(document.getContent(), document.getFileName(), document.getName());
    }

    private ByteArrayBody buildPartForFile(byte[] content, String fileName, String name) {
        String contentType = MimeTypeUtils.getContentTypeByFileName(fileName);
        return new ByteArrayBody(content, ContentType.create(contentType), name);
    }

    protected void addAuthorizationHeader(HttpUriRequest request) {
        request.setHeader("Authorization", "Basic " + apiToken);
    }

    private <T> T execute(HttpUriRequest request, ResponseHandler<T> handler) throws IOException, RequestException {

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
            addAuthorizationHeader(request);
            support.log(request);
            CloseableHttpResponse response = client.execute(request);

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
            if (null != client) {
                client.close();
            }
        }
    }

    private CloseableHttpClient buildHttpClient() throws HttpException {
        final HttpClientBuilder httpClientBuilder = HttpClients.custom();
        if (allowAllSSLCertificates) {
            httpClientBuilder.setSSLSocketFactory(buildSSLSocketFactory());
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

    private SSLConnectionSocketFactory buildSSLSocketFactory() throws HttpException {

        //Disabling all checks that SSL certificate is valid. We are actually calling e-SignLive anyways.
        //Our client library should implicitly trust our e-SignLive server. This also allows testing against
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

    public String get(String path) throws IOException, HttpException, URISyntaxException, RequestException {
        return get(path, ESL_ACCEPT_TYPE_APPLICATION_JSON);
    }

    public String get(String path, String acceptType) throws IOException, HttpException, URISyntaxException, RequestException {
        support.logRequest("GET", path);
        HttpGet get = new HttpGet(path);
        get.addHeader(buildAcceptHeader(acceptType));

        return execute(get, jsonHandler);
    }

    private Header buildAcceptHeaderForEslApi() {
        return buildAcceptHeader(ESL_ACCEPT_TYPE_APPLICATION_JSON);
    }

    private Header buildAcceptHeader(String acceptType) {
        return new BasicHeader(HEADER_KEY_ACCEPT, acceptType);
    }

    public byte[] getBytes(String path) throws IOException, HttpException, URISyntaxException, RequestException {
        support.logRequest("GET", path);
        HttpGet get = new HttpGet(path);

        return execute(get, bytesHandler);
    }

    public byte[] getBytesAsOctetStream(String path) throws IOException, HttpException, URISyntaxException, RequestException {
        support.logRequest("GET", path);
        HttpGet get = new HttpGet(path);
        get.addHeader(buildAcceptHeader("application/octet-stream"));

        return execute(get, bytesHandler);
    }

    public String delete(String path) throws HttpException, IOException, URISyntaxException, RequestException {
        support.logRequest("DELETE", path);
        HttpDelete delete = new HttpDelete(path);
        delete.addHeader(buildAcceptHeaderForEslApi());

        return execute(delete, jsonHandler);
    }

    private static interface ResponseHandler<T> {
        T extract(InputStream input);
    }

    private class BytesHandler implements ResponseHandler<byte[]> {

        public byte[] extract(InputStream input) {
            return Streams.toByteArray(input);
        }
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
