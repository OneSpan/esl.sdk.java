package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by schoi on 9/11/14.
 */
public class KnowledgeBasedAuthenticationStatusConverter {

    private String apiKnowledgeBasedAuthenticationStatus = null;
    private KnowledgeBasedAuthenticationStatus sdkKnowledgeBasedAuthenticationStatus = null;

    /**
     * Construct with API KnowledgeBasedAuthenticationStatus object involved in conversion.
     *
     * @param apiKnowledgeBasedAuthenticationStatus
     */
    public KnowledgeBasedAuthenticationStatusConverter(String apiKnowledgeBasedAuthenticationStatus) {
        this.apiKnowledgeBasedAuthenticationStatus = apiKnowledgeBasedAuthenticationStatus;
    }

    /**
     * Construct with SDK KnowledgeBasedAuthenticationStatus object involved in conversion.
     *
     * @param sdkKnowledgeBasedAuthenticationStatus
     */
    public KnowledgeBasedAuthenticationStatusConverter(KnowledgeBasedAuthenticationStatus sdkKnowledgeBasedAuthenticationStatus) {
        this.sdkKnowledgeBasedAuthenticationStatus = sdkKnowledgeBasedAuthenticationStatus;
    }

    /**
     * Convert from API KnowledgeBasedAuthenticationStatus to SDK KnowledgeBasedAuthenticationStatus.
     *
     * @return the SDK KnowledgeBasedAuthenticationStatus
     */
    public KnowledgeBasedAuthenticationStatus toSDKKnowledgeBasedAuthenticationStatus() {
        if (apiKnowledgeBasedAuthenticationStatus == null) {
            return sdkKnowledgeBasedAuthenticationStatus;
        }

        try {
            return Iterables.find(Arrays.asList(KnowledgeBasedAuthenticationStatus.values()), new Predicate<KnowledgeBasedAuthenticationStatus>() {
                public boolean apply(KnowledgeBasedAuthenticationStatus knowledgeBasedAuthenticationStatus) {
                    return apiKnowledgeBasedAuthenticationStatus.equals(knowledgeBasedAuthenticationStatus.getApiValue());
                }
            });
        } catch (NoSuchElementException e) {
            return KnowledgeBasedAuthenticationStatus.UNRECOGNIZED(apiKnowledgeBasedAuthenticationStatus);
        }
    }

    /**
     * Convert from SDK KnowledgeBasedAuthenticationStatus to API KnowledgeBasedAuthenticationStatus.
     *
     * @return the API KnowledgeBasedAuthenticationStatus
     */
    public String toAPIKnowledgeBasedAuthenticationStatus() {
        if (sdkKnowledgeBasedAuthenticationStatus == null) {
            return apiKnowledgeBasedAuthenticationStatus;
        }
        return sdkKnowledgeBasedAuthenticationStatus.getApiValue();
    }
}
