package com.silanis.esl.sdk;

public enum SupportedWritingSystem {

        ARABIC("arabic"),
        CHINESE_TRADITIONAL("chinese-traditional"),
        CHINESE_SIMPLIFIED("chinese-simplified"),
        CYRILLIC("cyrillic"),
        GREEK("greek"),
        JAPANESE("japanese"),
        KOREAN("korean"),
        LATIN("latin");

        private final String writingSystem;

        private SupportedWritingSystem(String writingSystem) {
                this.writingSystem = writingSystem;
        }

        public String getName() {
                return writingSystem;
        }
}
