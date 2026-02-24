package com.silanis.esl.sdk.examples;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.silanis.esl.sdk.ChooseSignatureOptions;
import com.silanis.esl.sdk.ChooseSignatureSettings;
import com.silanis.esl.sdk.ChooseSignatureStyleType;
import com.silanis.esl.sdk.builder.ChooseSignatureOptionsBuilder;
import com.silanis.esl.sdk.builder.ChooseSignatureSettingsBuilder;

public class ChooseSignatureOptionsExample extends SDKSample {

    public ChooseSignatureSettings chooseSignatureSettingsAfterPatch, chooseSignatureSettingsAfterDelete;

    public static void main(String... args) {
        new ChooseSignatureOptionsExample().run();
    }

    @Override
    protected void execute() {

        Map<String, List<String>> fontsForWritingSystems = new HashMap<>();
        //supported writing systems are: arabic, chinese-traditional, chinese-simplified, cyrillic, greek, japanese, korean, latin
        fontsForWritingSystems.put("latin",Arrays.asList("Kanit", "Licorice"));
        fontsForWritingSystems.put("arabic",Arrays.asList("Beiruti"));
        ChooseSignatureOptions chooseSignatureOptionsToPatch = ChooseSignatureOptionsBuilder.newChooseSignatureOptions()
                .allowDrawing()
                .disableStyling()
                .allowUploading()
                .disableMobileSigning()
                .withDefaultSignatureType(ChooseSignatureStyleType.DRAW)
                .withFontsPerWritingSystem(fontsForWritingSystems)
                .build();

        ChooseSignatureSettings chooseSignatureSettingsToPatch = ChooseSignatureSettingsBuilder.newChooseSignatureSettings()
                .withChooseSignatureOptions(chooseSignatureOptionsToPatch)
                .build();

        // Configure Choose Signature Settings
        eslClient.getSigningStyleService().patchChooseSignatureSettings(chooseSignatureSettingsToPatch);

        // Get Choose Signature Settings after patch
        chooseSignatureSettingsAfterPatch = eslClient.getSigningStyleService().getChooseSignatureSettings();

        // Delete Choose Signature Settings
        eslClient.getSigningStyleService().deleteChooseSignatureOptions();

        // Get Choose Signature Settings after delete
        chooseSignatureSettingsAfterDelete = eslClient.getSigningStyleService().getChooseSignatureSettings();

    }
}