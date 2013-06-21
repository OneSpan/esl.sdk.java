package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.RadioButtonOption;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * User: dave
 */
public class RadioButtonOptionBuilderTest {
    public static final double DEFAULT_DOUBLE_TOLERANCE = 0.01f;
    @Test
    public void buildWithSpecifiedValues() {
        double x = 1;
        double y = 2;
        int page = 3;
        double width = 10;
        double height = 20;
        RadioButtonOption option = RadioButtonOptionBuilder.newOptionOnPage( page )
                .atPosition( x, y )
                .withSize( width, height )
                .build();

        assertEquals( "x not correctly set", x, option.getX(), DEFAULT_DOUBLE_TOLERANCE );
        assertEquals( "y not correctly set", y, option.getY(), DEFAULT_DOUBLE_TOLERANCE );
        assertEquals( "page not correctly set", page, option.getPage() );
        assertEquals( "width not correctly set", width, option.getWidth(), DEFAULT_DOUBLE_TOLERANCE );
        assertEquals( "height not correctly set", height, option.getHeight(), DEFAULT_DOUBLE_TOLERANCE );
    }

    @Test
    public void buildWithDefaultValues() {
        RadioButtonOption option = new RadioButtonOptionBuilder()
                .build();

        assertEquals( RadioButtonOptionBuilder.DEFAULT_WIDTH, option.getWidth(), DEFAULT_DOUBLE_TOLERANCE );
        assertEquals( RadioButtonOptionBuilder.DEFAULT_HEIGHT, option.getHeight(), DEFAULT_DOUBLE_TOLERANCE );
    }
}
