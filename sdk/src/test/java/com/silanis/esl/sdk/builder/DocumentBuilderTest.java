package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class DocumentBuilderTest {
    @Test
    public void buildWithSpecifiedValues() {

        String name = "documentName";
        String fileName = "src/main/resources/document.pdf";

        Collection<Signature> signatures = new ArrayList<Signature>();
        for ( int i = 0; i < 4; i++ )
            signatures.add( mock( Signature.class ) );

        DocumentBuilder builder = DocumentBuilder
                .newDocumentWithName( name )
                .fromFile( fileName );

        for ( Signature signature : signatures )
            builder.withSignature( signature );

        Document document = builder.build();

        assertEquals( name, document.getName() );
        assertEquals( fileName, document.getFileName() );

        for ( Signature signature : signatures ) {
            assertTrue( document.getSignatures().contains( signature ) );
        }
    }

    @Test
    public void buildWithDefaultValues() {
        DocumentBuilder builder = new DocumentBuilder()
                .fromFile( "src/main/resources/document.pdf" );
        Document document = builder.build();

        assertEquals( DocumentBuilder.DEFAULT_NAME, document.getName() );
        assertTrue( document.getSignatures().isEmpty() );
    }

    @Test
    public void buildWithMissingFileName() {
        DocumentBuilder builder = new DocumentBuilder();
        try {
            builder.build();
            fail( "No validation exception thrown." );
        } catch ( BuilderException builderException ) {

        } catch ( Exception e ) {
            fail( "Unexpected exception thrown." );
        }
    }
}
