package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AccountSystemSettingPropertiesExampleTest {

    @Test
    public void verifyResult() {

        AccountSystemSettingPropertiesExample example = new AccountSystemSettingPropertiesExample();
        example.run();


        //account system settings
        assertNotNull("'senderLoginMaxFailedAttempts' in AccountSystemSettingProperties should be returned", example.defaultAccountSettingSystemProperties.getSenderLoginMaxFailedAttempts());
        assertNotNull("'loginSessionTimeout' in AccountSystemSettingProperties should be returned", example.deletedAccountSettingSystemProperties.getLoginSessionTimeout());
        assertNotNull("'sessionTimeoutWarning' in AccountSystemSettingProperties should be returned", example.defaultAccountSettingSystemProperties.getSessionTimeoutWarning());
        assertFalse("'OrderLastNameFirstName' in AccountSystemSettingProperties should be false by default", example.defaultAccountSettingSystemProperties.getOrderLastNameFirstName());

        assertTrue("'senderLoginMaxFailedAttempts' in AccountSystemSettingProperties should be updated correctly", example.patchedAccountSettingSystemProperties.getSenderLoginMaxFailedAttempts() == 2);
        assertTrue("'loginSessionTimeout' in AccountSystemSettingProperties should be updated correctly", example.patchedAccountSettingSystemProperties.getLoginSessionTimeout() == 60000);
        assertTrue("'sessionTimeoutWarning' in AccountSystemSettingProperties should be updated correctly", example.patchedAccountSettingSystemProperties.getSessionTimeoutWarning() == 200000);
        assertTrue("'OrderLastNameFirstName' in AccountSystemSettingProperties should be true", example.patchedAccountSettingSystemProperties.getOrderLastNameFirstName());

        assertTrue("'senderLoginMaxFailedAttempts' in AccountSystemSettingProperties should be updated correctly", example.patchedAccountSettingSystemProperties1.getSenderLoginMaxFailedAttempts() == 10);
        assertTrue("'loginSessionTimeout' in AccountSystemSettingProperties should be updated correctly", example.patchedAccountSettingSystemProperties1.getLoginSessionTimeout() == 35000000);
        assertTrue("'sessionTimeoutWarning' in AccountSystemSettingProperties should be updated correctly", example.patchedAccountSettingSystemProperties1.getSessionTimeoutWarning() == 35000000);
        assertFalse("'OrderLastNameFirstName' in AccountSystemSettingProperties should be false", example.patchedAccountSettingSystemProperties1.getOrderLastNameFirstName());
    }
}
