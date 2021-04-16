package com.silanis.esl.sdk.examples;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class VirtualRoomExampleTest {
    
    @Test
    public void verifyResult() {
        VirtualRoomExample example = new VirtualRoomExample();
        example.run();

        // Verify if the VirtualRoom was updated correctly.
        assertThat(example.packageVirtualRoomRoomAfterUpdate.getHostUid(), is(example.hostUid));
        assertDate(example.packageVirtualRoomRoomAfterUpdate.getStartDatetime(), example.startDateTime);
        assertThat(example.packageVirtualRoomRoomAfterUpdate.getVideo(), is(true));
        assertThat(example.packageVirtualRoomRoomAfterUpdate.getVideoRecording(), is(true));
    }

    private void assertDate(Date actual, Date expected) {
        if (actual == null) {
            if (expected == null)
                return;
            else
                fail();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        assertThat(sdf.format(actual), is(sdf.format(expected)));
    }
}
