package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.service.AccountConfigService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Locale.ENGLISH;

/**
 * @author singhlo1
 */
public class DeclineReasonExample extends SDKSample {

    public List<String> createdDeclineReasons, retrievedDeclineReasons, updatedDeclineReasons, declineReasonsAfterDelete;
    public List<String> declineReasonsCreateList = new ArrayList<String>(Arrays.asList("Decline Reason 1","Decline Reason 2"));
    public List<String> declineReasonsUpdateList = new ArrayList<String>(Arrays.asList("Decline Reason 1","Decline Reason 3"));

    public static void main(String... args) {
        new DeclineReasonExample().run();
    }

    @Override
    protected void execute() {
        AccountConfigService accountConfigService = eslClient.getAccountConfigService();

        createdDeclineReasons = accountConfigService.createDeclineReasons(ENGLISH, declineReasonsCreateList);

        updatedDeclineReasons = accountConfigService.updateDeclineReasons(ENGLISH,declineReasonsUpdateList);

        accountConfigService.deleteDeclineReasons(ENGLISH);

        declineReasonsAfterDelete = retrievedDeclineReasons = accountConfigService.getDeclineReasons(ENGLISH);
    }
}
