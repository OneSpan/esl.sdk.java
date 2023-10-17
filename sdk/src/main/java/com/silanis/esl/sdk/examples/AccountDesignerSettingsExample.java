package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AccountDesignerSettings;
import com.silanis.esl.sdk.builder.AccountDesignerSettingsBuilder;

public class AccountDesignerSettingsExample extends SDKSample{
    public AccountDesignerSettings defaultAccountDesignerSettings, patchedAccountDesignerSettings, deletedAccountDesignerSettings,patchedAccountDesignerSettings1;

    public static void main(String... args) {
        new AccountDesignerSettingsExample().run();
    }

    @Override
    protected void execute() {

        //Get account designer settings
        defaultAccountDesignerSettings = eslClient.getAccountConfigService().getAccountDesignerSettings();

        AccountDesignerSettings accountDesignerSettings = AccountDesignerSettingsBuilder.newAccountDesignerSettings()
                .withSend()
                .withDone()
                .withoutSettings()
                .withoutDocumentVisibility()
                .withAddDocument()
                .withEditDocument()
                .withoutDeleteDocument()
                .withoutAddSigner()
                .withEditRecipient()
                .withRolePickerSender()
                .withoutSaveLayout()
                .withoutApplyLayout()
                .withShowSharedLayouts()
                .build();

        //Save account designer settings
        eslClient.getAccountConfigService().saveAccountDesignerSettings(accountDesignerSettings);
        patchedAccountDesignerSettings = eslClient.getAccountConfigService().getAccountDesignerSettings();

        //Delete account designer settings
        eslClient.getAccountConfigService().deleteAccountDesignerSettings();
        deletedAccountDesignerSettings = eslClient.getAccountConfigService().getAccountDesignerSettings();

        accountDesignerSettings = AccountDesignerSettingsBuilder.newAccountDesignerSettings()
                .withSend()
                .withoutDone()
                .withSettings()
                .withoutDocumentVisibility()
                .withAddDocument()
                .withoutEditDocument()
                .withDeleteDocument()
                .withoutAddSigner()
                .withEditRecipient()
                .withoutRolePickerSender()
                .withSaveLayout()
                .withApplyLayout()
                .withoutShowSharedLayouts()
                .withDefaultSignatureType("capture")
                .build();

        //Save account designer settings
        eslClient.getAccountConfigService().saveAccountDesignerSettings(accountDesignerSettings);
        patchedAccountDesignerSettings1 = eslClient.getAccountConfigService().getAccountDesignerSettings();

        //Delete account designer settings
        eslClient.getAccountConfigService().deleteAccountDesignerSettings();
    }
}
