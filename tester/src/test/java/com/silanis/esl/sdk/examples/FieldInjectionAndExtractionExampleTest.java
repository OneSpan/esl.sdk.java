package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: jessica
 * Date: 16/01/14
 * Time: 11:47 AM
 * <p/>
 * Test FieldInjectionAndExtractionExample.
 */
public class FieldInjectionAndExtractionExampleTest {
    @Test
    public void verifyResult() {
        FieldInjectionAndExtractionExample example = new FieldInjectionAndExtractionExample(Props.get());
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        // Verify if the fields were injected correctly into the document.
        Document document = documentPackage.getDocument(example.DOCUMENT_NAME);

        assertThat("The number of signatures in extracted document is wrong", document.getSignatures().size(), is(6));

        Signature signature0 = convertList(document.getSignatures()).get(2);
        assertSignature(signature0, 4);
        final List<Field> fields0 = convertList(signature0.getFields());
/*
        assertField(fields0, FieldType.INPUT, FieldStyle.LABEL, "{signer.name}", 225, 303, 195, 28, 0);
        assertField(fields0, FieldType.INPUT, FieldStyle.UNBOUND_CHECK_BOX, null, 283, 94, 85, 28, 0);
        assertField(fields0, FieldType.INPUT, FieldStyle.LABEL, "{signer.name}", 222, 537, 195, 28, 0);
        assertField(fields0, FieldType.SIGNATURE, FieldStyle.CAPTURE, null, 224, 90, 195, 28, 0);

        //Assert approval #1
        Approval approval1 = document.getApprovals().get(3);
        assertSignature(approval1, SIGNER2_ID, 3);
        final List<Field> fields1 = approval1.getFields();
        assertField(fields1, FieldType.INPUT, FieldSubtype.TEXTFIELD, null, 343, 315, 194, 28, 0);
        assertField(fields1, FieldType.INPUT, FieldSubtype.LABEL, "{signer.title}", 342, 527, 195, 28, 0);
        assertField(fields1, FieldType.SIGNATURE, FieldSubtype.CAPTURE, null, 345, 93, 194, 28, 0);

        //Assert approval #2
        Approval approval2 = document.getApprovals().get(1);
        assertSignature(approval2, SIGNER2_ID, 1);
        assertField(approval2.getFields(), FieldType.SIGNATURE, FieldSubtype.INITIALS, null, 81, 89, 194, 28, 0);

        //Assert approval #3
        Approval approval3 = document.getApprovals().get(0);
        assertSignature(approval3, SIGNER2_ID, 2);
        final List<Field> fields3 = approval3.getFields();
        assertField(fields3, FieldType.INPUT, FieldSubtype.LABEL, "{signer.company}", 170, 542, 194, 28, 0);
        assertField(fields3, FieldType.SIGNATURE, FieldSubtype.FULLNAME, null, 131, 541, 195, 28, 0);

        //Assert approval #4
        Approval approval4 = document.getApprovals().get(5);
        assertSignature(approval4, SIGNER3_ID, 3);
        final List<Field> fields4 = approval4.getFields();
        assertField(fields4, FieldType.INPUT, FieldSubtype.LABEL, "{signer.name}", 724, 299, 195, 28, 0);
        assertField(fields4, FieldType.INPUT, FieldSubtype.LABEL, "{approval.signed}", 724, 509, 195, 28, 0);
        assertField(fields4, FieldType.SIGNATURE, FieldSubtype.CAPTURE, null, 726, 91, 195, 28, 0);

        //Assert approval #5
        Approval approval5 = document.getApprovals().get(4);
        assertSignature(approval5, SIGNER3_ID, 3);
        final List<Field> fields5 = approval5.getFields();
        assertField(fields5, FieldType.INPUT, FieldSubtype.LABEL, "{signer.name}", 42, 262, 195, 28, 1);
        assertField(fields5, FieldType.INPUT, FieldSubtype.LABEL, "{approval.signed}", 41, 471, 195, 28, 1);
        assertField(fields5, FieldType.SIGNATURE, FieldSubtype.CAPTURE, null, 43, 54, 195, 28, 1);*/

    }
/*

    private void assertField(Collection<Field> fields, FieldType type, FieldSubtype subtype, String binding,
                             int top, int left, int width, int height, int pageIndex) {

        boolean matches = false;

        for (Field field : fields) {
            if (ObjectUtils.equals(field.getType(), type) &&
                    ObjectUtils.equals(field.getBinding(), binding) &&
                    ObjectUtils.equals(field.getSubtype(), subtype) &&
                    equalsPlusOrMinusOne(field.getTop(), top) &&
                    equalsPlusOrMinusOne(field.getLeft(), left) &&
                    equalsPlusOrMinusOne(field.getWidth(), width) &&
                    equalsPlusOrMinusOne(field.getHeight(), height) &&
                    ObjectUtils.equals(field.getPage(), pageIndex)) {

                matches = true;
                break;
            }
        }

        MatcherAssert.assertThat("Fields contain expected field.", matches);
    }

    private boolean equalsPlusOrMinusOne(Number number, Number other) {
        return compare(Math.floor(number.doubleValue()), other.doubleValue()) == 0 ||
                compare(Math.ceil(number.doubleValue()), other.doubleValue()) == 0;
    }
*/

    private void assertSignature(Signature signature, int numbOfFields) {
        assertThat("Wrong number of fields for approval.", signature.getFields().size(), is(numbOfFields));
    }

    private <T> ArrayList<T> convertList(Collection<T> collection){
        ArrayList<T> list = new ArrayList<T>();
        for (T type : list){
            list.add(type);
        }
        return list;
    }
}
