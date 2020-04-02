package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Handover;
import com.silanis.esl.sdk.service.AccountConfigService;

import static com.silanis.esl.sdk.builder.HandoverBuilder.newHandover;
import static java.util.Locale.ITALIAN;

/**
 * Created by schoi on 2020-04-01.
 */
public class ConfigureHandoverUrlExample extends SDKSample {
    public static final String TEXT = "text";
    public static final String HREF = "href";
    public static final String TITLE = "title";
    public static final String UPDATED_TITLE = "updated title";

    public Handover handoverBeforeCreating, handoverAfterCreating, handoverAfterUpdating, handoverAfterDeleting;

    public static void main(String... args) {
        new ConfigureHandoverUrlExample().run();
    }

    public void execute() {
        AccountConfigService accountConfigService = eslClient.getAccountConfigService();
        handoverBeforeCreating = accountConfigService.getHandoverUrl(ITALIAN);

        handoverAfterCreating = accountConfigService
                .createHandoverUrl(newHandover(ITALIAN)
                        .withHref(HREF)
                        .withText(TEXT)
                        .withTitle(TITLE)
                        .build());

        handoverAfterUpdating = accountConfigService.updateHandoverUrl(newHandover(ITALIAN)
                .withTitle(UPDATED_TITLE)
                .build());

        accountConfigService.deleteHandoverUrl(ITALIAN);
        handoverAfterDeleting = accountConfigService.getHandoverUrl(ITALIAN);
    }
}