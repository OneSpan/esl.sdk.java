package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.VirtualRoom;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.internal.converter.VirtualRoomConverter;

public class VirtualRoomService extends EslComponent {

    public VirtualRoomService(RestClient client, String baseUrl) {
        super(client, baseUrl);
    }

    /**
     * Gets the virtual room configuration for package.
     *
     * @param packageId
     * @return VirtualRoom
     */
    public com.silanis.esl.sdk.VirtualRoom getVirtualRoom(PackageId packageId) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.VIRTUAL_ROOM_CONFIG_PATH)
                .replace("{packageId}", packageId.getId())
                .build();
        String stringResponse;
        try {
            stringResponse = getClient().get(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not get virtual room configuration.", e);
        } catch (Exception e) {
            throw new EslException("Could not get virtual room configuration.", e);
        }

        VirtualRoom virtualRoom =  Serialization.fromJson(stringResponse, VirtualRoom.class);
        VirtualRoomConverter converter = new VirtualRoomConverter(virtualRoom);
        return converter.toSDKVirtualRoom();
    }

    /**
     * Update virtual room configuration for package.
     *
     * @param packageId
     * @param virtualRoom
     */
    public void setVirtualRoom(PackageId packageId, com.silanis.esl.sdk.VirtualRoom virtualRoom) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.VIRTUAL_ROOM_CONFIG_PATH)
                .replace("{packageId}", packageId.getId())
                .build();
        VirtualRoomConverter converter = new VirtualRoomConverter(virtualRoom);
        String virtualRoomJson = Serialization.toJson(converter.toAPIVirtualRoom());

        try {
            getClient().put(path, virtualRoomJson);
        } catch (RequestException e) {
            throw new EslServerException("Could not update virtualRoom", e);
        } catch (Exception e) {
            throw new EslException("Could not update virtualRoom", e);
        }
    }
}
