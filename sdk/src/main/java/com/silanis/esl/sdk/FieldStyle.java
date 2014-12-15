package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

public class FieldStyle extends EslEnumeration {

    public static final FieldStyle BOUND_DATE = new FieldStyle("LABEL", "{approval.signed}");
    public static final FieldStyle BOUND_NAME = new FieldStyle("LABEL", "{signer.name}");
    public static final FieldStyle BOUND_TITLE = new FieldStyle("LABEL", "{signer.title}");
    public static final FieldStyle BOUND_COMPANY = new FieldStyle("LABEL", "{signer.company}");
    public static final FieldStyle BOUND_QRCODE = new FieldStyle("QRCODE");
    public static final FieldStyle UNBOUND_TEXT_FIELD = new FieldStyle("TEXTFIELD");
    public static final FieldStyle UNBOUND_CUSTOM_FIELD = new FieldStyle("CUSTOMFIELD");
    public static final FieldStyle UNBOUND_CHECK_BOX = new FieldStyle("CHECKBOX");
    public static final FieldStyle UNBOUND_RADIO_BUTTON = new FieldStyle("RADIO");
    public static final FieldStyle LABEL = new FieldStyle("LABEL");
    public static final FieldStyle DROP_LIST = new FieldStyle("LIST");
    public static final FieldStyle TEXT_AREA = new FieldStyle("TEXTAREA");
    public static final FieldStyle SEAL = new FieldStyle("SEAL");
    public static final FieldStyle UNRECOGNIZED(String unknownValue){
        log.warning("Unknown API FieldSubtype. The upgrade is required.");
        return new FieldStyle(unknownValue, "");
    }

    private static Map<String, FieldStyle> apiValues;
    static {
        apiValues = new HashMap<String, FieldStyle>();
        apiValues.put("QRCODE", BOUND_QRCODE);
        apiValues.put("TEXTFIELD", UNBOUND_TEXT_FIELD);
        apiValues.put("CUSTOMFIELD", UNBOUND_CUSTOM_FIELD);
        apiValues.put("CHECKBOX", UNBOUND_CHECK_BOX);
        apiValues.put("RADIO", UNBOUND_RADIO_BUTTON);
        apiValues.put("LABEL", LABEL);
        apiValues.put("LIST", DROP_LIST);
        apiValues.put("TEXTAREA", TEXT_AREA);
        apiValues.put("SEAL", SEAL);
    }

    private final String binding;

    private FieldStyle(String apiValue) {
        super(apiValue);
        this.binding = null;
    }

    private FieldStyle(String apiValue, String binding) {
        super(apiValue);
        this.binding = binding;
    }

    public String getBinding() {
        return binding;
    }

    public static Map<String, FieldStyle> values() {
        return apiValues;
    }

}