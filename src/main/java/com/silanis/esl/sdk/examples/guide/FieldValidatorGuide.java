package com.silanis.esl.sdk.examples.guide;

import com.silanis.esl.sdk.FieldStyle;
import com.silanis.esl.sdk.FieldValidator;
import com.silanis.esl.sdk.builder.FieldBuilder;
import com.silanis.esl.sdk.builder.FieldValidatorBuilder;

public class FieldValidatorGuide {
    private static class SampleA {
        public static void main( String... args ) {

            FieldValidator alphabeticFieldValidator = FieldValidatorBuilder.alphabetic()
                    .build();

            FieldValidator alphanumericFieldValidator = FieldValidatorBuilder.alphanumeric()
                    .build();

            FieldValidator numericFieldValidator = FieldValidatorBuilder.numeric()
                    .build();

            FieldValidator preMadeFieldValidator = FieldValidatorBuilder.regex(FieldValidatorBuilder.EMAIL_REGEX)
                    .build();

            FieldValidator regularExpressionFieldValidator = FieldValidatorBuilder.regex("/^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$/")
                    .build();

            FieldValidator requiredLengthFieldValidator = FieldValidatorBuilder.basic()
                    .maxLength(10)
                    .minLength(5)
                    .required()
                    .build();
        }
    }

    private static class SampleB {
        public static void main( String ... args ) {
            FieldBuilder.newField()
                    .atPosition( 100, 200 )
                    .onPage( 0 )
                    .withStyle( FieldStyle.UNBOUND_TEXT_FIELD )
                    .withValidation( FieldValidatorBuilder.regex("/^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$/") );
        }
    }
}
