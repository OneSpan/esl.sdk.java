package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.Signature;
import com.silanis.esl.sdk.SignatureStyle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static com.silanis.esl.sdk.builder.FieldBuilder.newField;
import static com.silanis.esl.sdk.builder.SignatureBuilder.captureFor;
import static com.silanis.esl.sdk.builder.SignatureBuilder.initialsFor;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: dave
 */
public class SignatureBuilderTest {
    public static final double DEFAULT_DOUBLE_TOLERANCE = 0.01f;

    @Test
    public void buildWithSpecifiedValues() {
        String email = "bob@aol.com";
        double x = 1;
        double y = 2;
        double width = 3;
        double height = 4;
        int page = 5;
        SignatureStyle style = SignatureStyle.HAND_DRAWN;

        Collection<Field> fields = new ArrayList<Field>();
        for ( int i = 0; i < 3; i++ )
            fields.add( newField().atPosition(50, 50).build() );

        SignatureBuilder builder = signatureFor(email)
                .withStyle( style )
                .atPosition( x, y )
                .onPage( page )
                .withSize( width, height );

        for ( Field field : fields )
            builder.withField( field );

        Signature signature = builder.build();

        assertEquals( email, signature.getSignerEmail() );
        assertEquals( x, signature.getX(), DEFAULT_DOUBLE_TOLERANCE );
        assertEquals( y, signature.getY(), DEFAULT_DOUBLE_TOLERANCE );
        assertEquals( width, signature.getWidth(), DEFAULT_DOUBLE_TOLERANCE );
        assertEquals( height, signature.getHeight(), DEFAULT_DOUBLE_TOLERANCE );
        assertEquals( page, signature.getPage() );
        assertEquals( style, signature.getStyle() );

        assertEquals( fields.size(), signature.getFields().size() );
        for ( Field field : fields )
            assertTrue( signature.getFields().contains( field ) );
    }

    @Test
    public void buildWithDefaultValues() {
        SignatureBuilder builder = signatureFor("test@test.com");
        Signature signature = builder.build();

        assertEquals( SignatureBuilder.DEFAULT_HEIGHT, signature.getHeight(), DEFAULT_DOUBLE_TOLERANCE );
        assertEquals( SignatureBuilder.DEFAULT_WIDTH, signature.getWidth(), DEFAULT_DOUBLE_TOLERANCE );
        assertEquals( SignatureBuilder.DEFAULT_STYLE, signature.getStyle() );
    }

    @Test
    public void creatingInitialsForSignerSetsStyle() {
        Signature signature = initialsFor("test@test.com").build();

        assertThat(signature.getStyle(), is(equalTo(SignatureStyle.INITIALS)));
    }

    @Test
    public void creatingCaptureForSignerSetsStyle() {
        Signature signature = captureFor("test@test.com").build();

        assertThat(signature.getStyle(), is(equalTo(SignatureStyle.HAND_DRAWN)));
    }
}