package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.RadioButtonOption;
import com.silanis.esl.sdk.RadioButtonSet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * User: dave
 */
public class RadioButtonSetBuilderTest {
    @Test
    public void buildWithSpecifiedValues() {
        String email = "bob@aol.com";
        String group = "group";
        RadioButtonOption option1 = mock( RadioButtonOption.class );
        RadioButtonOption option2 = mock( RadioButtonOption.class );
        RadioButtonOption option3 = mock( RadioButtonOption.class );

        RadioButtonSetBuilder builder = RadioButtonSetBuilder.newRadioButtonsForSigner( email )
                .withName( group )
                .withOption( option1 )
                .withOption( option2 )
                .withOption( option3 );

        RadioButtonSet buttonSet = builder.build();

        assertEquals( email, buttonSet.getEmail() );
        assertEquals( group, buttonSet.getGroup() );
        assertEquals( 3, buttonSet.getOptions().size() );
        assertTrue( buttonSet.getOptions().contains( option1 ) );
        assertTrue( buttonSet.getOptions().contains( option2 ) );
        assertTrue( buttonSet.getOptions().contains( option3 ) );
    }

    @Test
    public void buildWithDefaultValues() {
        RadioButtonSetBuilder builder = new RadioButtonSetBuilder();
        RadioButtonSet buttonSet = builder.build();

        assertTrue( buttonSet.getOptions().isEmpty() );
    }
}
