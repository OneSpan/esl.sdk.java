package com.silanis.esl.sdk.internal;

import com.silanis.esl.sdk.ServerError;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RequestExceptionTest {

    @Test
    public void aClassicErrorBodyIsParsed() {
        ServerError error = new RequestException("GET", "/api/packages/1", 403, "Forbidden",
                "{\"code\":403,\"message\":\"Not allowed\",\"messageKey\":\"error.forbidden\",\"technical\":\"details\"}")
                .getServerError();

        assertThat(error.getCode(), is(403));
        assertThat(error.getMessage(), is("Not allowed"));
        assertThat(error.getMessageKey(), is("error.forbidden"));
        assertThat(error.getTechnical(), is("details"));
    }

    @Test
    public void anEmptyBodyCarriesTheStatusAndReasonPhrase() {
        ServerError error = new RequestException("GET", "/api/packages/1", 401, "Unauthorized", "").getServerError();

        assertThat(error.getCode(), is(401));
        assertThat(error.getMessage(), is("Unauthorized"));
    }

    @Test
    public void aNonJsonBodyCarriesTheStatusAndReasonPhrase() {
        ServerError error = new RequestException("GET", "/api/packages/1", 502, "Bad Gateway",
                "<html><body>Bad gateway</body></html>").getServerError();

        assertThat(error.getCode(), is(502));
        assertThat(error.getMessage(), is("Bad Gateway"));
    }
}
