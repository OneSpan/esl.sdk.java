package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.GroupSummaryBuilder;

/**
 * Created by schoi on 1/28/15.
 */
public class GroupSummaryConverter {

    private com.silanis.esl.api.model.GroupSummary apiGroupSummary;
    private com.silanis.esl.sdk.GroupSummary sdkGroupSummary;

    /**
     * Construct with API groupSummary object involved in conversion.
     *
     * @param apiGroupSummary
     */
    public GroupSummaryConverter(com.silanis.esl.api.model.GroupSummary apiGroupSummary) {
        this.apiGroupSummary = apiGroupSummary;
    }

    /**
     * Construct with SDK groupSummary object involved in conversion.
     *
     * @param sdkGroupSummary
     */
    public GroupSummaryConverter(com.silanis.esl.sdk.GroupSummary sdkGroupSummary) {
        this.sdkGroupSummary = sdkGroupSummary;
    }

    /**
     * Convert from SDK GroupSummary to API GroupSummary.
     *
     * @return API GroupSummary.
     */
    public com.silanis.esl.api.model.GroupSummary toAPIGroupSummary() {
        if (sdkGroupSummary == null) {
            return apiGroupSummary;
        }

        com.silanis.esl.api.model.GroupSummary result = new com.silanis.esl.api.model.GroupSummary();
        result.setData(sdkGroupSummary.getData());
        result.setEmail(sdkGroupSummary.getEmail());
        result.setId(sdkGroupSummary.getId());
        result.setName(sdkGroupSummary.getName());
        return result;

    }

    /**
     * Convert from API to SDK.
     *
     * @return SDK GroupSummary.
     */
    public com.silanis.esl.sdk.GroupSummary toSDKGroupSummary() {

        if (apiGroupSummary == null) {
            return sdkGroupSummary;
        }

        return GroupSummaryBuilder.newGroupSummary(apiGroupSummary.getEmail())
                                 .withId( apiGroupSummary.getId() )
                                 .withName(apiGroupSummary.getName() )
                                 .withData(apiGroupSummary.getData() )
                                 .build();
    }
}
