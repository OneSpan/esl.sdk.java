package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.FieldStyle;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.FieldBuilder.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class FieldBuilderTest {

    public static final double DEFAULT_DOUBLE_TOLERANCE = 0.01f;

    @Test
    public void buildWithSpecifiedValues() {
        double x = 1;
        double y = 2;
        int page = 3;
        double width = 4;
        double height = 5;
        FieldStyle style = FieldStyle.BOUND_DATE;

        FieldBuilder builder = newField()
                .atPosition( x, y )
                .onPage( page )
                .withSize( width, height )
                .withStyle( style );

        Field field = builder.build();

        assertEquals( x, field.getX(), DEFAULT_DOUBLE_TOLERANCE );
        assertEquals( y, field.getY(), DEFAULT_DOUBLE_TOLERANCE );
        assertEquals( page, field.getPage() );
        assertEquals( width, field.getWidth(), DEFAULT_DOUBLE_TOLERANCE );
        assertEquals( height, field.getHeight(), DEFAULT_DOUBLE_TOLERANCE );
        assertEquals( style, field.getStyle() );
    }

    @Test
    public void buildWithDefaultValues() {
        Field field = newField().atPosition( 100, 100 ).build();

        assertEquals( DEFAULT_WIDTH, field.getWidth(), DEFAULT_DOUBLE_TOLERANCE );
        assertEquals( DEFAULT_HEIGHT, field.getHeight(), DEFAULT_DOUBLE_TOLERANCE );
        assertEquals( DEFAULT_STYLE, field.getStyle() );
    }

    @Test
    public void creatingNewSignatureDateFieldSetsStyle() {
        Field field = signatureDate().atPosition( 100, 100 ).build();

        assertThat( field.getStyle(), is( equalTo( FieldStyle.BOUND_DATE ) ) );
    }

    @Test
    public void creatingNewSignerNameFieldSetsStyle() {
        Field field = signerName().atPosition( 100, 100 ).build();

        assertThat( field.getStyle(), is( equalTo( FieldStyle.BOUND_NAME ) ) );
    }

    @Test
    public void creatingNewSignerTitleFieldSetsStyle() {
        Field field = signerTitle().atPosition( 100, 100 ).build();

        assertThat( field.getStyle(), is( equalTo( FieldStyle.BOUND_TITLE ) ) );
    }

    @Test
    public void creatingNewSignerCompanyFieldSetsStyle() {
        Field field = signerCompany().atPosition( 100, 100 ).build();

        assertThat( field.getStyle(), is( equalTo( FieldStyle.BOUND_COMPANY ) ) );
    }

    @Test
    public void creatingTextFieldSetsStyle() {
        Field field = textField().atPosition( 100, 100 ).build();

        assertThat( field.getStyle(), is( equalTo( FieldStyle.UNBOUND_TEXT_FIELD ) ) );
    }

    @Test
    public void creatingCustomFieldSetsStyle() {
        Field field = customField("myCustomField").atPosition( 100, 100 ).build();

        assertThat( field.getStyle(), is( equalTo( FieldStyle.UNBOUND_CUSTOM_FIELD ) ) );
    }

    @Test
    public void creatingCheckBoxFieldSetsStyle() {
        Field field = checkBox().atPosition( 100, 100 ).build();

        assertThat( field.getStyle(), is( equalTo( FieldStyle.UNBOUND_CHECK_BOX ) ) );
    }

    @Test
    public void creatingRadioButtonFieldSetsStyle(){
        Field field = radioButton("group").atPosition( 100, 100 ).build();

        assertThat( field.getStyle(), is( equalTo(FieldStyle.UNBOUND_RADIO_BUTTON)));
    }

    @Test
    public void creatingTextAreaFieldSetsStyle(){
        Field field = textArea().atPosition( 100, 100 ).build();

        assertThat( field.getStyle(), is( equalTo(FieldStyle.TEXT_AREA)));
    }

    @Test
    public void creatingDropListFieldSetsStyle(){
        Field field = dropList().atPosition( 100, 100 ).build();

        assertThat( field.getStyle(), is( equalTo(FieldStyle.DROP_LIST)));
    }

    @Test
    public void creatingQRCodeStyle() {
        Field field = qrCode().atPosition( 100, 100 ).build();

        assertThat( field.getStyle(), is( equalTo(FieldStyle.BOUND_QRCODE)));
        assertThat( field.getHeight(), is(77.0));
        assertThat( field.getWidth(), is(77.0));
    }
}