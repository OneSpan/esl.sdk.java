package com.silanis.esl.sdk.examples;

import java.util.Map;

public class SigningStyleExample extends SDKSample {

    public Map<String, Object> createdSigningThemes, retrievedSigningThemes, updatedSigningThemes, removedSigningThemes;
    public String signingThemesStringToCreate = "{\"default\":{\"color\":{\"primary\":\"#5940C3\"}}}";
    public String signingThemesStringToUpdate = "{\"default\":{\"color\":{\"primary\":\"#5940C3\",\"secondary\": \"#F31C8B\"}}}";

    public static void main(String... args) {
        new SigningStyleExample().run();
    }

    @Override
    protected void execute() {

        // Create default signing theme
        createdSigningThemes = eslClient.getSigningStyleService().createSigningThemes(signingThemesStringToCreate);

        // Get default signing theme
        retrievedSigningThemes = eslClient.getSigningStyleService().getSigningThemes();

        // Update the default signing theme
        updatedSigningThemes = eslClient.getSigningStyleService().updateSigningThemes(signingThemesStringToUpdate);

        // Delete default signing theme
        eslClient.getSigningStyleService().deleteSigningThemes();

        removedSigningThemes = eslClient.getSigningStyleService().getSigningThemes();
    }
}