package com.silanis.esl.sdk;

import java.io.Serializable;
import java.util.Date;

public class VirtualRoom implements Serializable {

    private boolean video;
    private boolean videoRecording;
    private Date startDatetime;
    private String hostUid;

    public boolean getVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public boolean getVideoRecording() {
        return videoRecording;
    }

    public void setVideoRecording(boolean videoRecording) {
        this.videoRecording = videoRecording;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getHostUid() {
        return hostUid;
    }

    public void setHostUid(String hostUid) {
        this.hostUid = hostUid;
    }

}
