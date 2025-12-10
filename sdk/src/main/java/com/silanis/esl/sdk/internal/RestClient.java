package com.silanis.esl.sdk.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silanis.esl.api.model.DocumentInfo;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.apitoken.ApiToken;
import com.silanis.esl.sdk.apitoken.ApiTokenAccessRequest;
import com.silanis.esl.sdk.apitoken.ApiTokenConfig;
import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.ProxyConfiguration;
import com.silanis.esl.sdk.io.DownloadedFile;
import com.silanis.esl.sdk.io.Streams;

import java.net.URLEncoder;
import java.util.*;

import com.silanis.esl.sdk.oauth.OAuthAccessToken;
import com.silanis.esl.sdk.oauth.OAuthTokenConfig;
import com.silanis.esl.sdk.oauth.Oauth2TokenManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static com.silanis.esl.sdk.internal.HttpUtil.percentDecode;
import static com.silanis.esl.sdk.internal.MimeTypeUtils.getContentTypeByFileName;

public class RestClient extends Client {

    public static final String CHARSET_UTF_8 = "UTF-8";

    public static final String ESL_API_VERSION = "11.66.0";
    public static final String ESL_API_USER_AGENT = "Java SDK v" + ESL_API_VERSION;
    public static final String ESL_API_VERSION_HEADER = "esl-api-version=" + ESL_API_VERSION;

    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
    public static final String ESL_CONTENT_TYPE_APPLICATION_JSON = CONTENT_TYPE_APPLICATION_JSON + "; " + ESL_API_VERSION_HEADER;

    public static final String HEADER_KEY_ACCEPT = "Accept";
    public static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";
    public static final String ACCEPT_TYPE_APPLICATION_JSON = "application/json";
    public static final String ACCEPT_TYPE_APPLICATION_OCTET_STREAM = "application/octet-stream";
    public static final String ACCEPT_TYPE_APPLICATION = "*/*";
    public static final String ESL_ACCEPT_TYPE_APPLICATION_JSON = ACCEPT_TYPE_APPLICATION_JSON + "; " + ESL_API_VERSION_HEADER;
    public static final String ESL_ACCEPT_TYPE_APPLICATION_OCTET_STREAM = ACCEPT_TYPE_APPLICATION_OCTET_STREAM + "; " + ESL_API_VERSION_HEADER;
    public static final String ESL_ACCEPT_TYPE_APPLICATION = ACCEPT_TYPE_APPLICATION + "; " + ESL_API_VERSION_HEADER;

    private final BytesHandler bytesHandler = new BytesHandler();
    private final ResponseHandler<String> jsonHandler = new JsonHandler();
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final Oauth2TokenManager oauth2TokenManager = new Oauth2TokenManager();

    private final String apiKey;
    private final ApiTokenConfig apiTokenConfig;
    private final OAuthTokenConfig oauthTokenConfig;
    private ApiToken apiToken = null;
    private OAuthAccessToken oAuthAccessToken = null;
    private final Map<String, String> additionalHeaders;

    public RestClient(String apiKey) {
        this(apiKey, false);
    }

    public RestClient(String apiKey, boolean allowAllSSLCertificates) {
        this(apiKey, allowAllSSLCertificates, null);
    }

    public RestClient(String apiKey, ProxyConfiguration proxyConfiguration) {
        this(apiKey, false, proxyConfiguration);
    }

    public RestClient(String apiKey, boolean allowAllSSLCertificates, ProxyConfiguration proxyConfiguration) {
        this(apiKey, allowAllSSLCertificates, proxyConfiguration, false, new HashMap<String, String>());
    }

    public RestClient(String apiKey, boolean allowAllSSLCertificates, ProxyConfiguration proxyConfiguration, boolean useSystemProperties, Map<String, String> headers) {
        this.apiKey = apiKey;
        this.apiTokenConfig = null;
        this.oauthTokenConfig = null;
        this.allowAllSSLCertificates = allowAllSSLCertificates;
        this.proxyConfiguration = proxyConfiguration;
        this.useSystemProperties = useSystemProperties;
        this.additionalHeaders = headers;
    }

