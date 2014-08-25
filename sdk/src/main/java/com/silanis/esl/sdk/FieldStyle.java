package com.silanis.esl.sdk;

/**
 * FieldStyle class is used to provide the style for a field.
 */
public enum FieldStyle {

    BOUND_DATE( "{approval.signed}" ),
    BOUND_NAME( "{signer.name}" ),
    BOUND_TITLE( "{signer.title}" ),
    BOUND_COMPANY( "{signer.company}" ),
    BOUND_QRCODE( null ),
    UNBOUND_TEXT_FIELD( null ),
    UNBOUND_CUSTOM_FIELD( null ),
    UNBOUND_CHECK_BOX( null ),
    UNBOUND_RADIO_BUTTON( null ),
    LABEL( null );

    private final String binding;

    private FieldStyle( String binding ) {
        this.binding = binding;
    }

    public String getBinding() {
        return binding;
    }
}
