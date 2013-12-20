package com.silanis.esl.sdk;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class AccountIdTest {
    @Test
    public void constructorWithString() {
        String string = "myIdString";
        AccountId id = new AccountId( string );
        assertThat( "id string was not set correctly", id.getId(), is( equalTo( string ) ) );
    }
}
