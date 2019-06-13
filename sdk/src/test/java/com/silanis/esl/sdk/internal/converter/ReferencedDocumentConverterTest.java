package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.ReferencedDocument;
import com.silanis.esl.sdk.ReferencedField;
import org.junit.Test;

import java.util.List;

import static com.silanis.esl.sdk.internal.converter.ReferencedDocumentConverter.toAPI;
import static com.silanis.esl.sdk.internal.converter.ReferencedDocumentConverter.toSDK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertTrue;

public class ReferencedDocumentConverterTest extends ReferencedConditionsConverterTest {

    @Override
    @Test
    public void convertNullSDKToAPI() {
        com.silanis.esl.api.model.ReferencedDocument api = toAPI(null);
        assertThat("Converter didn't return a null api object for a null sdk object", api, nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        ReferencedDocument sdk = toSDK(null);
        assertThat("Converter didn't return a null sdk object for a null api object", sdk, nullValue());
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        List<com.silanis.esl.api.model.ReferencedField> apiFields = buildApiReferencedFields();

        com.silanis.esl.api.model.ReferencedDocument api = new com.silanis.esl.api.model.ReferencedDocument();
        api.setDocumentId(DOCUMENT_ID);
        api.setFields(apiFields);

        ReferencedDocument sdk = toSDK(api);

        assertThat("Document ID was not correctly converted", sdk.getDocumentId(), is(DOCUMENT_ID));
        assertTrue("Referenced Fields was not correctly converted", compareReferencedFields(apiFields, sdk.getFields()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        List<ReferencedField> sdkFields = buildSdkReferencedFields();

        ReferencedDocument sdk = new ReferencedDocument();
        sdk.setDocumentId(DOCUMENT_ID);
        sdk.setFields(sdkFields);

        com.silanis.esl.api.model.ReferencedDocument api = toAPI(sdk);

        assertThat("Document ID was not correctly converted", api.getDocumentId(), is(DOCUMENT_ID));
        assertTrue("Referenced Fields was not correctly converted", compareReferencedFields(api.getFields(), sdkFields));
    }
}
