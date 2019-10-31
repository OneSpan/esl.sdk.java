package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.GroupId;
import com.silanis.esl.sdk.Placeholder;
import com.silanis.esl.sdk.Signature;
import com.silanis.esl.sdk.SignatureStyle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static com.silanis.esl.sdk.builder.FieldBuilder.newField;
import static com.silanis.esl.sdk.builder.SignatureBuilder.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SignatureBuilderTest {
    public static final double DEFAULT_DOUBLE_TOLERANCE = 0.01f;

    @Test
    public void buildCaptureForPlaceholder() {
        Placeholder placeholder = new Placeholder("placeholderId");
        Signature signature = captureFor(placeholder).build();

        assertThat(signature, notNullValue());
        assertThat(signature.getRoleId(), is(placeholder));
        assertThat(signature.getSignerEmail(), isEmptyOrNullString());
        assertThat(signature.getStyle(), is(SignatureStyle.HAND_DRAWN));
    }

    @Test
    public void buildSignatureForPlaceholder() {
        Placeholder placeholder = new Placeholder("placeholderId");
        Signature signature = signatureFor(placeholder).build();

        assertThat(signature, notNullValue());
        assertThat(signature.getRoleId(), is(placeholder));
        assertThat(signature.getSignerEmail(), isEmptyOrNullString());
        assertThat(signature.getStyle(), is(SignatureStyle.FULL_NAME));
    }

    @Test
    public void buildAcceptanceForPlaceholder() {
        Placeholder placeholder = new Placeholder("placeholderId");
        Signature signature = SignatureBuilder.acceptanceFor(placeholder).build();

        assertThat(signature, notNullValue());
        assertThat(signature.getRoleId(), is(placeholder));
        assertThat(signature.getSignerEmail(), isEmptyOrNullString());
        assertThat(signature.getStyle(), is(SignatureStyle.ACCEPTANCE));
    }

    @Test
    public void buildInitialsForPlaceholder() {
        Placeholder placeholder = new Placeholder("placeholderId");
        Signature signature = initialsFor(placeholder).build();

        assertThat(signature, notNullValue());
        assertThat(signature.getRoleId(), is(placeholder));
        assertThat(signature.getSignerEmail(), isEmptyOrNullString());
        assertThat(signature.getStyle(), is(SignatureStyle.INITIALS));
    }

    @Test
    public void buildMobileCaptureForPlaceholder() {
        Placeholder placeholder = new Placeholder("placeholderId");
        Signature signature = mobileCaptureFor(placeholder).build();

        assertThat(signature, notNullValue());
        assertThat(signature.getRoleId(), is(placeholder));
        assertThat(signature.getSignerEmail(), isEmptyOrNullString());
        assertThat(signature.getStyle(), is(SignatureStyle.MOBILE_CAPTURE));
    }

    @Test
    public void buildCaptureForGroup() {
        GroupId groupId = new GroupId("myGroupId");
        Signature signature = captureFor(groupId).build();

        assertThat(signature, notNullValue());
        assertThat(signature.getGroupId(), is(groupId));
        assertThat(signature.getSignerEmail(), isEmptyOrNullString());
        assertThat(signature.getStyle(), is(SignatureStyle.HAND_DRAWN));
    }

    @Test
    public void buildSignatureForGroup() {
        GroupId groupId = new GroupId("myGroupId");
        Signature signature = signatureFor(groupId).build();

        assertThat(signature, notNullValue());
        assertThat(signature.getGroupId(), is(groupId));
        assertThat(signature.getSignerEmail(), isEmptyOrNullString());
        assertThat(signature.getStyle(), is(SignatureStyle.FULL_NAME));
    }

    @Test
    public void buildAcceptanceForGroup() {
        GroupId groupId = new GroupId("myGroupId");
        Signature signature = SignatureBuilder.acceptanceFor(groupId).build();

        assertThat(signature, notNullValue());
        assertThat(signature.getGroupId(), is(groupId));
        assertThat(signature.getSignerEmail(), isEmptyOrNullString());
        assertThat(signature.getStyle(), is(SignatureStyle.ACCEPTANCE));
    }

    @Test
    public void buildInitialsForGroup() {
        GroupId groupId = new GroupId("myGroupId");
        Signature signature = initialsFor(groupId).build();

        assertThat(signature, notNullValue());
        assertThat(signature.getGroupId(), is(groupId));
        assertThat(signature.getSignerEmail(), isEmptyOrNullString());
        assertThat(signature.getStyle(), is(SignatureStyle.INITIALS));
    }

    @Test
    public void buildMobileCaptureForGroup() {
        GroupId groupId = new GroupId("myGroupId");
        Signature signature = mobileCaptureFor(groupId).build();

        assertThat(signature, notNullValue());
        assertThat(signature.getGroupId(), is(groupId));
        assertThat(signature.getSignerEmail(), isEmptyOrNullString());
        assertThat(signature.getStyle(), is(SignatureStyle.MOBILE_CAPTURE));
    }

    @Test
    public void buildWithSpecifiedValues() {
        String email = "bob@aol.com";
        double x = 1;
        double y = 2;
        double width = 3;
        double height = 4;
        int page = 5;
        Integer fontSize = 20;
        SignatureStyle style = SignatureStyle.HAND_DRAWN;

        Collection<Field> fields = new ArrayList<Field>();
        for (int i = 0; i < 3; i++)
            fields.add(newField().atPosition(50, 50).build());

        SignatureBuilder builder = signatureFor(email)
                .withStyle(style)
                .atPosition(x, y)
                .onPage(page)
                .withSize(width, height)
                .withFontSize(fontSize);

        for (Field field : fields)
            builder.withField(field);

        Signature signature = builder.build();

        assertEquals(email, signature.getSignerEmail());
        assertEquals(x, signature.getX(), DEFAULT_DOUBLE_TOLERANCE);
        assertEquals(y, signature.getY(), DEFAULT_DOUBLE_TOLERANCE);
        assertEquals(width, signature.getWidth(), DEFAULT_DOUBLE_TOLERANCE);
        assertEquals(height, signature.getHeight(), DEFAULT_DOUBLE_TOLERANCE);
        assertEquals(page, signature.getPage());
        assertEquals(style, signature.getStyle());
        assertEquals(fontSize, signature.getFontSize());

        assertEquals(fields.size(), signature.getFields().size());
        for (Field field : fields)
            assertTrue(signature.getFields().contains(field));
    }

    @Test
    public void buildWithDefaultValues() {
        SignatureBuilder builder = signatureFor("test@test.com");
        Signature signature = builder.build();

        assertEquals(SignatureBuilder.DEFAULT_HEIGHT, signature.getHeight(), DEFAULT_DOUBLE_TOLERANCE);
        assertEquals(SignatureBuilder.DEFAULT_WIDTH, signature.getWidth(), DEFAULT_DOUBLE_TOLERANCE);
        assertEquals(SignatureBuilder.DEFAULT_STYLE, signature.getStyle());
    }

    @Test
    public void creatingInitialsForSignerSetsStyle() {
        Signature signature = initialsFor("test@test.com").build();

        assertThat(signature.getStyle(), is(SignatureStyle.INITIALS));
    }

    @Test
    public void creatingCaptureForSignerSetsStyle() {
        Signature signature = captureFor("test@test.com").build();

        assertThat(signature.getStyle(), is(SignatureStyle.HAND_DRAWN));
    }

    @Test
    public void creatingMobileCaptureForSignerSetsStyle() {
        Signature signature = mobileCaptureFor("test@test.com").build();

        assertThat(signature.getStyle(), is(SignatureStyle.MOBILE_CAPTURE));
    }

    @Test
    public void creatingOptionalSignature() {
        SignatureBuilder builder = signatureFor("test@test.com").makeOptional();
        Signature signature = builder.build();

        assertTrue(signature.isOptional());
    }

    @Test
    public void creatingDisabledSignature() {
        SignatureBuilder builder = signatureFor("test@test.com").disabled();
        Signature signature = builder.build();

        assertTrue(signature.isDisabled());
    }

    @Test
    public void creatingCaptureWithEnforceCaptureSignatureSetting() {
        Signature signature = captureFor("test@test.com").enableEnforceCaptureSignature().build();

        assertTrue(signature.isEnforceCaptureSignature());
    }
}