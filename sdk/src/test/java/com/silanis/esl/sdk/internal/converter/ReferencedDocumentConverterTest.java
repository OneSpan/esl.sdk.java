package com.silanis.esl.sdk.internal.converter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertTrue;

import com.silanis.esl.sdk.ReferencedDocument;
import com.silanis.esl.sdk.ReferencedField;
import java.util.List;
import org.junit.Test;

public class ReferencedDocumentConverterTest extends ReferencedConditionsConverterTest {

    @Override
    @Test
    public void convertNullSDKToAPI() {
        com.silanis.esl.api.model.ReferencedDocument api = ReferencedDocumentConverter.toAPI(null);
        assertThat("Converter didn't return a null api object for a null sdk object", api, is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        ReferencedDocument sdk = ReferencedDocumentConverter.toSDK(null);
        assertThat("Converter didn't return a null sdk object for a null api object", sdk, is(nullValue()));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        List<com.silanis.esl.api.model.ReferencedField> apiFields = createApiReferencedFieldsForTest();

        com.silanis.esl.api.model.ReferencedDocument api = new com.silanis.esl.api.model.ReferencedDocument();
        api.setDocumentId(DOCUMENT_ID);
        api.setFields(apiFields);

        ReferencedDocument sdk = ReferencedDocumentConverter.toSDK(api);

        assertThat("Document ID was not correctly converted", sdk.getDocumentId(), is(DOCUMENT_ID));
        assertTrue("Referenced Fields was not correctly converted", compareReferencedFields(apiFields, sdk.getFields()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        List<ReferencedField> sdkFields = createSdkReferencedFieldsForTest();

        ReferencedDocument sdk = new ReferencedDocument();
        sdk.setDocumentId(DOCUMENT_ID);
        sdk.setFields(sdkFields);

        com.silanis.esl.api.model.ReferencedDocument api = ReferencedDocumentConverter.toAPI(sdk);

        assertThat("Document ID was not correctly converted", api.getDocumentId(), is(DOCUMENT_ID));
        assertTrue("Referenced Fields was not correctly converted", compareReferencedFields(api.getFields(), sdkFields));
    }
}
