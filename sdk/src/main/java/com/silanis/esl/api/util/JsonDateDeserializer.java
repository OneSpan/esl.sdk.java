package com.silanis.esl.api.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.DeserializationContext;

public class JsonDateDeserializer extends JsonDeserializer<Date> {

    private final static String ISO_8061 = "yyyy-MM-dd'T'HH:mm:ss";
    private final static Pattern OFFSET_SUFFIX = Pattern.compile("[+-]\\d{2}:\\d{2}$");

    /** Use ConverterUtil.java **/
    public static String asIso8061( Date date){

        if ( null == date)
            return null;

        SimpleDateFormat sdf = new SimpleDateFormat( ISO_8061);
        sdf.setTimeZone( TimeZone.getTimeZone( "UTC"));

        //  we need to append the Z to indicate to UI this is a UTC value
        return sdf.format( date) + "Z";
    }

    /**
     * Returns the date with its UTC offset when the value carries an explicit offset (e.g. "+03:30"),
     * or null when it is in UTC ("Z") or has no zone at all.
     */
    public static OffsetDateTime extractIso8061OffsetDateTime(String strValue) {
        if (strValue == null || !OFFSET_SUFFIX.matcher(strValue).find()){
            return null;
        }
        try {
            return OffsetDateTime.parse(strValue, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        } catch (DateTimeParseException e){
            return null;
        }
    }

    /** Use ConverterUtil.java **/
    public static Date extractIso8061Date(String strValue) throws ParseException {
        OffsetDateTime withOffset = extractIso8061OffsetDateTime(strValue);
        if (withOffset != null){
            return Date.from(withOffset.toInstant());
        }

        SimpleDateFormat dateParser = new SimpleDateFormat(ISO_8061);
        dateParser.setLenient(true);
        if ( strValue.endsWith( "Z")){
            dateParser.setTimeZone( TimeZone.getTimeZone( "UTC"));
        }

        return dateParser.parse( strValue);
    }

    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException {
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
