package com.silanis.esl.sdk.examples;

import org.junit.Test;


import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AccountUploadSettingsExampleTest {

    @Test
    public void verifyResult() {

        AccountUploadSettingsExample example = new AccountUploadSettingsExample();
        example.run();

        //account upload settings
        assertNotNull("'allowedFileTypes' in AccountUploadSettings should be returned", example.defaultAccountUploadSettings.getAllowedFileTypes());

        assertTrue("'allowedFileTypes' in AccountUploadSettings should be updated correctly", example.updatedAccountUploadSettings.getAllowedFileTypes().containsAll(Arrays.asList("\"TESTFILETYPE\"")));

        assertTrue("'allowedFileTypes' in AccountUploadSettings should be updated correctly", example.updatedAccountUploadSettings1.getAllowedFileTypes().
                containsAll(Arrays.asList("\"TESTFILETYPE1\"","\"TESTFILETYPE2\"","\"TESTFILETYPE3\"")));

    }
}
