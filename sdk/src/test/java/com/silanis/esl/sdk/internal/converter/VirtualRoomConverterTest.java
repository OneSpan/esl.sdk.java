package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.VirtualRoom;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class VirtualRoomConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.VirtualRoom sdkVirtualRoom1 = null;
    private com.silanis.esl.sdk.VirtualRoom sdkVirtualRoom2 = null;
    private com.silanis.esl.api.model.VirtualRoom apiVirtualRoom1 = null;
    private com.silanis.esl.api.model.VirtualRoom apiVirtualRoom2 = null;
    private VirtualRoomConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkVirtualRoom1 = null;
        converter = new VirtualRoomConverter(sdkVirtualRoom1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toAPIVirtualRoom(), is(nullValue()));

    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiVirtualRoom1 = null;
        converter = new VirtualRoomConverter(apiVirtualRoom1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toSDKVirtualRoom(), is(nullValue()));

    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkVirtualRoom1 = null;
        converter = new VirtualRoomConverter(sdkVirtualRoom1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKVirtualRoom(), is(nullValue()));

    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiVirtualRoom1 = null;
        converter = new VirtualRoomConverter(apiVirtualRoom1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIVirtualRoom(), is(nullValue()));

    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkVirtualRoom1 = new VirtualRoom();
        sdkVirtualRoom2 = new VirtualRoomConverter(sdkVirtualRoom1).toSDKVirtualRoom();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkVirtualRoom2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkVirtualRoom2, is(equalTo(sdkVirtualRoom1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiVirtualRoom1 = new com.silanis.esl.api.model.VirtualRoom();
        apiVirtualRoom2 = new VirtualRoomConverter(apiVirtualRoom1).toAPIVirtualRoom();

        assertThat("Converter returned a null api object for a non null api object", apiVirtualRoom2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiVirtualRoom2, is(equalTo(apiVirtualRoom1)));

    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiVirtualRoom1 = buildApiVirtualRoom();
        sdkVirtualRoom1 = new VirtualRoomConverter(apiVirtualRoom1).toSDKVirtualRoom();

        assertThat("Converter returned a null api object for a non null sdk object", sdkVirtualRoom1, is(notNullValue()));
        assertThat("Video Value was not correctly set", sdkVirtualRoom1.getVideo(), is(apiVirtualRoom1.getVideo()));
        assertThat("VideoRecording Value was not correctly set", sdkVirtualRoom1.getVideoRecording(), is(apiVirtualRoom1.getVideoRecording()));
        assertThat("HostUid Value was not correctly set", sdkVirtualRoom1.getHostUid(), is(apiVirtualRoom1.getHostUid()));
        assertThat("StartDatetime Value was not correctly set", sdkVirtualRoom1.getStartDatetime(), is(apiVirtualRoom1.getStartDatetime()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkVirtualRoom1 = buildSdkVirtualRoom();
        apiVirtualRoom1 = new VirtualRoomConverter(sdkVirtualRoom1).toAPIVirtualRoom();

        assertThat("Converter returned a null api object for a non null sdk object", apiVirtualRoom1, is(notNullValue()));
        assertThat("Video Value was not correctly set", apiVirtualRoom1.getVideo(), is(sdkVirtualRoom1.getVideo()));
        assertThat("VideoRecording Value was not correctly set", apiVirtualRoom1.getVideoRecording(), is(sdkVirtualRoom1.getVideoRecording()));
        assertThat("HostUid Value was not correctly set", apiVirtualRoom1.getHostUid(), is(sdkVirtualRoom1.getHostUid()));
        assertThat("StartDatetime Value was not correctly set", apiVirtualRoom1.getStartDatetime(), is(sdkVirtualRoom1.getStartDatetime()));
    }

    private VirtualRoom buildSdkVirtualRoom() {
        VirtualRoom result = new VirtualRoom();
        result.setVideo(true);
        result.setVideoRecording(true);
        result.setHostUid("hostUid");
        result.setStartDatetime(new Date());
        return result;
    }


    private com.silanis.esl.api.model.VirtualRoom buildApiVirtualRoom() {
        com.silanis.esl.api.model.VirtualRoom result = new com.silanis.esl.api.model.VirtualRoom();
        result.setVideo(true);
        result.setVideoRecording(true);
        result.setHostUid("hostUid");
        result.setStartDatetime(new Date());
        return result;
    }
}