package com.silanis.esl.sdk.internal;

import org.apache.http.HttpException;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.IOException;
import java.net.URISyntaxException;

public class RestClient {

    private final String apiToken;
    private final Support support = new Support();

    public RestClient(String apiToken) {
        this.apiToken = apiToken;
    }

    public String post(String path, String jsonPayload) throws IOException, HttpException, URISyntaxException {
        support.logRequest("POST", path, jsonPayload);

        StringEntity entity = new StringEntity(jsonPayload);
        entity.setContentType("application/json");

        byte[] response = HttpMethods.postHttp(apiToken, path, entity);
        String json = new String(response, "UTF-8");

        support.logResponse(json);

        return json;
    }

    public String get(String path) throws IOException, HttpException, URISyntaxException {
        support.logRequest("GET", path);

        byte[] response = HttpMethods.getHttp(apiToken, path);
        String json = new String(response, "UTF-8");

        support.logResponse(json);
        return json;
    }

    public byte[] getBytes(String path) throws IOException, HttpException, URISyntaxException {
        support.logRequest("GET", path);

        return HttpMethods.getHttp(apiToken, path);
    }

    public String delete(String path) throws HttpException, IOException, URISyntaxException {
        support.logRequest("DELETE", path);

        byte[] response = HttpMethods.deleteHttp(apiToken, path);

        return new String(response, "UTF-8");
    }

    public void postMultipartFile(String path, String fileName, byte[] fileBytes, String jsonPayload) throws IOException, HttpException, URISyntaxException {

        MultipartEntity entity = new MultipartEntity();
        String contentType = MimeTypeUtils.getContentTypeByFileName(fileName);
        entity.addPart("payload", new StringBody(jsonPayload));
        entity.addPart("file", new ByteArrayBody(fileBytes, contentType, fileName));

        HttpMethods.postHttp( apiToken, path, entity );
    }
}