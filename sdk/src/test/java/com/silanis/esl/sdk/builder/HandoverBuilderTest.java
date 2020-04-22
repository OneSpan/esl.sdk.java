package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Handover;
import org.junit.Test;

import java.util.Locale;

import static com.silanis.esl.sdk.builder.HandoverBuilder.newHandover;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by schoi on 2020-04-02.
 */
public class HandoverBuilderTest {

    @Test
    public void test() {
        Locale lang = Locale.FRENCH;
        String href = "href";
        String text = "text";
        String title = "title";
        Handover handover = newHandover(lang)
                .withHref(href)
                .withText(text)
                .withTitle(title)
                .build();

        assertThat(handover.getLanguage(), is(lang));
        assertThat(handover.getHref(), is(href));
        assertThat(handover.getText(), is(text));
        assertThat(handover.getTitle(), is(title));
    }
}