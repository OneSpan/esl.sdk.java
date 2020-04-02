package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Handover;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.HandoverBuilder.newHandover;
import static com.silanis.esl.sdk.examples.ConfigureHandoverUrlExample.*;
import static java.util.Locale.ITALIAN;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 2020-04-01.
 */
public class ConfigureHandoverUrlExampleTest {

    @Test
    public void verifyResult() {
        ConfigureHandoverUrlExample example = new ConfigureHandoverUrlExample();
        example.run();

        assertHandover(example.handoverBeforeCreating, newHandover(ITALIAN)
                .withHref(EMPTY)
                .withTitle(EMPTY)
                .withText(EMPTY)
                .build());
        assertHandover(example.handoverAfterCreating, newHandover(ITALIAN)
                .withHref(HREF)
                .withTitle(TITLE)
                .withText(TEXT)
                .build());
        assertHandover(example.handoverAfterUpdating, newHandover(ITALIAN)
                .withHref(HREF)
                .withTitle(UPDATED_TITLE)
                .withText(TEXT)
                .build());
        assertHandover(example.handoverAfterDeleting, newHandover(ITALIAN)
                .withHref(EMPTY)
                .withTitle(EMPTY)
                .withText(EMPTY)
                .build());
    }

    private void assertHandover(Handover actual, Handover expected) {
        assertThat("The handover href is not set correctly.", actual.getHref(), is(expected.getHref()));
        assertThat("The handover text is not set correctly.", actual.getText(), is(expected.getText()));
        assertThat("The handover title is not set correctly.", actual.getTitle(), is(expected.getTitle()));
    }
}