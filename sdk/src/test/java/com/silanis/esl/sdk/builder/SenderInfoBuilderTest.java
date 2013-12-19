package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.SenderInfo;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SenderInfoBuilderTest {
    @Test
    public void typicalUseCase() {
        SenderInfoBuilder builder = SenderInfoBuilder.newSenderInfo()
                .withCompany( "company" )
                .withTitle( "title" )
                .withName( "firstName", "lastName" );

        SenderInfo senderInfo = builder.build();

        assertThat( "builder returned a null object", senderInfo, is( notNullValue() ) );
        assertThat( "company was not set correctly", senderInfo.getCompany(), is( equalTo( "company" ) ) );
        assertThat( "title was not set correctly", senderInfo.getTitle(), is( equalTo( "title" ) ) );
        assertThat( "first name was not set correctly", senderInfo.getFirstName(), is( equalTo( "firstName" ) ) );
        assertThat( "last name was not set correctly", senderInfo.getLastName(), is( equalTo( "lastName" ) ) );
    }
}
