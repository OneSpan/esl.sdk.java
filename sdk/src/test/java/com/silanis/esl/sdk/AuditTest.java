package com.silanis.esl.sdk;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class AuditTest {
    @Test
    public void constructor() {
        String type = "type";
        String dateTime = "dateTime";
        String target = "target";
        String user = "user";
        String email = "email";
        String ip = "ip";
        String data = "data";
        Audit audit = new Audit( type, dateTime, target, user, email, ip, data );
        assertThat( "type was not set correctly", audit.getType(), is( equalTo( type ) ) );
        assertThat( "date/time was not set correctly", audit.getDateTime(), is( equalTo( dateTime ) ) );
        assertThat( "data was not set correctly", audit.getData(), is( equalTo( data ) ) );
        assertThat( "user was not set correctly", audit.getUser(), is( equalTo( user ) ) );
        assertThat( "email was not set correctly", audit.getEmail(), is( equalTo( email ) ) );
        assertThat( "ip was not set correctly", audit.getIp(), is( equalTo( ip ) ) );
    }
}
