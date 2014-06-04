package com.silanis.esl.sdk.internal;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.io.Streams;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

public class RestClient {

    public static final String CHARSET_UTF_8 = "UTF-8";
    private final ResponseHandler<byte[]> bytesHandler = new BytesHandler();
    private final ResponseHandler<String> jsonHandler = new JsonHandler();

    private final String apiToken;
    private final Support support = new Support();

    public RestClient(String apiToken) {
        this.apiToken = apiToken;
    }

    public String post(String path, String jsonPayload) throws IOException, HttpException, URISyntaxException {
        support.logRequest("POST", path, jsonPayload);

        HttpPost post = new HttpPost( path );
        if ( jsonPayload != null ) {
            StringEntity body = new StringEntity(jsonPayload, Charset.forName(CHARSET_UTF_8));

            body.setContentType("application/json");

            post.setEntity(body);
        }

        return execute(post, jsonHandler);
    }

    public String put(String path, String jsonPayload) throws IOException {
        support.logRequest("PUT", path, jsonPayload);

        HttpPut put = new HttpPut( path );
        StringEntity body = new StringEntity(jsonPayload, Charset.forName("UTF-8"));

        body.setContentType("application/json");
        put.setEntity(body);

        return execute(put, jsonHandler);
    }

    public void postMultipartFile(String path, String fileName, byte[] fileBytes, String jsonPayload) throws IOException, HttpException, URISyntaxException {
        support.logRequest("POST", path, jsonPayload);

        MultipartEntity multipart = new MultipartEntity();
        String contentType = MimeTypeUtils.getContentTypeByFileName(fileName);

        multipart.addPart("payload", new StringBody(jsonPayload));
        multipart.addPart("file", new ByteArrayBody(fileBytes, contentType, fileName));

        HttpPost post = new HttpPost( path );

        post.setEntity(multipart);

        execute(post, jsonHandler);
    }

    protected void addAuthorizationHeader(HttpUriRequest request) {
        request.setHeader("Authorization", "Basic " + apiToken);
    }

    private <T> T execute(HttpUriRequest request, ResponseHandler<T> handler) throws IOException {
        HttpClient client = new DefaultHttpClient();

        addAuthorizationHeader(request);

        try {
            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() >= 400) {
                String errorDetails = Streams.toString(response.getEntity().getContent());
                throw new CommunicationException(request.getRequestLine().getMethod(),
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

    public String get(String path) throws IOException, HttpException, URISyntaxException {
        support.logRequest("GET", path);
        HttpGet get = new HttpGet( path );
        Header header = new BasicHeader("Accept", "application/json");
        get.addHeader(header);

        return execute(get, jsonHandler);
    }

    public byte[] getBytes(String path) throws IOException, HttpException, URISyntaxException {
        support.logRequest("GET", path);
        HttpGet get = new HttpGet( path );

        return execute(get, bytesHandler);
    }

    public String delete(String path) throws HttpException, IOException, URISyntaxException {
        support.logRequest("DELETE", path);
        HttpDelete delete = new HttpDelete( path );

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