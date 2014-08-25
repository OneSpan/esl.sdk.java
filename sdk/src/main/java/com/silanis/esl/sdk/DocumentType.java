package com.silanis.esl.sdk;

public enum DocumentType {

    PDF("pdf"), WORD("docx");

    private final String extension;

    DocumentType(String extension) {
        this.extension = extension;
    }

    public String normalizeName(String name) {
        String normalized = name.replace(' ', '_');

        if (!normalized.endsWith("." + extension)) {
            if (normalized.endsWith(".")) {
                normalized = normalized.substring(0, normalized.length() - 1);
            }

            normalized += "." + extension;
        }

        return normalized;
    }
}