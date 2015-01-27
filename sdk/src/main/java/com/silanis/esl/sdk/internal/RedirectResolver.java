package com.silanis.esl.sdk.internal;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by schoi on 1/27/15.
 */
public class RedirectResolver {

    public static String resolveUrlAfterRedirect(String url){
        try {
            URL url1 = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.connect();

            final String locationHeader = httpURLConnection.getHeaderField("Location");

            return StringUtils.isNotEmpty(locationHeader) ? locationHeader : httpURLConnection.getURL().toString();

        } catch (MalformedURLException e) {
            return url;
        } catch (IOException e) {
            return url;
        }

    }
}
