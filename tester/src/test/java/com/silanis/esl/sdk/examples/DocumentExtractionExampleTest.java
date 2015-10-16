package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import org.apache.commons.lang3.ObjectUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.silanis.esl.sdk.FieldStyle.*;
import static com.silanis.esl.sdk.examples.DocumentExtractionExample.DOCUMENT_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * User: jessica
 * Date: 10/01/14
 * Time: 3:15 PM
 *
 * Test DocumentExtractionExample.
 *
 */
public class DocumentExtractionExampleTest {

    /* The test is written based on scaling factor 1.3 but it should work with 1.33333 */
    private double scalingFactor = 1.3d;
    private double scalingFactorTolerance = 0.1d;

    @Test
    public void verifyResult() {
        DocumentExtractionExample example = new DocumentExtractionExample( Props.get() );
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        // Verify if the fields were injected correctly into the document.
        Document document = documentPackage.getDocument(DOCUMENT_NAME);
        List<Signature> actualSignatures = (ArrayList<Signature>) document.getSignatures();

        assertThat("The number of signatures in extracted document is wrong", actualSignatures.size(), CoreMatchers.is(6));

        Signature signature0 = signatureForTopLeft(actualSignatures, 224, 90);
        assertThat(signature0, is(notNullValue()));
        assertSignature(signature0, 3);
        final List<Field> fields0 = convertList(signature0.getFields());
        assertField(fields0, BOUND_NAME, "{signer.name}", 225, 303, 195, 28, 0);
        assertField(fields0, UNBOUND_CHECK_BOX, null, 283, 94, 85, 28, 0);
        assertField(fields0, BOUND_NAME, "{signer.name}", 222, 537, 195, 28, 0);
        assertSignature(signature0, SignatureStyle.HAND_DRAWN, 224, 90, 195, 28, 0);

        Signature signature1 = signatureForTopLeft(actualSignatures, 345, 93);
        assertThat(signature1, is(notNullValue()));
        assertSignature(signature1, 2);
        final List<Field> fields1 = convertList(signature1.getFields());
        assertField(fields1, UNBOUND_TEXT_FIELD, null, 343, 315, 195, 28, 0);
        assertField(fields1, BOUND_TITLE, "{signer.title}", 342, 527, 195, 28, 0);
        assertSignature(signature1, SignatureStyle.HAND_DRAWN, 345, 93, 195, 28, 0);

        Signature signature2 = signatureForTopLeft(actualSignatures, 81, 89);
        assertThat(signature2, is(notNullValue()));
        assertSignature(signature2, 0);
        assertSignature(signature2, SignatureStyle.INITIALS, 81, 89, 195, 28, 0);

        Signature signature3 = signatureForTopLeft(actualSignatures, 131, 541);
        assertThat(signature3, is(notNullValue()));
        assertSignature(signature3, 1);
        final List<Field> fields3 = convertList(signature3.getFields());
        assertField(fields3, BOUND_COMPANY, "{signer.company}", 170, 542, 195, 28, 0);
        assertSignature(signature3, SignatureStyle.FULL_NAME, 131, 541, 195, 28, 0);

        Signature signature4 = signatureForTopLeft(actualSignatures, 726, 91);
        assertThat(signature4, is(notNullValue()));
        assertSignature(signature4, 2);
        final List<Field> fields4 = convertList(signature4.getFields());
        assertField(fields4, BOUND_NAME, "{signer.name}", 724, 299, 195, 28, 0);
        assertField(fields4, BOUND_DATE, "{approval.signed}", 724, 509, 195, 28, 0);
        assertSignature(signature4, SignatureStyle.HAND_DRAWN, 726, 91, 195, 28, 0);

        Signature signature5 = signatureForTopLeft(actualSignatures, 43, 54);
        assertThat(signature5, is(notNullValue()));
        assertSignature(signature5, 2);
        final List<Field> fields5 = convertList(signature5.getFields());
        assertField(fields5, BOUND_NAME, "{signer.name}", 42, 262, 195, 28, 1);
        assertField(fields5, BOUND_DATE, "{approval.signed}", 41, 471, 195, 28, 1);
        assertSignature(signature5, SignatureStyle.HAND_DRAWN, 43, 54, 195, 28, 1);
    }

    private Signature signatureForTopLeft(List<Signature> signatures, double top, double left) {
        if (signatures != null) {
            for (Signature signature : signatures) {
                if (areClose(signature.getX(), left) &&
                    areClose(signature.getY(), top)) {
                    return signature;
                }
            }
        }
        return null;
    }

    private void assertField(Collection<Field> fields, FieldStyle subtype, String binding,
                             int top, int left, int width, int height, int pageIndex) {

        boolean matches = false;
        for (Field field : fields) {
            if (ObjectUtils.equals(field.getBinding(), binding) &&
                    ObjectUtils.equals(field.getStyle(), subtype) &&
                    areClose(field.getY(), top) &&
                    areClose(field.getX(), left) &&
                    areClose(field.getWidth(), width) &&
                    areClose(field.getHeight(), height) &&
                    ObjectUtils.equals(field.getPage(), pageIndex)) {

                matches = true;
                break;
            }
        }

        assertThat("Fields contain expected field.", matches);
    }

    private void assertSignature(Signature signature, SignatureStyle style,
                                 int top, int left, int width, int height, int pageIndex) {

        boolean matches = false;
        if (ObjectUtils.equals(signature.getStyle(), style) &&
                areClose(signature.getY(), top) &&
                areClose(signature.getX(), left) &&
                areClose(signature.getWidth(), width) &&
                areClose(signature.getHeight(), height) &&
                ObjectUtils.equals(signature.getPage(), pageIndex)) {
            matches = true;
        }

        assertThat("Signature contain expected signature.", matches);
    }

    /**
     * Compares values considering the scaling factor and scaling factor tolerance.
     */
    private boolean areClose(Number number, Number other) {
        double numberValue = number.doubleValue();
        double otherValue = other.doubleValue();
        double minValue = (otherValue / scalingFactor) * (scalingFactor - scalingFactorTolerance);
        double maxValue = (otherValue / scalingFactor) * (scalingFactor + scalingFactorTolerance);
        return numberValue > minValue && numberValue < maxValue;
    }

    private void assertSignature(Signature signature, int numbOfFields) {
        assertThat("Wrong number of fields for signature.", signature.getFields().size(), CoreMatchers.is(numbOfFields));
    }

    private <T> ArrayList<T> convertList(Collection<T> collection){
        ArrayList<T> list = new ArrayList<T>();
        for (T type : collection){
            list.add(type);
        }
        return list;
    }
}
