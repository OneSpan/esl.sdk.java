package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.ClickableArea;
import com.silanis.esl.sdk.ClickableAreaAlignment;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.ClickableAreaBuilder.newClickableArea;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class ClickableAreaBuilderTest {

    @Test
    public void buildWithSizeAndAlignment() {
        double width = 20;
        double height = 10;
        ClickableAreaAlignment alignment = ClickableAreaAlignment.TOP_LEFT;

        ClickableArea clickableArea = newClickableArea()
                .withSize(width, height)
                .withAlignment(alignment)
                .build();

        assertThat(clickableArea.getWidth(), is(width));
        assertThat(clickableArea.getHeight(), is(height));
        assertThat(clickableArea.getAlignment(), is(alignment));
    }

    @Test
    public void buildWithNoValuesSetLeavesEverythingNull() {
        ClickableArea clickableArea = newClickableArea().build();

        assertThat(clickableArea.getWidth(), is(nullValue()));
        assertThat(clickableArea.getHeight(), is(nullValue()));
        assertThat(clickableArea.getAlignment(), is(nullValue()));
    }

    @Test(expected = BuilderException.class)
    public void withSizeRejectsNegativeWidth() {
        newClickableArea().withSize(-1, 10);
    }

    @Test(expected = BuilderException.class)
    public void withSizeRejectsNegativeHeight() {
        newClickableArea().withSize(10, -1);
    }
}
