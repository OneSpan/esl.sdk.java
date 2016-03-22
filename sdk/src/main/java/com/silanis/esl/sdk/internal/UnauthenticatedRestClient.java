package com.silanis.esl.sdk.internal;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.io.Streams;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

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
        HttpClient client = new DefaultHttpClient();

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
