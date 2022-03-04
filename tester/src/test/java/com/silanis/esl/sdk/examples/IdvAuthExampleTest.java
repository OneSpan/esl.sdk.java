package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.IdvWorkflow;
import com.silanis.esl.sdk.IdvWorkflowConfig;
import com.silanis.esl.sdk.Signer;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.silanis.esl.sdk.AuthenticationMethod.IDV;
import static com.silanis.esl.sdk.builder.IdvWorkflowConfigBuilder.newIdvWorkflowConfig;
import static com.silanis.esl.sdk.examples.IdvAuthExample.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.fail;

/**
 * Created by schoi on 2020-10-01.
 */
public class IdvAuthExampleTest {

    @Test
    public void verifyResult() {
        IdvAuthExample example = new IdvAuthExample();
        example.run();
        DocumentPackage documentPackage = example.getRetrievedPackage();

        assertIdvWorkflowConfigs(example.idvWorkflowConfigsBeforeCreating, new ArrayList<IdvWorkflowConfig>());
        assertIdvWorkflowConfigs(example.idvWorkflowConfigsAfterCreating, singletonList(newIdvWorkflowConfig(IDV_WORKFLOW_ID1)
                .withType(TYPE1)
                .withTenant(TENANT)
                .withDesc(DESC1)
                .enableSkipWhenAccessingSignedDocuments()
                .build()));
        assertIdvWorkflowConfigs(example.idvWorkflowConfigsAfterUpdating, asList(newIdvWorkflowConfig(IDV_WORKFLOW_ID1)
                .withType(TYPE1)
                .withTenant(TENANT)
                .withDesc(DESC1)
                .enableSkipWhenAccessingSignedDocuments()
                .build(), newIdvWorkflowConfig(IDV_WORKFLOW_ID2)
                .withType(TYPE2)
                .withTenant(TENANT)
                .withDesc(DESC2)
                .enableSkipWhenAccessingSignedDocuments()
                .build()));
        assertIdvWorkflowConfigs(example.idvWorkflowConfigsAfterDeleting, new ArrayList<IdvWorkflowConfig>());

        Signer signer = documentPackage.getSigner(example.email1);
        assertThat(signer.getAuthenticationMethod(), is(IDV));
        assertThat(signer.getChallengeQuestions(), hasSize(0));
        assertThat(signer.getPhoneNumber(), is(PHONE_NUMBER));

        IdvWorkflow idvWorkflow = signer.getAuthentication().getIdvWorkflow();
        assertThat(idvWorkflow.getId(), is(IDV_WORKFLOW_ID1));
        assertThat(idvWorkflow.getTenant(), is(TENANT));
    }

    private void assertIdvWorkflowConfigs(List<IdvWorkflowConfig> actual, List<IdvWorkflowConfig> expected) {
        assertThat("The idvWorkflowConfig is not set correctly.", actual, hasSize(expected.size()));

        if (actual.isEmpty()) {
            return;
        }

        for (IdvWorkflowConfig config : expected) {
            IdvWorkflowConfig idvWorkflowConfig = findById(config.getId(), actual);
            if (idvWorkflowConfig == null) {
                fail("The idvWorkflowConfig is not set correctly");
            }
            assertIdvWorkflowConfig(idvWorkflowConfig, config);
        }
    }

    private IdvWorkflowConfig findById(String id, List<IdvWorkflowConfig> idvWorkflowConfigs) {
        for (IdvWorkflowConfig config : idvWorkflowConfigs) {
            if (StringUtils.equals(config.getId(), id)) {
                return config;
            }
        }
        return null;
    }

    private void assertIdvWorkflowConfig(IdvWorkflowConfig actual, IdvWorkflowConfig expected) {
        assertThat("The idvWorkflowConfig id is not set correctly.", actual.getId(), is(expected.getId()));
        assertThat("The idvWorkflowConfig type is not set correctly.", actual.getType(), is(expected.getType()));
        assertThat("The idvWorkflowConfig desc is not set correctly.", actual.getDesc(), is(expected.getDesc()));
        assertThat("The idvWorkflowConfig skipWhenAccessingSignedDocuments is not set correctly.", actual.isSkipWhenAccessingSignedDocuments(), is(expected.isSkipWhenAccessingSignedDocuments()));
    }
}