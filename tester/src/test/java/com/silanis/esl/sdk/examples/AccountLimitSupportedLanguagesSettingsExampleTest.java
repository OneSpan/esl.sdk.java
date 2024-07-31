package com.silanis.esl.sdk.examples;

import com.silanis.esl.api.model.SupportedLanguages;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AccountLimitSupportedLanguagesSettingsExampleTest {

    private SupportedLanguages supportedLanguages1 = getSupportedLanguages(Arrays.asList("en", "fr"), "fr");
    private SupportedLanguages supportedLanguages2 = getSupportedLanguages(Arrays.asList("en", "de"), "de");
    @Test
    public void verifyResult() {
        AccountLimitSupportedLanguagesSettingsExample example = new AccountLimitSupportedLanguagesSettingsExample();
        example.run();

        //assertTrue("'signer languages list in supportedLanguages' in AccountLimitSupportedLanguagesSettings should be updated correctly", compareLists(example.defaultLimitSupportedLanguagesSettings1.getSignerLanguages(),supportedLanguages1.getSignerLanguages()));
        assertNull("'default signer language in supportedLanguages' in AccountLimitSupportedLanguagesSettings should be null", example.defaultLimitSupportedLanguagesSettings1.getDefaultSignerLanguage());
        assertNull("'default signer languages list in supportedLanguages' in AccountLimitSupportedLanguagesSettings should be null", example.defaultLimitSupportedLanguagesSettings1.getSignerLanguages());

        assertTrue("'signer languages list in supportedLanguages' in AccountLimitSupportedLanguagesSettings should be updated correctly", compareLists(example.patchedLimitSupportedLanguagesSettings1.getSignerLanguages(),supportedLanguages1.getSignerLanguages()));
        assertTrue("'default signer language in supportedLanguages' in AccountLimitSupportedLanguagesSettings should be updated correctly", example.patchedLimitSupportedLanguagesSettings1.getDefaultSignerLanguage().equals(supportedLanguages1.getDefaultSignerLanguage()));

        assertTrue("'signer languages list in supportedLanguages' in AccountLimitSupportedLanguagesSettings should be updated correctly", compareLists(example.patchedLimitSupportedLanguagesSettings2.getSignerLanguages(),supportedLanguages2.getSignerLanguages()));
        assertTrue("'default signer language in supportedLanguages' in AccountLimitSupportedLanguagesSettings should be updated correctly", example.patchedLimitSupportedLanguagesSettings2.getDefaultSignerLanguage().equals(supportedLanguages2.getDefaultSignerLanguage()));

        assertNull("'default signer language in supportedLanguages' in AccountLimitSupportedLanguagesSettings should be null", example.defaultLimitSupportedLanguagesSettings2.getDefaultSignerLanguage());
        assertNull("'default signer languages list in supportedLanguages' in AccountLimitSupportedLanguagesSettings should be null", example.defaultLimitSupportedLanguagesSettings2.getSignerLanguages());

    }

    private SupportedLanguages getSupportedLanguages(List<String> signerLanguages, String defaultSignerLanguage) {
        SupportedLanguages supportedLanguages = new SupportedLanguages();
        supportedLanguages.setDefaultSignerLanguage(defaultSignerLanguage);
        supportedLanguages.setSignerLanguages(signerLanguages);
        return supportedLanguages;
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
