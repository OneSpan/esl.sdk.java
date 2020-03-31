package com.silanis.esl.sdk.internal;

import com.silanis.esl.sdk.EslException;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssertsTest {

    @Test
    public void notNullOk() {
        Asserts.notNull("abc", "abc");
    }

    @Test(expected = EslException.class)
    public void notNullFail() {
        Asserts.notNull(null, "abc");
    }
}