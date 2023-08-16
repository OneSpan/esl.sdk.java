package com.silanis.esl.sdk.examples;

import org.junit.Test;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class AccountDesignerSettingsExampleTest {

    @Test
    public void verifyResult() {

        AccountDesignerSettingsExample example = new AccountDesignerSettingsExample();
        example.run();

        //designer settings
        assertNotNull("'send' in AccountDesignerSettings should be returned", example.defaultAccountDesignerSettings.getSend());
        assertNotNull("'done' in AccountDesignerSettings should be returned", example.defaultAccountDesignerSettings.getDone());
        assertNotNull("'settings' in AccountDesignerSettings should be returned", example.defaultAccountDesignerSettings.getSettings());
        assertNotNull("'applyLayout' in AccountDesignerSettings should be returned", example.defaultAccountDesignerSettings.getApplyLayout());
        assertNotNull("'addSigner' in AccountDesignerSettings should be returned", example.defaultAccountDesignerSettings.getAddSigner());
        assertNotNull("'documentVisibility' in AccountDesignerSettings should be returned", example.defaultAccountDesignerSettings.getDocumentVisibility());

        assertTrue("'send' in AccountDesignerSettings should be updated correctly", example.patchedAccountDesignerSettings.getSend());
        assertTrue("'done' in AccountDesignerSettings should be updated correctly", example.patchedAccountDesignerSettings.getDone());
        assertFalse("'settings' in AccountDesignerSettings should be updated correctly",example.patchedAccountDesignerSettings.getSettings());
        assertFalse("'documentVisibility' in AccountDesignerSettings should be updated correctly", example.patchedAccountDesignerSettings.getDocumentVisibility());
        assertFalse("'applyLayout' in AccountDesignerSettings should be updated correctly", example.patchedAccountDesignerSettings.getApplyLayout());
        assertFalse("'addSigner' in AccountDesignerSettings should be updated correctly", example.patchedAccountDesignerSettings.getAddSigner());
        assertTrue("'addDocument' in AccountDesignerSettings should be updated correctly", example.patchedAccountDesignerSettings.getAddDocument());

        assertFalse("'done' in AccountDesignerSettings should be updated correctly", example.patchedAccountDesignerSettings1.getDone());
        assertTrue("'settings' in AccountDesignerSettings should be updated correctly",example.patchedAccountDesignerSettings1.getSettings());
        assertTrue("'applyLayout' in AccountDesignerSettings should be updated correctly", example.patchedAccountDesignerSettings1.getApplyLayout());
        assertFalse("'addSigner' in AccountDesignerSettings should be updated correctly", example.patchedAccountDesignerSettings1.getAddSigner());
        assertTrue("'addDocument' in AccountDesignerSettings should be updated correctly", example.patchedAccountDesignerSettings1.getAddDocument());
        assertTrue("'defaultSignatureType' in AccountDesignerSettings should be updated correctly",example.patchedAccountDesignerSettings1.getDefaultSignatureType().equals("capture"));


    }
}
