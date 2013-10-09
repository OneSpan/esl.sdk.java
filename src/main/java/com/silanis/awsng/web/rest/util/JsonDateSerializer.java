package com.silanis.awsng.web.rest.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.core.JsonGenerator;

public class JsonDateSerializer extends JsonSerializer<Date> {

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

    public void serialize(Date value, JsonGenerator gen,SerializerProvider provider) throws IOException, JsonProcessingException {
        gen.writeString(asIso8061(value));
    }

}
