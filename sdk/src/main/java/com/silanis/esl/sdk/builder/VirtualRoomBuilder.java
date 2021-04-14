package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.VirtualRoom;

import java.util.Date;

public class VirtualRoomBuilder {

    private boolean video;
    private boolean videoRecording;
    private Date startDatetime;
    private String hostUid;

    private VirtualRoomBuilder() {
    }

    /**
     * Create a new Virtual Room.
     *
     * @return the Virtual Room builder itself
     */
    public static VirtualRoomBuilder newVirtualRoom() {
        return new VirtualRoomBuilder();
    }

    /**
     * Set if video is on
     *
     * @param video
     * @return This
     */
    public VirtualRoomBuilder withVideo(boolean video) {
        this.video = video;
        return this;
    }

    /**
     * Set if videoRecording is on
     *
     * @param videoRecording
     * @return This
     */
    public VirtualRoomBuilder withVideoRecording(boolean videoRecording) {
        this.videoRecording = videoRecording;
        return this;
    }

    /**
     * Set startDatetime of virtual room meeting
     *
     * @param startDatetime
     * @return This
     */

    public VirtualRoomBuilder withStartDateTime(Date startDatetime) {
        this.startDatetime = startDatetime;
        return this;
    }

    /**
     * Set hostUid for meeting
     *
     * @param hostUid
     * @return This
     */

    public VirtualRoomBuilder withHostUid(String hostUid) {
        this.hostUid = hostUid;
        return this;
    }

    /**
     * Builds the actual VirtualRoom with the specified values
     *
     * @return the VirtualRoom object
     */
    public VirtualRoom build() {
        VirtualRoom result = new VirtualRoom();
        result.setVideo(video);
        result.setStartDatetime(startDatetime);
        result.setVideoRecording(videoRecording);
        result.setHostUid(hostUid);
        return result;
    }
}
