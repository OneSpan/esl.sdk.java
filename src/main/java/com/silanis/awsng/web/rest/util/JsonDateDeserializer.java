package com.silanis.awsng.web.rest.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.core.JsonGenerator;

public class JsonDateDeserializer extends JsonDeserializer<Date> {

    private final static String ISO_8061 = "yyyy-MM-dd'T'HH:mm:ss";

    /** Use ConverterUtil.java **/
    public static String asIso8061( Date date){

        if ( null == date)
            return null;

        SimpleDateFormat sdf = new SimpleDateFormat( ISO_8061);
        sdf.setTimeZone( TimeZone.getTimeZone( "UTC"));

        //  we need to append the Z to indicate to UI this is a UTC value
        return sdf.format( date) + "Z";
    }

    /** Use ConverterUtil.java **/
    public static Date extractIso8061Date( String strValue) throws ParseException {
        SimpleDateFormat dateParser = new SimpleDateFormat( ISO_8061);
        dateParser.setLenient( true);
        if ( strValue.endsWith( "Z")){
            dateParser.setTimeZone( TimeZone.getTimeZone( "UTC"));
        }

        return dateParser.parse( strValue);
    }

    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {
        String date = jsonparser.getText();

        if (date == null) {
            return null;
        }
        try {
            return extractIso8061Date(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
