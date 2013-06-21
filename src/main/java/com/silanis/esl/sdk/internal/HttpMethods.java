package com.silanis.esl.sdk.internal;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import static com.silanis.esl.sdk.io.Streams.toByteArray;

public class HttpMethods {

    private HttpMethods() {
    }

    public static byte[] postHttp( String apiToken, String path, HttpEntity entity ) throws URISyntaxException, IOException, HttpException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost( path );


        httpPost.setHeader( "Authorization", "Basic " + apiToken );
        httpPost.setEntity( entity );

        HttpResponse response = httpclient.execute( httpPost );
        InputStream in = response.getEntity().getContent();

        return toByteArray( in );
    }

    public static byte[] putHttp( String apiToken, String path, HttpEntity entity ) throws URISyntaxException, IOException, HttpException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPut httpPut = new HttpPut( path );

        httpPut.setHeader( "Authorization", "Basic " + apiToken );
        httpPut.setEntity( entity );

        HttpResponse response = httpclient.execute( httpPut );
        InputStream in = response.getEntity().getContent();

        return toByteArray( in );
    }

    public static byte[] getHttp( String apiToken, String path ) throws URISyntaxException, IOException, HttpException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet( path );

        httpGet.setHeader( "Authorization", "Basic " + apiToken );

        HttpResponse response = httpclient.execute( httpGet );
        InputStream in = response.getEntity().getContent();

        return toByteArray( in );
    }

    public static byte[] deleteHttp( String apiToken, String path ) throws URISyntaxException, IOException, HttpException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpDelete httpDelete = new HttpDelete( path );

        httpDelete.setHeader( "Authorization", "Basic " + apiToken );

        HttpResponse response = httpclient.execute( httpDelete );
        InputStream in = response.getEntity().getContent();

        return toByteArray( in );
    }

}
