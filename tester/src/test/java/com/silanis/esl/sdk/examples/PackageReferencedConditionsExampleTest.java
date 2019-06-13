package com.silanis.esl.sdk.examples;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import com.silanis.esl.sdk.ReferencedDocument;
import com.silanis.esl.sdk.ReferencedField;
import com.silanis.esl.sdk.ReferencedFieldConditions;
import java.util.List;
import org.junit.Test;

public class PackageReferencedConditionsExampleTest {

    @Test
    public void verifyResult() {
        PackageReferencedConditionsExample example = new PackageReferencedConditionsExample();
        example.run();

        assertThat("ReferencedConditions should not be null", example.packageLevelRefConditions, notNullValue());
        assertThat("Package level request should return conditions from both documents", example.packageLevelRefConditions.getDocuments(), hasSize(2));

        List<ReferencedDocument> documentLevelRefConditionsDocuments = example.documentLevelRefConditions.getDocuments();
        assertThat("Document level request should return conditions from this particular document only", documentLevelRefConditionsDocuments, hasSize(1));
        assertThat("Document level request should return conditions from all the fields from this particular document",
            documentLevelRefConditionsDocuments.get(0).getFields(), hasSize(3));

        List<ReferencedField> fieldLevelRefConditionsDoc1Fields = example.fieldLevelRefConditions.getDocuments().get(0).getFields();
        assertThat("Field level request should return conditions from this particular field only", fieldLevelRefConditionsDoc1Fields, hasSize(1));

        ReferencedFieldConditions field1Conditions = fieldLevelRefConditionsDoc1Fields.get(0).getConditions();
        assertThat("Field1 should be referenced twice in condition logic in this example", field1Conditions.getReferencedInCondition(), hasSize(2));
        assertThat("Field1 should not be referenced in action logic in this example", field1Conditions.getReferencedInAction(), hasSize(0));
    }
}