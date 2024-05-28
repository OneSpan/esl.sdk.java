package com.silanis.esl.sdk.examples;

import com.silanis.esl.api.model.SupportedLanguages;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AccountLimitSupportedLanguagesSettingsExampleTest {

    @Test
    public void verifyResult() {
        AccountLimitSupportedLanguagesSettingsExample example = new AccountLimitSupportedLanguagesSettingsExample();
        example.run();
        SupportedLanguages supportedLanguages = new SupportedLanguages();
        List<String> signerLanguages = Arrays.asList("en", "fr");
        supportedLanguages.setDefaultSignerLanguage("fr");
        supportedLanguages.setSignerLanguages(signerLanguages);

        assertTrue("'signer languages list in supportedLanguages' in AccountLimitSupportedLanguagesSettings should be updated correctly", compareLists(example.patchedLimitSupportedLanguagesSettings.getSupportedLanguages().getSignerLanguages(),supportedLanguages.getSignerLanguages()));
        assertTrue("'default signer language in supportedLanguages' in AccountLimitSupportedLanguagesSettings should be updated correctly", example.patchedLimitSupportedLanguagesSettings.getSupportedLanguages().getDefaultSignerLanguage().equals(supportedLanguages.getDefaultSignerLanguage()));
        assertTrue("'enableLimitSupportedLanguages' in AccountLimitSupportedLanguagesSettings should be updated correctly", example.patchedLimitSupportedLanguagesSettings.getEnableLimitSupportedLanguages() == Boolean.TRUE);

        assertTrue("'signer languages list in supportedLanguages' in AccountLimitSupportedLanguagesSettings should be updated correctly", compareLists(example.patchedLimitSupportedLanguagesSettings1.getSupportedLanguages().getSignerLanguages(),supportedLanguages.getSignerLanguages()));
        assertTrue("'default signer language in supportedLanguages' in AccountLimitSupportedLanguagesSettings should be updated correctly", example.patchedLimitSupportedLanguagesSettings1.getSupportedLanguages().getDefaultSignerLanguage().equals(supportedLanguages.getDefaultSignerLanguage()));
        assertTrue("'enableLimitSupportedLanguages' in AccountLimitSupportedLanguagesSettings should be updated correctly", example.patchedLimitSupportedLanguagesSettings1.getEnableLimitSupportedLanguages() == Boolean.FALSE);
    }

    public static boolean compareLists(List<String> list1, List<String> list2) {
        // Check if lists are of the same size
        if (list1.size() != list2.size()) {
            return false;
        }
        // Iterate through each element and compare
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }
}
