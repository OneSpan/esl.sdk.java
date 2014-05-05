package com.silanis.esl.sdk.examples;

import org.junit.Test;

/**
 * Created by dave on 05/05/14.
 */
public class GroupManagementExampleTest {
    @Test
    public void verifyResult() {
        GroupManagementExample example = new GroupManagementExample(Props.get());
        example.run();
    }

}
