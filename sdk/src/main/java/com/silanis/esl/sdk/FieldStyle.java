package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

public class FieldStyle extends EslEnumeration {

    public static final FieldStyle BOUND_DATE = new FieldStyle("LABEL", "BOUND_DATE", "{approval.signed}");
    public static final FieldStyle BOUND_NAME = new FieldStyle("LABEL", "BOUND_NAME", "{signer.name}");
    public static final FieldStyle BOUND_TITLE = new FieldStyle("LABEL", "BOUND_TITLE", "{signer.title}");
    public static final FieldStyle BOUND_COMPANY = new FieldStyle("LABEL", "BOUND_COMPANY", "{signer.company}");
    public static final FieldStyle BOUND_QRCODE = new FieldStyle("QRCODE", "BOUND_QRCODE");
    public static final FieldStyle UNBOUND_TEXT_FIELD = new FieldStyle("TEXTFIELD", "UNBOUND_TEXT_FIELD");
    public static final FieldStyle UNBOUND_CUSTOM_FIELD = new FieldStyle("CUSTOMFIELD", "UNBOUND_CUSTOM_FIELD");
    public static final FieldStyle UNBOUND_CHECK_BOX = new FieldStyle("CHECKBOX", "UNBOUND_CHECK_BOX");
    public static final FieldStyle UNBOUND_RADIO_BUTTON = new FieldStyle("RADIO", "UNBOUND_RADIO_BUTTON");
    public static final FieldStyle LABEL = new FieldStyle("LABEL", "LABEL");
    public static final FieldStyle DROP_LIST = new FieldStyle("LIST", "DROP_LIST");
    public static final FieldStyle TEXT_AREA = new FieldStyle("TEXTAREA", "TEXT_AREA");
    public static final FieldStyle SEAL = new FieldStyle("SEAL", "SEAL");
    public static final FieldStyle UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API FieldSubtype(%s). The upgrade is required.", unknownValue));
        return new FieldStyle(unknownValue, unknownValue);
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

    private FieldStyle(String apiValue, String sdkValue) {
        this(apiValue, sdkValue, null);
    }

    private FieldStyle(String apiValue, String sdkValue, String binding) {
        super(apiValue, sdkValue);
        this.binding = binding;
    }

    public String getBinding() {
        return binding;
    }

    public static FieldStyle[] values() {
        return apiValues.values().toArray(new FieldStyle[apiValues.size()]);
    }

    public static FieldStyle valueOf(String name) {
        return apiValues.get(name);
    }

}