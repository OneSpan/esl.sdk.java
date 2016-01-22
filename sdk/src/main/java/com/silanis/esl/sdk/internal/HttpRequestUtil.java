package com.silanis.esl.sdk.internal;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * Created by schoi on 2/13/15.
 */
public class HttpRequestUtil {

    public static final String SESSION_TOKEN_COOKIE_VALUE_KEY = "ESIGNLIVE_SESSION_ID";
    public static final String TEMP_SESSION_TOKEN_COOKIE_VALUE_KEY = "ESIGNLIVE_TEMP_TOKEN";
    public static final String SESSION_TOKEN_COOKIE_KEY = "Cookie";

    public static final String API_KEY_AUTHENTICATION_AUTHORIZATION_KEY = "Authorization";
    public static final String API_KEY_AUTHENTICATION_BASIC_PREFIX = "Basic ";

    public static String getUrlContent(String requestedURL){
        String urlContent = "";
        InputStream is = null;
        try {
            URL url = new URL(requestedURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setInstanceFollowRedirects(false);

            final String location = conn.getHeaderField("Location");
            final String cookieSessionToken = extractSessionCookieValue(conn);
            final String cookieTempTokenValue = extractTempTokenCookieValue(conn);
            final URL redirectURL = new URL(location);

            HttpURLConnection redirectRequest = (HttpURLConnection)  redirectURL.openConnection();
            setAuthentication(redirectRequest, AuthRequestParameters.empty());

            redirectRequest.setRequestProperty("Cookie", buildSessionTokenCookieValue(cookieSessionToken) + ";" + buildTempTokenCookieValue(cookieTempTokenValue));

            // open the stream and put it into BufferedReader
            is = redirectRequest.getInputStream();
            urlContent = IOUtils.toString(new BufferedReader(new InputStreamReader(is)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
        }
        return urlContent;
    }

    private static void setAuthentication(HttpURLConnection request,  AuthRequestParameters authRequestParameters) {
        if (authRequestParameters.hasSessionToken()) {
            request.addRequestProperty(SESSION_TOKEN_COOKIE_KEY, buildSessionTokenCookieValue(authRequestParameters.getSessionToken()));
        } else if (authRequestParameters.hasApiKey()) {
            request.addRequestProperty(API_KEY_AUTHENTICATION_AUTHORIZATION_KEY, API_KEY_AUTHENTICATION_BASIC_PREFIX + authRequestParameters.getApiKey());
        } else if (authRequestParameters.hasTempToken()) {
            request.addRequestProperty(SESSION_TOKEN_COOKIE_KEY, buildTempTokenCookieValue(authRequestParameters.getTempToken()));
        }  else if (authRequestParameters.hasConnectorsAuth()) {
            request.addRequestProperty(API_KEY_AUTHENTICATION_AUTHORIZATION_KEY, API_KEY_AUTHENTICATION_BASIC_PREFIX + authRequestParameters.getConnectorsAuth());
        }
    }

    private static String buildSessionTokenCookieValue(String sessionTokenValue) {
        return SESSION_TOKEN_COOKIE_VALUE_KEY + "=" + sessionTokenValue;
    }
    private static String buildTempTokenCookieValue(String sessionTokenValue) {
        return TEMP_SESSION_TOKEN_COOKIE_VALUE_KEY + "=" + sessionTokenValue;
    }

    private static String extractSessionCookieValue(HttpURLConnection request) {
        return extractSessionCookieValue(request, SESSION_TOKEN_COOKIE_VALUE_KEY);
    }

    private static String extractTempTokenCookieValue(HttpURLConnection request) {
        return extractSessionCookieValue(request, TEMP_SESSION_TOKEN_COOKIE_VALUE_KEY);
    }

    private static String extractSessionCookieValue(HttpURLConnection request, String sessionKeyString) {
        final String setCookieValue = request.getHeaderField("Set-Cookie");
        if(isNotEmpty(setCookieValue) && contains(setCookieValue, sessionKeyString) && hasCookieValue(setCookieValue, sessionKeyString)) {
            final String SESSION_ID_KEY_WITH_EQUAL = sessionKeyString + "=";
            final int startOfSessionIdValue = setCookieValue.indexOf(SESSION_ID_KEY_WITH_EQUAL) + SESSION_ID_KEY_WITH_EQUAL.length();
            final String endOfCookieValue = setCookieValue.substring(startOfSessionIdValue);
            final int endOfSessionIdValue = endOfCookieValue.indexOf(";");
            return endOfCookieValue.substring(0, endOfSessionIdValue);
        }
        return "";
    }

    private static boolean hasCookieValue(String setCookieValue, String sessionKeyString) {
        return isNotBlank(remove(remove(setCookieValue, sessionKeyString), '='));
    }
}
