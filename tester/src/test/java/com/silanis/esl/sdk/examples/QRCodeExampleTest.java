package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.FieldStyle;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;

/**
 * Created by lena on 2014-07-30.
 */
public class QRCodeExampleTest {

    @Test
    public void verifyResult() {
        QRCodeExample example = new QRCodeExample(Props.get());
        example.run();

        // *** IMPORTANT NOTES ****
        //
        // Do not use "both" if we still need to support JDK 1.6. It is a known bug:
        // https://code.google.com/p/hamcrest/issues/detail?id=82
        //
        // So instead of
        //
        // assertThat("Field x coordinate was not set correctly", field.getX(), is(both(greaterThan(99.0)).and(lessThan(101.0))));
        //
        // do:
        //
        // assertThat("Field x coordinate was not set correctly", field.getX(), is(greaterThan(99.0)));
        // assertThat("Field x coordinate was not set correctly", field.getX(), is(lessThan(101.0)));

        // Verify QR codes were added to document
        assertThat("First QR code's style was not set correctly.", example.addedQRCode1.getStyle(), is(FieldStyle.BOUND_QRCODE));
        assertThat("First QR code's height was not set correctly.", example.addedQRCode1.getHeight(), is(greaterThan(76.0)));
        assertThat("First QR code's height was not set correctly.", example.addedQRCode1.getHeight(), is(lessThan(78.0)));
        assertThat("First QR code's width was not set correctly.", example.addedQRCode1.getWidth(), is(greaterThan(76.0)));
        assertThat("First QR code's width was not set correctly.", example.addedQRCode1.getWidth(), is(lessThan(78.0)));
        assertThat("First QR code's x position was not set correctly.", example.addedQRCode1.getX(), is(greaterThan(399.0)));
        assertThat("First QR code's x position was not set correctly.", example.addedQRCode1.getX(), is(lessThan(401.0)));
        assertThat("First QR code's y position was not set correctly.", example.addedQRCode1.getY(), is(greaterThan(99.0)));
        assertThat("First QR code's y position was not set correctly.", example.addedQRCode1.getY(), is(lessThan(101.0)));

        assertThat("Second QR code's style was not set correctly.", example.addedQRCode2.getStyle(), is(FieldStyle.BOUND_QRCODE));
        assertThat("Second QR code's height was not set correctly.", example.addedQRCode2.getHeight(), is(greaterThan(76.0)));
        assertThat("Second QR code's height was not set correctly.", example.addedQRCode2.getHeight(), is(lessThan(78.0)));
        assertThat("Second QR code's width was not set correctly.", example.addedQRCode2.getWidth(), is(greaterThan(76.0)));
        assertThat("Second QR code's width was not set correctly.", example.addedQRCode2.getWidth(), is(lessThan(78.0)));
        assertThat("Second QR code's x position was not set correctly.", example.addedQRCode2.getX(), is(greaterThan(499.0)));
        assertThat("Second QR code's x position was not set correctly.", example.addedQRCode2.getX(), is(lessThan(501.0)));
        assertThat("Second QR code's y position was not set correctly.", example.addedQRCode2.getY(), is(greaterThan(99.0)));
        assertThat("Second QR code's y position was not set correctly.", example.addedQRCode2.getY(), is(lessThan(101.0)));

        // Assert the first QR code was modified correctly
        List<Field> modifiedQRCodeList = example.modifiedQRCodeList;
        assertThat("Modified QR code list should have two QR codes.", modifiedQRCodeList.size(), is(2));

        for (Field field : modifiedQRCodeList) {
            if (field.getId().equals(example.qrCodeId1)) {
                assertThat("Modified QR code's style was not set correctly.", field.getStyle(), is(FieldStyle.BOUND_QRCODE));
                assertThat("Modified QR code's height was not set correctly.", field.getHeight(), is(greaterThan(76.0)));
                assertThat("Modified QR code's height was not set correctly.", field.getHeight(), is(lessThan(78.0)));
                assertThat("Modified QR code's width was not set correctly.", field.getWidth(), is(greaterThan(76.0)));
                assertThat("Modified QR code's width was not set correctly.", field.getWidth(), is(lessThan(78.0)));
                assertThat("Modified QR code's x position was not set correctly.", field.getX(), is(greaterThan(399.0)));
                assertThat("Modified QR code's x position was not set correctly.", field.getX(), is(lessThan(401.0)));
                assertThat("Modified QR code's y position was not set correctly.", field.getY(), is(greaterThan(499.0)));
                assertThat("Modified QR code's y position was not set correctly.", field.getY(), is(lessThan(501.0)));
            }
        }

        // Assert the second QR code was deleted
        List<Field> deletedQRCodeList = example.deletedQRCodeList;
        assertThat("The second QR code should have been deleted.", deletedQRCodeList.size(), is(1));

        // Assert the QR codes was replaced with the updated ones
        List<Field> updatedQRCodeList = example.updatedQRCodeList;
        for (Field updatedQRCode : updatedQRCodeList) {
            if (updatedQRCode.getId().equals(example.qrCodeId1)) {
                assertThat("First updated QR code's style was not set correctly.", updatedQRCode.getStyle(), is(FieldStyle.BOUND_QRCODE));
                assertThat("First updated QR code's height was not set correctly.", updatedQRCode.getHeight(), is(greaterThan(76.0)));
                assertThat("First updated QR code's height was not set correctly.", updatedQRCode.getHeight(), is(lessThan(78.0)));
                assertThat("First updated QR code's width was not set correctly.", updatedQRCode.getWidth(), is(greaterThan(76.0)));
                assertThat("First updated QR code's width was not set correctly.", updatedQRCode.getWidth(), is(lessThan(78.0)));
                assertThat("First updated QR code's x position was not set correctly.", updatedQRCode.getX(), is(greaterThan(199.0)));
                assertThat("First updated QR code's x position was not set correctly.", updatedQRCode.getX(), is(lessThan(201.0)));
                assertThat("First updated QR code's y position was not set correctly.", updatedQRCode.getY(), is(greaterThan(599.0)));
                assertThat("First updated QR code's y position was not set correctly.", updatedQRCode.getY(), is(lessThan(601.0)));            }
            if (updatedQRCode.getId().equals(example.qrCodeId2)) {
                assertThat("Second updated QR code's style was not set correctly.", updatedQRCode.getStyle(), is(FieldStyle.BOUND_QRCODE));
                assertThat("Second updated QR code's height was not set correctly.", updatedQRCode.getHeight(), is(greaterThan(76.0)));
                assertThat("Second updated QR code's height was not set correctly.", updatedQRCode.getHeight(), is(lessThan(78.0)));
                assertThat("Second updated QR code's width was not set correctly.", updatedQRCode.getWidth(), is(greaterThan(76.0)));
                assertThat("Second updated QR code's width was not set correctly.", updatedQRCode.getWidth(), is(lessThan(78.0)));
                assertThat("Second updated QR code's x position was not set correctly.", updatedQRCode.getX(), is(greaterThan(299.0)));
                assertThat("Second updated QR code's x position was not set correctly.", updatedQRCode.getX(), is(lessThan(301.0)));
                assertThat("Second updated QR code's y position was not set correctly.", updatedQRCode.getY(), is(greaterThan(599.0)));
                assertThat("Second updated QR code's y position was not set correctly.", updatedQRCode.getY(), is(lessThan(601.0)));
            }
        }

    }
}
