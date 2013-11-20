package com.silanis.esl.sdk.internal.converter.sdk;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.FieldValidatorBuilder;
import com.silanis.esl.sdk.builder.TextAnchorBuilder;
import com.silanis.esl.sdk.internal.converter.FieldConverter;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.FieldBuilder.newField;
import static org.junit.Assert.assertEquals;

public class FieldConverterTest {
    public static final double DEFAULT_DOUBLE_TOLERANCE = 0.01f;

    @Test
    public void toAPIField() {
        double x = 1;
        double y = 2;
        int page = 3;
        double width = 4;
        double height = 5;

        com.silanis.esl.sdk.Field sdkField = newField()
                .withId(new FieldId("99"))
                .atPosition(x, y)
                .onPage(page)
                .withSize(width, height)
                .withStyle(FieldStyle.BOUND_DATE)
                .withName("Field name")
                .withPositionAnchor(TextAnchorBuilder.newTextAnchor("Anchor Text")
                        .atPosition(TextAnchorPosition.BOTTOMLEFT)
                        .withCharacter(65)
                        .withOccurence(2)
                        .withOffset(101, 102) //xOffset, yOffset
                        .withSize(201, 202)   // width, height
                        .build())
                .withValidation(FieldValidatorBuilder.alphabetic()
                        .maxLength(15)
                        .minLength(5)
                        .required()
                        .withErrorMessage("Error message for validation.")
                        .build())
                .withValue("value")
                .build();

        com.silanis.esl.api.model.Field apiField = new FieldConverter(sdkField).toAPIField();

        assertEquals( apiField.getLeft(),  sdkField.getX(), DEFAULT_DOUBLE_TOLERANCE);
        assertEquals( apiField.getTop(), sdkField.getY(), DEFAULT_DOUBLE_TOLERANCE);
        assertEquals( apiField.getWidth(), sdkField.getWidth(), DEFAULT_DOUBLE_TOLERANCE);
        assertEquals( apiField.getHeight(), sdkField.getHeight(), DEFAULT_DOUBLE_TOLERANCE);
        assertEquals( apiField.getPage(), sdkField.getPage(), DEFAULT_DOUBLE_TOLERANCE);
        assertEquals( apiField.getExtract(), sdkField.isExtraction());
        assertEquals( apiField.getValue(), sdkField.getValue() );
        assertEquals( apiField.getId().toString(), sdkField.getId().toString());
        assertEquals( apiField.getName(), sdkField.getName());

    }

}