    public RestClient(ApiTokenConfig apiTokenConfig, boolean allowAllSSLCertificates, ProxyConfiguration proxyConfiguration, boolean useSystemProperties, Map<String, String> headers) {
        this.apiKey = null;
        this.oauthTokenConfig = null;
        this.apiTokenConfig = apiTokenConfig;
        this.allowAllSSLCertificates = allowAllSSLCertificates;
        this.proxyConfiguration = proxyConfiguration;
        this.useSystemProperties = useSystemProperties;
        this.additionalHeaders = headers;
    }

    public RestClient(OAuthTokenConfig oauthTokenConfig, boolean allowAllSSLCertificates, ProxyConfiguration proxyConfiguration, boolean useSystemProperties, Map<String, String> headers) {
        this.apiKey = null;
        this.apiTokenConfig = null;
        this.oauthTokenConfig = oauthTokenConfig;
        this.allowAllSSLCertificates = allowAllSSLCertificates;
        this.proxyConfiguration = proxyConfiguration;
        this.useSystemProperties = useSystemProperties;
        this.additionalHeaders = headers;
    }

    public String post(String path, String jsonPayload) throws IOException, RequestException {
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
        StringEntity body = new StringEntity(jsonPayload, Charset.forName(CHARSET_UTF_8));

        body.setContentType(ESL_CONTENT_TYPE_APPLICATION_JSON);
        put.setEntity(body);

        return execute(put, jsonHandler);
    }

    public void patch(String path, String jsonPayload) throws IOException, RequestException {
        support.logRequest("PATCH", path, jsonPayload);

        HttpPatch patch = new HttpPatch(path);
        patch.addHeader(buildAcceptHeaderForEslApi());
        StringEntity body = new StringEntity(jsonPayload, Charset.forName(CHARSET_UTF_8));

        body.setContentType(ESL_CONTENT_TYPE_APPLICATION_JSON);
        patch.setEntity(body);

        execute(patch, jsonHandler);
    }

    public String postMultipartFile(String path, Map<String, byte[]> files, String jsonPayload) throws IOException, RequestException {
        support.logRequest("POST", path, jsonPayload);

        final MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addPart("payload", buildPartForPayload(jsonPayload));
        for (Map.Entry<String, byte[]> file : files.entrySet()) {
            multipartEntityBuilder.addPart("file", buildPartForFile(file.getValue(), file.getKey()));
        }

        HttpPost post = new HttpPost(path);

        post.setEntity(multipartEntityBuilder.build());

        return execute(post, jsonHandler);
    }

    public void postMultipartFile(String path, Map<String, byte[]> files) throws IOException, RequestException {
        postMultipartFileWithPartName(path, files, "file");
    }

    public List<DocumentInfo> postMultipartFileForSupportingDocument(String path, Map<String, byte[]> files) throws IOException, RequestException {
        String response = postMultipartFileWithPartName(path, files, "files");
        return JacksonUtil.deserialize(response, new TypeReference<List<DocumentInfo>>() {});
    }

    public String postMultipartFile(String path, String fileName, byte[] fileBytes, String jsonPayload) throws IOException, RequestException {
        return postMultipartFile(path, Collections.singletonMap(fileName, fileBytes), jsonPayload);
    }

    public String postMultipartPackage(String path, Collection<Document> documents, String jsonPayload) throws IOException, RequestException {
        support.logRequest("POST", path, jsonPayload);

        final MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addPart("payload", buildPartForPayload(jsonPayload));

        for (com.silanis.esl.sdk.Document document : documents) {
            if (document.getExternal() == null) {
                multipartEntityBuilder.addPart("file", buildPartForFile(document));
            }
        }

        HttpPost post = new HttpPost(path);

        post.setEntity(multipartEntityBuilder.build());
        return execute(post, jsonHandler);
    }

    private String postMultipartFileWithPartName(String path, Map<String, byte[]> files, String partName) throws IOException, RequestException {
        support.logRequest("POST", path);

        final MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        for (Map.Entry<String, byte[]> file : files.entrySet()) {
            multipartEntityBuilder.addPart(partName, buildPartForFile(file.getValue(), file.getKey()));
        }

        HttpPost post = new HttpPost(path);
        post.setEntity(multipartEntityBuilder.build());

        return execute(post, jsonHandler);
    }

    private void addAdditionalHeaders(HttpUriRequest request) {
        for (Map.Entry<String, String> entry : additionalHeaders.entrySet()) {
            request.setHeader(entry.getKey(), entry.getValue());
        }
    }

