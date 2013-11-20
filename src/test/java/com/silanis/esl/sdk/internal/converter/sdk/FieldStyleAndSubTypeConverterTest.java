package com.silanis.esl.sdk.internal.converter.sdk;

import com.silanis.esl.api.model.FieldSubtype;
import com.silanis.esl.sdk.FieldStyle;
import com.silanis.esl.sdk.internal.converter.FieldStyleAndSubTypeConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: jessica
 * Date: 19/11/13
 * Time: 3:42 PM
 */
public class FieldStyleAndSubTypeConverterTest {

    @Test
    public void toAPIFieldSubtype() {
        FieldStyle fieldStyle = FieldStyle.UNBOUND_CUSTOM_FIELD;
        FieldSubtype fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertEquals( fieldSubtype, FieldSubtype.CUSTOMFIELD );

        fieldStyle = FieldStyle.UNBOUND_TEXT_FIELD;
        fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertEquals( fieldSubtype, FieldSubtype.TEXTFIELD );

        fieldStyle = FieldStyle.LABEL;
        fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertEquals( fieldSubtype, FieldSubtype.LABEL );

        fieldStyle = FieldStyle.UNBOUND_CHECK_BOX;
        fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertEquals( fieldSubtype, FieldSubtype.CHECKBOX);

        fieldStyle = FieldStyle.BOUND_DATE;
        fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertEquals( fieldSubtype, FieldSubtype.LABEL);

        fieldStyle = FieldStyle.BOUND_NAME;
        fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertEquals( fieldSubtype, FieldSubtype.LABEL);

        fieldStyle = FieldStyle.BOUND_TITLE;
        fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertEquals( fieldSubtype, FieldSubtype.LABEL);

        fieldStyle = FieldStyle.BOUND_COMPANY;
        fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertEquals( fieldSubtype, FieldSubtype.LABEL);

    }

    public void toSDKFieldStyle() {

        String BINDING_DATE = "{approval.signed}";
        String BINDING_TITLE = "{signer.title}";
        String BINDING_NAME = "{signer.name}";
        String BINDING_COMPANY = "{signer.company}";

        // Where the conversion is based on subtype.
        String binding;
        FieldSubtype fieldSubtype;
        FieldStyle fieldStyle;

        fieldSubtype = null;
        binding = null;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertEquals( fieldStyle, FieldStyle.BOUND_COMPANY);

        fieldSubtype = FieldSubtype.CUSTOMFIELD;
        binding = null;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertEquals( fieldStyle, FieldStyle.UNBOUND_CUSTOM_FIELD);

        fieldSubtype = FieldSubtype.TEXTFIELD;
        binding = null;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertEquals( fieldStyle, FieldStyle.UNBOUND_TEXT_FIELD);

        fieldSubtype = FieldSubtype.CHECKBOX;
        binding = null;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertEquals( fieldStyle, FieldStyle.UNBOUND_CHECK_BOX);


        // Where the conversion is based on binding.
        fieldSubtype = null;
        binding = BINDING_DATE;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertEquals( fieldStyle, FieldStyle.BOUND_DATE);

        fieldSubtype = null;
        binding = BINDING_TITLE;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertEquals( fieldStyle, FieldStyle.BOUND_TITLE);

        fieldSubtype = null;
        binding = BINDING_NAME;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertEquals( fieldStyle, FieldStyle.BOUND_NAME);

        fieldSubtype = null;
        binding = BINDING_COMPANY;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertEquals( fieldStyle, FieldStyle.BOUND_COMPANY);


    }
}
