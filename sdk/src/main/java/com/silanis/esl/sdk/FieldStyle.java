package com.silanis.esl.sdk;

public class FieldStyle {
    public static FieldStyle BINDING_DATE = new FieldStyle("BINDING_DATE", "", "{approval.signed}");
    public static FieldStyle BINDING_NAME = new FieldStyle("BINDING_NAME", "", "{signer.name}");
    public static FieldStyle BINDING_TITLE = new FieldStyle("BINDING_TITLE", "", "{signer.title}");
    public static FieldStyle BINDING_COMPANY = new FieldStyle("BINDING_COMPANY", "", "{signer.company}");
    public static FieldStyle QRCODE = new FieldStyle("QRCODE");
    public static FieldStyle TEXTFIELD = new FieldStyle("TEXTFIELD");
    public static FieldStyle CUSTOMFIELD = new FieldStyle("CUSTOMFIELD");
    public static FieldStyle CHECKBOX = new FieldStyle("CHECKBOX");
    public static FieldStyle RADIO = new FieldStyle("RADIO");
    public static FieldStyle LABEL = new FieldStyle("LABEL");
    public static FieldStyle LIST = new FieldStyle("LIST");
    public static FieldStyle TEXTAREA = new FieldStyle("TEXTAREA");
    public static FieldStyle SEAL = new FieldStyle("SEAL");
    public static FieldStyle UNRECOGNIZED(String unknownValue){
        return new FieldStyle("UNRECOGNIZED", unknownValue);
    }
    private final String value;
    private final String unknownValue;
    private final String binding;

    private FieldStyle(String value) {
        this.value = value;
        this.unknownValue = "";
        this.binding = null;
    }

    private FieldStyle(String value, String unknownValue) {
        this.value = value;
        this.unknownValue = unknownValue;
        this.binding = null;
    }

    private FieldStyle(String value, String unknownValue, String binding) {
        this.value = value;
        this.unknownValue = unknownValue;
        this.binding = binding;
    }

    public String getBinding() {
        return binding;
    }

    public String getUnknownValue() {
        return unknownValue;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return value;
    }
}