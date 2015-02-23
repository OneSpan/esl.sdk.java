package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import org.apache.commons.lang3.ObjectUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Double.compare;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: jessica
 * Date: 10/01/14
 * Time: 3:15 PM
 *
 * Test DocumentExtractionExample.
 *
 */
public class DocumentExtractionExampleTest {

    @Test
    public void verifyResult() {
        DocumentExtractionExample example = new DocumentExtractionExample( Props.get() );
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        // Verify if the fields were injected correctly into the document.
        Document document = documentPackage.getDocument(example.DOCUMENT_NAME);

        assertThat("The number of signatures in extracted document is wrong", document.getSignatures().size(), CoreMatchers.is(6));

        Signature signature0 = ((ArrayList<Signature>)document.getSignatures()).get(2);
        assertSignature(signature0, 3);
        final List<Field> fields0 = convertList(signature0.getFields());

        assertField(fields0, FieldStyle.BOUND_NAME, "{signer.name}", 225, 303, 195, 28, 0);
        assertField(fields0, FieldStyle.UNBOUND_CHECK_BOX, null, 283, 94, 85, 28, 0);
        assertField(fields0, FieldStyle.BOUND_NAME, "{signer.name}", 222, 537, 195, 28, 0);
        assertSignature(signature0, SignatureStyle.HAND_DRAWN, 224, 90, 195, 28, 0);

        //Assert signature #1
        Signature signature1 = convertList(document.getSignatures()).get(3);
        assertSignature(signature1, 2);
        final List<Field> fields1 = convertList(signature1.getFields());
        assertField(fields1, FieldStyle.UNBOUND_TEXT_FIELD, null, 343, 315, 195, 28, 0);
        assertField(fields1, FieldStyle.BOUND_TITLE, "{signer.title}", 342, 527, 195, 28, 0);
        assertSignature(signature1, SignatureStyle.HAND_DRAWN, 345, 93, 195, 28, 0);

        //Assert signature #2
        Signature signature2 = convertList(document.getSignatures()).get(1);
        assertSignature(signature2, 0);
        assertSignature(signature2, SignatureStyle.INITIALS, 81, 89, 195, 28, 0);

        //Assert signature #3
        Signature signature3 = convertList(document.getSignatures()).get(0);
        assertSignature(signature3, 1);
        final List<Field> fields3 = convertList(signature3.getFields());
        assertField(fields3, FieldStyle.BOUND_COMPANY, "{signer.company}", 170, 542, 195, 28, 0);
        assertSignature(signature3, SignatureStyle.FULL_NAME, 131, 541, 195, 28, 0);

        //Assert signature #4
        Signature signature4 = convertList(document.getSignatures()).get(5);
        assertSignature(signature4, 2);
        final List<Field> fields4 = convertList(signature4.getFields());
        assertField(fields4, FieldStyle.BOUND_NAME, "{signer.name}", 724, 299, 195, 28, 0);
        assertField(fields4, FieldStyle.BOUND_DATE, "{approval.signed}", 724, 509, 195, 28, 0);
        assertSignature(signature4, SignatureStyle.HAND_DRAWN, 726, 91, 195, 28, 0);

        //Assert signature #5
        Signature signature5 = convertList(document.getSignatures()).get(4);
        assertSignature(signature5, 2);
        final List<Field> fields5 = convertList(signature5.getFields());
        assertField(fields5, FieldStyle.BOUND_NAME, "{signer.name}", 42, 262, 195, 28, 1);
        assertField(fields5, FieldStyle.BOUND_DATE, "{approval.signed}", 41, 471, 195, 28, 1);
        assertSignature(signature5, SignatureStyle.HAND_DRAWN, 43, 54, 195, 28, 1);
    }

    private void assertField(Collection<Field> fields, FieldStyle subtype, String binding,
                             int top, int left, int width, int height, int pageIndex) {

        boolean matches = false;
        for (Field field : fields) {
            if (ObjectUtils.equals(field.getBinding(), binding) &&
                    ObjectUtils.equals(field.getStyle(), subtype) &&
                    equalsPlusOrMinusOne(field.getY(), top) &&
                    equalsPlusOrMinusOne(field.getX(), left) &&
                    equalsPlusOrMinusOne(field.getWidth(), width) &&
                    equalsPlusOrMinusOne(field.getHeight(), height) &&
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
                equalsPlusOrMinusOne(signature.getY(), top) &&
                equalsPlusOrMinusOne(signature.getX(), left) &&
                equalsPlusOrMinusOne(signature.getWidth(), width) &&
                equalsPlusOrMinusOne(signature.getHeight(), height) &&
                ObjectUtils.equals(signature.getPage(), pageIndex)) {
            matches = true;
        }

        assertThat("Signature contain expected signature.", matches);
    }

    private boolean equalsPlusOrMinusOne(Number number, Number other) {
        return compare(Math.floor(number.doubleValue()), other.doubleValue()) == 0 ||
                compare(Math.ceil(number.doubleValue()), other.doubleValue()) == 0;
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
