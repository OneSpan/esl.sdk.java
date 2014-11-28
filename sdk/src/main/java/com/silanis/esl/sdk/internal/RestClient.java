package com.silanis.esl.sdk.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Collection;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.io.Streams;

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
    private HttpHost proxy;

    public RestClient(String apiToken) {
        this.apiToken = apiToken;
    }
    
    public RestClient(String apiToken, HttpHost proxy) {
        this.apiToken = apiToken;
        this.proxy = proxy;
    }

    public String post(String path, String jsonPayload) throws IOException, HttpException, URISyntaxException, RequestException {
        support.logRequest("POST", path, jsonPayload);

        HttpPost post = new HttpPost( path );
        post.addHeader(buildAcceptHeaderForEslApi());
        if ( jsonPayload != null ) {
            StringEntity body = new StringEntity(jsonPayload, Charset.forName(CHARSET_UTF_8));

            body.setContentType(ESL_CONTENT_TYPE_APPLICATION_JSON);

            post.setEntity(body);
        }

        return execute(post, jsonHandler);
    }

    public String put(String path, String jsonPayload) throws IOException, RequestException {
        support.logRequest("PUT", path, jsonPayload);

        HttpPut put = new HttpPut( path );
        put.addHeader(buildAcceptHeaderForEslApi());
        StringEntity body = new StringEntity(jsonPayload, Charset.forName("UTF-8"));

        body.setContentType(ESL_CONTENT_TYPE_APPLICATION_JSON);
        put.setEntity(body);

        return execute(put, jsonHandler);
    }

    public void postMultipartFile(String path, String fileName, byte[] fileBytes, String jsonPayload) throws IOException, HttpException, URISyntaxException, RequestException {
        support.logRequest("POST", path, jsonPayload);

        MultipartEntity multipart = new MultipartEntity();
        String contentType = MimeTypeUtils.getContentTypeByFileName(fileName);

        multipart.addPart("payload", new StringBody(jsonPayload));
        multipart.addPart("file", new ByteArrayBody(fileBytes, contentType, fileName));

        HttpPost post = new HttpPost( path );

        post.setEntity(multipart);

        execute(post, jsonHandler);
    }

    public String postMultipartPackage(String path, Collection<Document> documents, String jsonPayload) throws IOException, HttpException, URISyntaxException, RequestException {
        support.logRequest("POST", path, jsonPayload);

        MultipartEntity multipart = new MultipartEntity();
        multipart.addPart("payload", new StringBody(jsonPayload));

        for(com.silanis.esl.sdk.Document document : documents) {
            String contentType = MimeTypeUtils.getContentTypeByFileName(document.getFileName());
            multipart.addPart("file", new ByteArrayBody(document.getContent(), contentType, document.getName()));
        }

        HttpPost post = new HttpPost(path);

        post.setEntity(multipart);
        return execute(post, jsonHandler);
    }

    protected void addAuthorizationHeader(HttpUriRequest request) {
        request.setHeader("Authorization", "Basic " + apiToken);
    }
    
    private <T> T execute(HttpUriRequest request, ResponseHandler<T> handler) throws IOException, RequestException {
        HttpClient client = new DefaultHttpClient();
        if(proxy != null){
        	//We configure a proxy if one is defined.
        	client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
        }

        addAuthorizationHeader(request);

        try {
            support.log(request);
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

    public String get(String path) throws IOException, HttpException, URISyntaxException, RequestException {
        return get(path, ESL_ACCEPT_TYPE_APPLICATION_JSON);
    }

    public String get(String path, String acceptType) throws IOException, HttpException, URISyntaxException, RequestException {
        support.logRequest("GET", path);
        HttpGet get = new HttpGet( path );
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
        HttpGet get = new HttpGet( path );

        return execute(get, bytesHandler);
    }

    public String delete(String path) throws HttpException, IOException, URISyntaxException, RequestException {
        support.logRequest("DELETE", path);
        HttpDelete delete = new HttpDelete( path );
        delete.addHeader(buildAcceptHeaderForEslApi());

        return execute(delete, jsonHandler);
    }

    private static interface ResponseHandler<T> {
        T extract(InputStream input);
    }

    private class BytesHandler implements ResponseHandler<byte[]> {

        public byte[] extract(InputStream input) {
            return Streams.toByteArray( input );
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
