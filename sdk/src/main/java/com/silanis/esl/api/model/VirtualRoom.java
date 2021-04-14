package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.esl.api.util.JsonDateSerializer;

import java.util.Date;

import static com.silanis.esl.api.util.SchemaSanitizer.throwOnNull;
import static com.silanis.esl.api.util.SchemaSanitizer.trim;

@JsonIgnoreProperties(ignoreUnknown=true)
public class VirtualRoom extends Model {

    @JsonIgnore
    private static final String FIELD_VIDEO = "video";
    @JsonIgnore
    private static final String FIELD_VIDEO_RECORDING = "videoRecording";
    @JsonIgnore
    private static final String FIELD_START_DATETIME = "startDatetime";
    @JsonIgnore
    private static final String FIELD_HOST_UID = "hostUid";

    private boolean video;
    private boolean videoRecording;
    private Date startDatetime;
    private String hostUid;

    public boolean getVideo() {
        return video;
    }

    public VirtualRoom setVideo(boolean value) {
        this.video = value;
        setDirty(FIELD_VIDEO);
        return this;
    }

    public boolean getVideoRecording() {
        return videoRecording;
    }

    public VirtualRoom setVideoRecording(boolean value) {
        this.videoRecording = value;
        setDirty(FIELD_VIDEO_RECORDING);
        return this;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getStartDatetime() {
        return startDatetime;
    }

    public VirtualRoom setStartDatetime(Date value) {
        this.startDatetime = value;
        setDirty(FIELD_START_DATETIME);
        return this;
    }

    public String getHostUid() {
        return hostUid;
    }

    public VirtualRoom setHostUid(String hostUid) {
        this.hostUid = hostUid;
        setDirty(FIELD_HOST_UID);
        return this;
    }
}
