package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.VirtualRoom;
import com.silanis.esl.sdk.TransactionRetention;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class VirtualRoomBuilderTest {

    @Test
    public void withSpecifiedValues() {
        Date startDateTime = new Date();
        VirtualRoomBuilder builder = VirtualRoomBuilder.newVirtualRoom()
                .withVideo(true)
                .withVideoRecording(true)
                .withStartDateTime(startDateTime)
                .withHostUid("hostUid");

        VirtualRoom result = builder.build();

        assertThat(result, is(Matchers.notNullValue()));
        assertThat(result.getVideo(), is(true));
        assertThat(result.getVideoRecording(), is(true));
        assertThat(result.getStartDatetime(), is(startDateTime));
        assertThat(result.getHostUid(), is("hostUid"));
    }
}
