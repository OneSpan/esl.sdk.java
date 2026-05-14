package com.silanis.esl.sdk.service;

import com.silanis.esl.sdk.AttachmentVerificationResult;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.ServerError;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.UrlTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AttachmentRequirementServiceTest {

    private static final String BASE_URL = "http://baseurl";
    private static final PackageId PACKAGE_ID = new PackageId("package-id");

    private AttachmentRequirementService service;

    @Mock
    private RestClient client;

    @Before
    public void setup() {
        service = new AttachmentRequirementService(client, BASE_URL);
    }

    @Test
    public void getAttachmentVerificationResultsUsesExpectedPathAndReturnsResults() throws Exception {
        String path = expectedVerificationResultsPath();
        when(client.get(path)).thenReturn("[{\"attachmentUuid\":\"attachment-uid\",\"fileName\":\"passport\"}]");

        List<AttachmentVerificationResult> results = service.getAttachmentVerificationResults(PACKAGE_ID);

        verify(client).get(path);
        assertThat(results.size(), is(1));
        assertThat(results.get(0).getAttachmentUuid(), is("attachment-uid"));
        assertThat(results.get(0).getFileName(), is("passport"));
    }

    @Test
    public void getAttachmentVerificationResultsReturnsEmptyListWhenResponseIsNull() throws Exception {
        String path = expectedVerificationResultsPath();
        when(client.get(path)).thenReturn(null);

        List<AttachmentVerificationResult> results = service.getAttachmentVerificationResults(PACKAGE_ID);

        verify(client).get(path);
        assertThat(results, empty());
    }

    @Test(expected = EslServerException.class)
    public void getAttachmentVerificationResultsWrapsRequestExceptionAsServerException() throws Exception {
        String path = expectedVerificationResultsPath();
        when(client.get(path)).thenThrow(new RequestException("GET", path, 500, "Server Error", "{}"));

        service.getAttachmentVerificationResults(PACKAGE_ID);
    }

    @Test
    public void getAttachmentVerificationResultsPreservesServerErrorWhenRequestExceptionIsThrown() throws Exception {
        String path = expectedVerificationResultsPath();
        when(client.get(path)).thenThrow(new RequestException("GET", path, 500, "Server Error",
                "{\"messageKey\":\"error.key\",\"message\":\"Something failed\",\"technical\":\"details\"}"));

        try {
            service.getAttachmentVerificationResults(PACKAGE_ID);
        } catch (EslServerException e) {
            ServerError serverError = e.getServerError();
            assertThat(serverError, notNullValue());
            assertThat(serverError.getMessageKey(), is("error.key"));
            return;
        }

        throw new AssertionError("Expected EslServerException");
    }

    @Test(expected = EslException.class)
    public void getAttachmentVerificationResultsWrapsMalformedResponseAsEslException() throws Exception {
        String path = expectedVerificationResultsPath();
        when(client.get(path)).thenReturn("{not-json");

        service.getAttachmentVerificationResults(PACKAGE_ID);
    }

    private String expectedVerificationResultsPath() {
        return new UrlTemplate(BASE_URL)
                .urlFor(UrlTemplate.ATTACHMENT_VERIFICATION_RESULTS_PATH)
                .replace("{packageId}", PACKAGE_ID.getId())
                .build();
    }
}