    private StringBody buildPartForPayload(String jsonPayload) {
        return new StringBody(jsonPayload, ContentType.create("text/plain", Consts.UTF_8));
    }

    private ContentBody buildPartForFile(byte[] content, String fileName) {
        return buildPartForFile(content, fileName, fileName);
    }

    private ByteArrayBody buildPartForFile(Document document) {
        return buildPartForFile(document.getContent(), document.getFileName(), document.getName());
    }

    private ByteArrayBody buildPartForFile(byte[] content, String fileName, String name) {
        String contentType = getContentTypeByFileName(fileName);
        return new ByteArrayBody(content, ContentType.create(contentType), name);
    }

    protected void addAuthorizationHeader(HttpUriRequest request) {
        if (StringUtils.isNotEmpty(apiKey)) {
            request.setHeader("Authorization", "Basic " + apiKey);
        } else if (oauthTokenConfig != null) {
            try {
                request.setHeader("Authorization", "Bearer " + getOAuth2BearerToken(oauthTokenConfig));
            } catch (Exception x) {
                throw new RuntimeException(x);
            }
        } else if (apiTokenConfig != null){
            try {
                request.setHeader("Authorization", "Bearer " + getClientAppBearerToken());
            } catch (Exception x) {
                throw new RuntimeException(x);
            }
        }
    }

