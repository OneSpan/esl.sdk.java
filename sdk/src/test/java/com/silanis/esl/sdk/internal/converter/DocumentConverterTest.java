package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentType;
import org.junit.Test;

import java.io.InputStream;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by chi-wing on 5/6/14.
 */
public class DocumentConverterTest {
    private Document sdkDocument1 = null;
    private Document sdkDocument2 = null;
    private com.silanis.esl.api.model.Document apiDocument1 = null;
    private com.silanis.esl.api.model.Document apiDocument2 = null;
    private com.silanis.esl.api.model.Package apiPackage = null;
    private DocumentConverter converter;
    private InputStream documentInputStream;

    @Test
    public void convertNullSDKToAPI() {
        sdkDocument1 = null;
        converter = new DocumentConverter(sdkDocument1);
        assertThat( "Converter didn't return a null api object for a null sdk object", converter.toAPIDocument(apiPackage), is( nullValue() ) );
    }

    @Test
    public void convertNullAPIToSDK() {
        apiDocument1 = null;
        converter = new DocumentConverter(apiDocument1, apiPackage);
        assertThat( "Converter didn't return a null sdk object for a null api object", converter.toAPIDocument(apiPackage), is( nullValue() ) );
    }

    @Test
    public void convertNullSDKToSDK() {
        sdkDocument1 = null;
        converter = new DocumentConverter(sdkDocument1);
        assertThat( "Converter didn't return a null sdk object for a null sdk object", converter.toSDKDocument(), is( nullValue() ) );
    }

    @Test
    public void convertNullAPIToAPI() {
        apiDocument1 = null;
        converter = new DocumentConverter(apiDocument1, apiPackage);
        assertThat( "Converter didn't return a null api object for a null api object", converter.toAPIDocument(apiPackage), is( nullValue() ) );
    }

    @Test
    public void convertSDKToSDK() {
        sdkDocument1 = createTypicalSDKDocument();
        sdkDocument2 = new DocumentConverter(sdkDocument1).toSDKDocument();
        assertThat( "Converter returned a null sdk object for a non null sdk object", sdkDocument2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null sdk object it was given", sdkDocument2, is( equalTo( sdkDocument1 ) ) );
    }

    @Test
    public void convertAPIToAPI() {
        apiDocument1 = createTypicalAPIDocument();
        apiDocument2 = new DocumentConverter(apiDocument1, apiPackage).toAPIDocument(apiPackage);

        assertThat( "Converter returned a null api object for a non null api object", apiDocument2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null api object it was given", apiDocument2, is( equalTo( apiDocument1 ) ) );
    }

    @Test
    public void convertAPIToSDK(){
        apiDocument1 = createTypicalAPIDocument();
        sdkDocument1 = new DocumentConverter(apiDocument1, apiPackage).toSDKDocument();

        assertThat("Converter returned a null api object for a non null sdk object", sdkDocument1, is( notNullValue() ) );
        assertThat("Description was not correctly set", sdkDocument1.getDescription(), is(equalTo(apiDocument1.getDescription())));
        assertThat("Name was not correctly set", sdkDocument1.getName(), is(equalTo(apiDocument1.getName())));
        assertThat("Id was not correctly set", sdkDocument1.getId().toString(), is(equalTo(apiDocument1.getId())));
        assertThat("Index was not correctly set", sdkDocument1.getIndex(), is(equalTo(apiDocument1.getIndex())));
    }

    @Test
    public void convertSDKToAPI(){
        sdkDocument1 = createTypicalSDKDocument();
        apiDocument1 = new DocumentConverter(sdkDocument1).toAPIDocumentMetadata();

        assertThat("Converter returned a null api object for a non null sdk object", apiDocument1, is( notNullValue() ) );
        assertThat("Description was not correctly set", sdkDocument1.getDescription(), is(equalTo(apiDocument1.getDescription())));
        assertThat("Name was not correctly set", sdkDocument1.getName(), is(equalTo(apiDocument1.getName())));
        assertThat("Id was not correctly set", sdkDocument1.getId().toString(), is(equalTo(apiDocument1.getId())));
        assertThat("Index was not correctly set", sdkDocument1.getIndex(), is(equalTo(apiDocument1.getIndex())));
    }

    @Test(expected=IllegalArgumentException.class)
    public void convertNullSDKtoAPIMetadata(){
        sdkDocument1 = null;
        converter = new DocumentConverter(sdkDocument1);
        converter.toAPIDocumentMetadata();
    }

    @Test
    public void convertSDKtoAPIMetadataWithNullId(){
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        sdkDocument1 = newDocumentWithName("sdkDocument")
                .fromStream(documentInputStream, DocumentType.PDF)
                .withSignature(signatureFor("john.smith@email.com")
                        .onPage(0))
                .withDescription("Unit test null id")
                .build();

        assertThat("SDK document id is not null", sdkDocument1.getId(), is(nullValue()));
    }

    @Test
    public void convertSDKtoAPIMetadataWithNullDescription(){
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        sdkDocument1 = newDocumentWithName("sdkDocument")
                .fromStream(documentInputStream, DocumentType.PDF)
                .withId("sdkDocumentId")
                .withSignature(signatureFor("john.smith@email.com")
                        .onPage(0))
                .build();

        assertThat("SDK document id is not null", sdkDocument1.getDescription(), is(nullValue()));
    }

    private com.silanis.esl.sdk.Document createTypicalSDKDocument() {
        com.silanis.esl.sdk.Document sdkDocument;
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream("document.pdf");

        sdkDocument = newDocumentWithName("sdkDocument")
                .fromStream(documentInputStream, DocumentType.PDF)
                .withId("sdkDocumentId")
                .withDescription("sdkDocument Description")
                .withSignature(signatureFor("john.smith@email.com")
                        .onPage(0))
                .build();

        return sdkDocument;
    }

    private com.silanis.esl.api.model.Document createTypicalAPIDocument() {
        com.silanis.esl.api.model.Document apiDocument;
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream("document.pdf");

        apiDocument = new com.silanis.esl.api.model.Document()
                .setIndex(1)
                .setName("apiDocument")
                .setId("apiDocumentId")
                .setDescription("apiDocument Description");

        return apiDocument;
    }
}
