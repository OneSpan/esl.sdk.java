package com.silanis.esl.sdk.internal.converter.sdk;

import com.silanis.esl.api.model.*;
import com.silanis.esl.sdk.Signature;
import com.silanis.esl.sdk.internal.ConversionException;
import com.silanis.esl.sdk.internal.converter.ConversionService;
import com.silanis.esl.sdk.internal.converter.TextAnchorConverter;

public class SignatureConverter {

    private Field getESLField(Signature signature) {
        Field result = new Field();

        result.setPage( signature.getPage() );
        result.setExtract(signature.isExtraction());
        if ( signature.getName() != null ) {
            result.setName(signature.getName());
        }

        if (!signature.isExtraction()) {
            result.setTop( signature.getY() );
            result.setLeft( signature.getX() );
            result.setWidth( signature.getWidth() );
            result.setHeight( signature.getHeight() );
        }

        if (signature.getTextAnchor() != null ) {
            result.setExtractAnchor( new TextAnchorConverter(signature.getTextAnchor()).toAPIExtractAnchor() );
        }

        result.setType( FieldType.SIGNATURE );
        result.setSubtype( getSignatureSubtype(signature) );

        return result;
    }

    private FieldSubtype getSignatureSubtype(Signature signature) {
        switch (signature.getStyle()) {
            case FULL_NAME:
                return FieldSubtype.FULLNAME;
            case HAND_DRAWN:
                return FieldSubtype.CAPTURE;
            case INITIALS:
                return FieldSubtype.INITIALS;
            case ACCEPTANCE:
                return FieldSubtype.FULLNAME;
            default:
                throw new ConversionException( Signature.class, Approval.class, "Unable to decode signature type." );
        }
    }

    public Approval convertToApproval(Signature signature) {
        Approval result = new Approval();

        result.addField(getESLField(signature));

        for ( com.silanis.esl.sdk.Field field : signature.getFields() ) {
            result.addField( ConversionService.convert( field ) );
        }

        return result;
    }
}