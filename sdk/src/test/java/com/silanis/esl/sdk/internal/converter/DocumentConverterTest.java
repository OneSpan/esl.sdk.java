package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentType;
import org.junit.Test;

import java.io.InputStream;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

/**
 * Created by chi-wing on 5/6/14.
 */
public class DocumentConverterTest {
    private Document sdkDocument = null;
    private DocumentConverter converter;
    private InputStream documentInputStream;

    @Test(expected=IllegalArgumentException.class)
    public void convertNullSDKtoAPIMetadata(){
        sdkDocument = null;
        converter = new DocumentConverter(sdkDocument);
        converter.toAPIDocumentMetadata();
    }

    @Test
    public void convertSDKtoAPIMetadataWithNullId(){
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        sdkDocument = newDocumentWithName("sdkDocument")
                .fromStream(documentInputStream, DocumentType.PDF)
                .withSignature(signatureFor("john.smith@email.com")
                        .onPage(0))
                .withDescription("Unit test null id")
                .build();

        assertThat("SDK document id is not null", sdkDocument.getId(), is( nullValue() ));
    }

    @Test
    public void convertSDKtoAPIMetadataWithNullDescription(){
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        sdkDocument = newDocumentWithName("sdkDocument")
                .fromStream(documentInputStream, DocumentType.PDF)
                .withId("sdkDocumentId")
                .withSignature(signatureFor("john.smith@email.com")
                        .onPage(0))
                .build();

        assertThat("SDK document id is not null", sdkDocument.getDescription(), is( nullValue() ));
    }
}
