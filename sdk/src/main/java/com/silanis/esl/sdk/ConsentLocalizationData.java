package com.silanis.esl.sdk;

import java.io.Serializable;

/**
 * Represents a response for consent localization in the eSignLive SDK.
 * <p>
 * This class contains the consent ID and metadata describing package, properties, and document information.
 * </p>
 *
 * <p>Example structure:</p>
 * <pre>
 * {
 *   "consentId": "default-consent",
 *   "consentMetadata": {
 *     "packageInfo": {
 *       "uid": "${PackageID}",
 *       "language": "${PackageLanguage}"
 *     },
 *     "properties": {
 *       "accountId": "${AccountId}",
 *       "language": "${PropertyResourceLanguage}"
 *     },
 *     "document": {
 *       "accountId": "${AccountId}",
 *       "language": "${DocumentResourceLanguage}"
 *     }
 *   }
 * }
 * </pre>
 *
 * @author OneSpan
 * @since 1.0
 */
public class ConsentLocalizationData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String consentId;
    private ConsentMetadata consentMetadata;

    public String getConsentId() {
        return consentId;
    }

    public void setConsentId(String consentId) {
        this.consentId = consentId;
    }

    public ConsentMetadata getConsentMetadata() {
        return consentMetadata;
    }

    public void setConsentMetadata(ConsentMetadata consentMetadata) {
        this.consentMetadata = consentMetadata;
    }

    /**
     * Metadata for consent localization.
     */
    public static class ConsentMetadata implements Serializable {

        private static final long serialVersionUID = 1L;

        private PackageInfo packageInfo;
        private ResourceMetadata properties;
        private ResourceMetadata document;

        public PackageInfo getPackageInfo() {
            return packageInfo;
        }

        public void setPackageInfo(PackageInfo packageInfo) {
            this.packageInfo = packageInfo;
        }

        public ResourceMetadata getProperties() {
            return properties;
        }

        public void setProperties(ResourceMetadata properties) {
            this.properties = properties;
        }

        public ResourceMetadata getDocument() {
            return document;
        }

        public void setDocument(ResourceMetadata document) {
            this.document = document;
        }
    }

    /**
     * Package information.
     */
    public static class PackageInfo implements Serializable {
        private static final long serialVersionUID = 1L;

        private String uid;
        private String language;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }
    }

    /**
     * Properties information.
     */
    public static class ResourceMetadata implements Serializable {
        private static final long serialVersionUID = 1L;

        private String accountId;
        private String language;

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }
    }
}
