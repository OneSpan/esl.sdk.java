package com.silanis.esl.sdk.internal.converter;

public class VirtualRoomConverter {

    private com.silanis.esl.sdk.VirtualRoom sdkVirtualRoom = null;
    private com.silanis.esl.api.model.VirtualRoom apiVirtualRoom = null;

    /**
     * Construct with API VirtualRoom object involved in conversion.
     *
     * @param apiVirtualRoom
     */
    public VirtualRoomConverter(com.silanis.esl.api.model.VirtualRoom apiVirtualRoom) {
        this.apiVirtualRoom = apiVirtualRoom;
    }

    /**
     * Construct with SDK VirtualRoom object involved in conversion.
     *
     * @param sdkVirtualRoom
     */
    public VirtualRoomConverter(com.silanis.esl.sdk.VirtualRoom sdkVirtualRoom) {
        this.sdkVirtualRoom = sdkVirtualRoom;
    }

    public com.silanis.esl.sdk.VirtualRoom toSDKVirtualRoom() {
        if (apiVirtualRoom == null) {
            return sdkVirtualRoom;
        }
        com.silanis.esl.sdk.VirtualRoom sdkVirtualRoom = new com.silanis.esl.sdk.VirtualRoom();
        sdkVirtualRoom.setVideo(apiVirtualRoom.getVideo());
        sdkVirtualRoom.setHostUid(apiVirtualRoom.getHostUid());
        sdkVirtualRoom.setStartDatetime(apiVirtualRoom.getStartDatetime());
        sdkVirtualRoom.setVideoRecording(apiVirtualRoom.getVideoRecording());
        return sdkVirtualRoom;
    }

    public com.silanis.esl.api.model.VirtualRoom toAPIVirtualRoom() {
        if (sdkVirtualRoom == null) {
            return apiVirtualRoom;
        }
        com.silanis.esl.api.model.VirtualRoom apiVirtualRoom = new com.silanis.esl.api.model.VirtualRoom();
        apiVirtualRoom.setVideo(sdkVirtualRoom.getVideo());
        apiVirtualRoom.setHostUid(sdkVirtualRoom.getHostUid());
        apiVirtualRoom.setStartDatetime(sdkVirtualRoom.getStartDatetime());
        apiVirtualRoom.setVideoRecording(sdkVirtualRoom.getVideoRecording());
        return apiVirtualRoom;
    }
}
