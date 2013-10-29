package com.silanis.esl.sdk;

import com.silanis.esl.api.model.Sender;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SenderInfoTest {
    private SenderInfo senderInfo;

    @Before
    public void before() {
        senderInfo = new SenderInfo();
    }

    @Test
    public void testFirstName() {
        senderInfo.setFirstName( "firstName" );
        assertThat( "first name was not properly set or retrieved", senderInfo.getFirstName(), is( equalTo( "firstName" ) ) );
    }

    @Test
    public void testLastName() {
        senderInfo.setLastName( "lastName" );
        assertThat( "last name was not properly set or retrieved", senderInfo.getLastName(), is( equalTo( "lastName" ) ) );
    }

    @Test
    public void testCompany() {
        senderInfo.setCompany( "company" );
        assertThat( "company was not properly set or retrieved", senderInfo.getCompany(), is( equalTo( "company" ) ) );
    }

    @Test
    public void testTitle() {
        senderInfo.setTitle( "title" );
        assertThat( "title was not properly set or retrieved", senderInfo.getTitle(), is( equalTo( "title" ) ) );
    }

    @Test
    public void testToAPI() {
        senderInfo.setTitle( "title" );
        senderInfo.setCompany( "company" );
        senderInfo.setFirstName( "firstName" );
        senderInfo.setLastName( "lastName" );

        Sender sender = senderInfo.toAPISender();

        assertThat( "first name was not properly set or retrieved", sender.getFirstName(), is( equalTo( "firstName" ) ) );
        assertThat( "last name was not properly set or retrieved", sender.getLastName(), is( equalTo( "lastName" ) ) );
        assertThat( "company was not properly set or retrieved", sender.getCompany(), is( equalTo( "company" ) ) );
        assertThat( "title was not properly set or retrieved", sender.getTitle(), is( equalTo( "title" ) ) );
    }
}