    private String getClientAppBearerToken() throws RequestException, IOException {
        //token has to have more than 1mn to live
        if (apiTokenConfig != null && (apiToken == null || System.currentTimeMillis() > apiToken.getExpiresAt() - 60 * 1000)) {
            String url = apiTokenConfig.getBaseUrl() + ApiTokenConfig.ACCESS_TOKEN_URL;
            HttpPost request = withUserAgent(new HttpPost(url));
            request.addHeader(HEADER_CONTENT_TYPE, ESL_CONTENT_TYPE_APPLICATION_JSON);
            request.setEntity(new StringEntity(getApiTokenPayload()));
            try(CloseableHttpClient client = getHttpClient(request);) {
                HttpResponse httpResponse = client.execute(request);
                if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    throw new EslException("Unable to create access token for " + apiTokenConfig);
                }
                apiToken = OBJECT_MAPPER.readValue(httpResponse.getEntity().getContent(), ApiToken.class);
            }
        }
        if (apiToken == null)
            throw new EslException("Unable to create access token for " + apiTokenConfig);
        return apiToken.getAccessToken();
    }

    private String getOAuth2BearerToken(OAuthTokenConfig oauthTokenConfig) throws IOException, RequestException {
        if (oAuthAccessToken == null || oauth2TokenManager.isOAuth2TokenExpired(oAuthAccessToken.getAccessToken())) {
            oAuthAccessToken = generateOAuth2AccessToken(oauthTokenConfig);
        }
        return oAuthAccessToken.getAccessToken();
    }

    private OAuthAccessToken generateOAuth2AccessToken(OAuthTokenConfig oauthTokenConfig) throws RequestException, IOException{
        HttpPost request = withUserAgent(new HttpPost(oauthTokenConfig.getAuthenticationServer()));
        request.setHeader(
            "Authorization",
            "Basic " + Base64.getEncoder().encodeToString(String.format("%s:%s", oauthTokenConfig.getClientId(),
                oauthTokenConfig.getClientSecret()).getBytes()));

        request.addHeader(HEADER_CONTENT_TYPE, "application/x-www-form-urlencoded");

        StringBuilder encodedParams = new StringBuilder("grant_type=client_credentials");

        String senderId = oauthTokenConfig.getSenderId();
        if (senderId != null) {
            encodedParams.append("&sender_id=" + URLEncoder.encode(senderId, CHARSET_UTF_8));
        }

        String delegatorId = oauthTokenConfig.getDelegatorId();
        if (delegatorId != null) {
            encodedParams.append("&delegator_id=" + URLEncoder.encode(delegatorId, CHARSET_UTF_8));
        }

        request.setEntity(new StringEntity(encodedParams.toString(), ContentType.create(HEADER_CONTENT_TYPE, Consts.UTF_8)));

        try(CloseableHttpClient client = getHttpClient(request);)
        {
            HttpResponse httpResponse = client.execute(request);
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                JsonHandler jsonHandler = new JsonHandler();
                throw new EslException(
                        "Unable to create access token for "
                                + oauthTokenConfig
                                + " "
                                + httpResponse.getStatusLine().getStatusCode()
                                + ":"
                                + httpResponse.getStatusLine().getReasonPhrase()
                                + " "
                                + jsonHandler.extract(httpResponse.getEntity().getContent())
                );
            }
            return OBJECT_MAPPER.readValue(httpResponse.getEntity().getContent(), OAuthAccessToken.class);
        }
    }

    private String getApiTokenPayload() throws JsonProcessingException {
        ApiTokenAccessRequest apiTokenAccessRequest = null;
        if (apiTokenConfig != null) {
            apiTokenAccessRequest = ApiTokenAccessRequest.newBuilder()
                .clientId(apiTokenConfig.getClientAppId())
                .secret(apiTokenConfig.getClientAppSecret())
                .type(apiTokenConfig.getTokenType())
                .email(apiTokenConfig.getTokenType() == ApiTokenConfig.TokenType.SENDER ? apiTokenConfig.getSenderEmail() : null)
                .build();
        }
        return apiTokenAccessRequest != null ? OBJECT_MAPPER.writeValueAsString(apiTokenAccessRequest) : "";
    }

    @SuppressWarnings("unchecked")
    private <T> T execute(HttpUriRequest request, ResponseHandler<T> handler) throws IOException, RequestException {

        withUserAgent(request);
        CloseableHttpClient client = getHttpClient(request);

        try {
            addAdditionalHeaders(request);
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
                if (null != response.getHeaders(HEADER_CONTENT_DISPOSITION) && response.getHeaders(HEADER_CONTENT_DISPOSITION).length > 0) {
                    String fileName = getFilename(response.getHeaders(HEADER_CONTENT_DISPOSITION)[0].getValue());
                    DownloadedFile<?> downloadedFile = (DownloadedFile<?>) handler.extract(bodyContent);
                    downloadedFile.setFilename(fileName);
                    return (T) downloadedFile;
                }
                return handler.extract(bodyContent);
            }
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }

    private CloseableHttpClient getHttpClient(HttpUriRequest request) throws RequestException {
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
        return client;
    }

    private String getFilename(String disposition) {
        String fileNameTitle = "filename*=UTF-8''";
        String[] parts = disposition.split(";");

        for (String part : parts) {
            int index = part.indexOf(fileNameTitle);
            if (index > 0) {
                return percentDecode(part.substring(index + fileNameTitle.length()));
            }
        }

        return "";
    }

    public String get(String path) throws IOException, RequestException {
        return get(path, ESL_ACCEPT_TYPE_APPLICATION_JSON);
    }

    public String get(String path, String acceptType) throws IOException, RequestException {
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

    public DownloadedFile<?> getBytes(String path) throws IOException, RequestException {
        return getBytes(path, ESL_ACCEPT_TYPE_APPLICATION);
    }

    public DownloadedFile<?> getBytes(String path, String acceptType) throws IOException, RequestException {
        support.logRequest("GET", path);
        HttpGet get = new HttpGet(path);
        get.addHeader(buildAcceptHeader(acceptType));

        return execute(get, bytesHandler);
    }

    public DownloadedFile<?> getBytesAsOctetStream(String path) throws IOException, RequestException {
        return getBytes(path, ESL_ACCEPT_TYPE_APPLICATION_OCTET_STREAM);
    }

    public String delete(String path) throws IOException, RequestException {
        support.logRequest("DELETE", path);
        HttpDelete delete = new HttpDelete(path);
        delete.addHeader(buildAcceptHeaderForEslApi());

        return execute(delete, jsonHandler);
    }

    public String delete(String path, String jsonPayload) throws IOException, RequestException {
        support.logRequest("DELETE", path);
        HttpDeleteWithBody delete = new HttpDeleteWithBody(path);
        delete.addHeader(buildAcceptHeaderForEslApi());
        StringEntity body = new StringEntity(jsonPayload, Charset.forName(CHARSET_UTF_8));

        body.setContentType(ESL_CONTENT_TYPE_APPLICATION_JSON);
        delete.setEntity(body);

        return execute(delete, jsonHandler);
    }

    private <T extends HttpRequest> T withUserAgent(T request) {
        request.addHeader(HttpHeaders.USER_AGENT, ESL_API_USER_AGENT);
        return request;
    }
}
