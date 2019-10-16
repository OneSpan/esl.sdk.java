package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.SigningLogo;
import com.silanis.esl.sdk.builder.SigningLogoBuilder;

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

        eslClient.getSigningStyleService().saveSigningLogos(new ArrayList<SigningLogo>());
        List<SigningLogo> signingLogos =  new ArrayList();

        String logoEn = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAQIAAAAoCAIAAACabWPjAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAPHSURBVHhe7ZdNTiMxEIU5CMs5TVacJYucBYmjII4CN5hFlAVCQsp02a/af1XdbsHC8rxPXgTntavseuUOD4QQQgghhBBicCfkPwB294CKkKmB3T2gImRqYHcPqAiZGtjdAypCpgZ294CKkKmB3T2gImRqYHcPqAiZGtjdAypCpgZ294CKkKmB3T2gGoW32+Ofv3Gc3zB3v3+ddfLx8nX/+DyFz6eXb3wvqGYRWLy/XLFC+yyC3l7xdwQLZmkIr5e0SBkrS7Ie1+ePqPl+fsrny4g9e6+wM6/YDKoU+6rPNtK1zqjA7h5QjUJmhVQJ9b2MYAWtWaqEunw1XI5j0KfP9/h9bxtUPggjWXOvDfJdZCNts2/vBbttsBt0wdGk81noWWdoYHcPqEYBdb2eFsOtVZfJMLN8hUl4DmXQIplV8XtG9Z1tgCjaaZpq23hYP7dRaqEURROrF9zbe4ad+UpH0C3NuveedQYHdveAahS0rufllNVG4cTDzPKVWkGtLIVBSQrbKZV3gdQ19Yxtpr02MF4XwGgDx6/IPG6qe++J7TY4ErTU9JxPuc7owO4eUI3CeuLyIRouXEWXr+bQcUWdLrf4KmjtuGBdzA12mVuXY0bGZu3boK5jYuioPLB3xc4c9ARFqpvb6Up+dGB3D6hGYa1ruHqDBcV8y81kFANip0iBnjI7ZrIu+zyi8Qjw2sD4zYY3TFjn0N4jduagJ6iryehKfnRgdw+oRiHVFQ6IZ714wrQCJv1K/HIbCHgL6TB+HP+0Dfr2Lvx6G+ArHSFiV/KjA7t7QDUKqa74PRBmxGqmFVrDVewKBLucsQ0MlwOkarjTawPXx1F5cO9CesSgJ2h9TfhtsL3O8MDuHlCNQlZXOf2nz2epgf7ZFGPf5ahr7WZZra59qWkm2wS86MY89lWloS+WuObBvQvZIwYHglrns6Mp1xkd2N0DqlHI64rPOGjTCvttsFYr8wqeSi96/P7J10G43GG1G1wfWFlpiGxBDaELHty7kD9i0BHUOp/0TkDEnnUGB3b3gGoU8rpqMaJZTSt0tMHCWsVyGLd1PcofxKtj8mH4wM5q9VY5UoiDexectFNWu0EF53zy/4u61hkZ2N0DqlEorjeUJxbjB20grG+AOIz6NZVu/jkW9BaMw74L/ayqRiofP7h3YbcNhM2gSrkv099d64wK7O4BFSFTA7t7QEXI1MDuHlARMjWwuwdUhEwN7O4BFSFTA7t7QEXI1MDuHlARMjWwuwdUhEwN7O4BFSFTA7sTQgghhBBCCCEk4+HhHx1+oXmIUn1VAAAAAElFTkSuQmCC";
        SigningLogo signingLogoEn = SigningLogoBuilder.newSigningLogo()
                .withLanguage(Locale.ENGLISH)
                .withImage(logoEn)
                .build();
        signingLogos.add(signingLogoEn);
        // Create signing logos
        eslClient.getSigningStyleService().saveSigningLogos(signingLogos);
        createdSigningLogos = eslClient.getSigningStyleService().getSigningLogos();

        String logoFr = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAQIAAAAoCAIAAACabWPjAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAPVSURBVHhe7dy/SxthGAfw06RHGkXB3SFXUjqE/AsFm1qKUNDBXRCuIA7V4ZaMLhmsQyg0ILhniJNDbSr4LwQnA8ngHlBSG0KifX8ld+97P7yogV7z/XDUu9zrcyd9n/d93jtRAwAAAAAAIKbE14H7+3uxB/D/mpqSev60+AowwZAGAEgDAKQBAIE0AEAaACANAAikAQDSAGDC3iKftRfNrth3yiTPjxOG31lNP6rP5viubxtNW5u9KujDBuulhf0lfoLo7qbbZfVDrXF487bQEwealrXmTzZj4kDEcVyaqVqtjcrgWk4+7WX90ur13oU48GssLjEg3ZUQKs6/DW+RR9bdSLerYj+sshn8LSQxWs4cIGqF68XVTkMcPbdmZyXt7LsE+blaK4d9cUTQNlIOEOpdhYkTPZOYBmT0WrhybmQqEKcI5ezsOv2weyD9N7sikE0ZntVvkVQtOjk445xbcfrBxe32WPpTv7RzW6M79hWP1uhxrfC71KQ7AW3IXX07Yzuh4kQRZoMH6B95Vxidb89odg7oiBvPn9q1hLE5k894ViDP4ewPG79J37WvmCvwHtzb+87KPO828/yuRC0XJk4kIQ2CiS6rvTFG6p16no7u3j2j8atLB9SM/j7FP+Bi5vHCWHKATD4/2G2s6UoFn/vAZrDLO1LzNBp3dF9tI91VmDjRNIlpQGvZRXu7kcds+ewyqwEyyS3H0tYVgWxKEO0VG921SntXlBMur6cdlZgf9UJK4T6SbNqVY6l4lvx70aNpUKcLFY82LsFxogmzwQNI6aIsHkKKmdt0jCwXx7bqHSu2XLYz0IpuwRMGlsj1OVMqTsRZsTrUepce9b0SgWxKEGbpJZ0Q7PWlLFQJoV5ocFePUau7Ft/NHpvr4iTJjTRdo3u0cQmOE02YDXzw1SFRNtWCJ7SY+TVJqoWyyZ8LCcY7nZUQ3Z9y2Ko1rkFX1O6VjvyD9EtFdjlWnhkG6wrDNqnEiSvxwsSJJqSBL9GJ6Up357GFTSrxxT1+p158ognW21u23y00Dm9o3V9pj+UB/JLOHvtKV6xa/PF/PP+ZdW4+d8lthk8IhDBxIglpECCVKHo/zncvkVt+b75yBf7mwSlmHovXEcM44lVaJll80sMiv7W7vn/KU9puwFfbWWtmUM553JV4QqBp63weCBUnipAGgYzNOV4VPOH1kL7Fc0mi7w9fmQ1krflHrcXDoUWOKPMG6OpffkRL70pZftC7qjt+ByRUnMjBX6aASYTfKQJQIQ0AkAYASAMAAmkAgDQAQBoAEEgDAAAAAAAAAACgNO0vBzE5dtSK0xIAAAAASUVORK5CYII=";
        SigningLogo signingLogoFr = SigningLogoBuilder.newSigningLogo()
                .withLanguage(Locale.FRENCH)
                .withImage(logoFr)
                .build();
        signingLogos.add(signingLogoFr);
        // Update signing logos
        eslClient.getSigningStyleService().saveSigningLogos(signingLogos);
        updatedSigningLogos = eslClient.getSigningStyleService().getSigningLogos();

        // Delete signing logos
        eslClient.getSigningStyleService().saveSigningLogos(new ArrayList<SigningLogo>());
        removedSigningLogos = eslClient.getSigningStyleService().getSigningLogos();
    }
}