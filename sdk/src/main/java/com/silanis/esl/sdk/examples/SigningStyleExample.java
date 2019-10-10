package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.SigningLogo;
import com.silanis.esl.sdk.builder.SigningLogoBuilder;
import com.silanis.esl.sdk.io.Streams;
import sun.misc.BASE64Encoder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SigningStyleExample extends SDKSample {

    public Map<String, Object> createdSigningThemes, retrievedSigningThemes, updatedSigningThemes, removedSigningThemes;
    public String signingThemesStringToCreate = "{\"default\":{\"color\":{\"primary\":\"#5940C3\"}}}";
    public String signingThemesStringToUpdate = "{\"default\":{\"color\":{\"primary\":\"#5940C3\",\"secondary\": \"#F31C8B\"}}}";
    public List<SigningLogo> createdSigningLogos, updatedSigningLogos, removedSigningLogos;

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

        eslClient.getSigningStyleService().deleteSigningLogos();
        List<SigningLogo> signingLogos =  new ArrayList();

        InputStream logoEn = this.getClass().getClassLoader().getResourceAsStream("one_span-logo.png");
        SigningLogo signingLogoEn = SigningLogoBuilder.newSigningLogo()
                .withLanguage(Locale.ENGLISH)
                .withImage(convertImageToURI("png", logoEn))
                .build();
        signingLogos.add(signingLogoEn);
        // Create signing logos
        eslClient.getSigningStyleService().createSigningLogos(signingLogos);
        createdSigningLogos = eslClient.getSigningStyleService().getSigningLogos();

        InputStream logoFr = this.getClass().getClassLoader().getResourceAsStream("one_span-logo.png");
        SigningLogo signingLogoFr = SigningLogoBuilder.newSigningLogo()
                .withLanguage(Locale.FRENCH)
                .withImage(convertImageToURI("png" , logoFr))
                .build();
        signingLogos.add(signingLogoFr);
        // Update signing logos
        eslClient.getSigningStyleService().updateSigningLogos(signingLogos);
        updatedSigningLogos = eslClient.getSigningStyleService().getSigningLogos();

        // Delete signing logos
        eslClient.getSigningStyleService().deleteSigningLogos();
        removedSigningLogos = eslClient.getSigningStyleService().getSigningLogos();
    }

    private String convertImageToURI(String extension, InputStream inputStream){
        byte[] fileContent = Streams.toByteArray( inputStream );
        return "data:image/" + extension + ";base64," + new BASE64Encoder().encode(fileContent);
    }

}