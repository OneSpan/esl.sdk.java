package com.silanis.awsng.web.rest.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.silanis.awsng.web.rest.JsonDeserializationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sam Habbab
 *
 */
public class JacksonUtil {

    private static final String CLASS = JacksonUtil.class.getName();
    protected static Logger log = Logger.getLogger( CLASS);

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    public static <T> T deserialize(String json, Class<T> cl) {
        try {
            return mapper.readValue(json, cl);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Failed to deserialize json string: " + json, e);
            throw new JsonDeserializationException(cl, e);
        }
    }

    public static <T> List<T> deserializeList(String json, Class<T> cl) {
        List<T> obj;

        try {
            obj = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(ArrayList.class, cl));
        } catch (JsonMappingException me) {
            //  maybe the string representation is an object
            obj = new ArrayList<T>();
            obj.add(deserialize(json, cl));
        } catch (IOException e) {
            log.log(Level.SEVERE, "Failed to deserialize json list: " + json, e);
            throw new JsonDeserializationException(cl, e);
        }

        return obj;
    }

    /**
     * Generic deserializer.
     *
     * Example:
     * Result<Approval> result = JacksonUtil.deserialize(reusltString, new TypeReference<Result<Approval>>() {});
     */
    public static <T> T deserialize(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to deserialize generic object: " + json, e);
            throw new JsonDeserializationException(typeReference, e);
        }
    }
    // ******************************************************************************************************************************************************

    public static String serializeDirty(Object json) {
        return _serialize(json, true);
    }

    public static String serialize(Object json) {
        return _serialize(json, false);
    }

    // ******************************************************************************************************************************************************

    private static String _serialize(Object json, boolean filterDirtyFields) {
        String result = null;

        try {
            ObjectMapper mapperFilter = new ObjectMapper();
            DirtyFieldsFilter filter = new DirtyFieldsFilter(filterDirtyFields);
            FilterProvider fp = new SimpleFilterProvider().addFilter("DirtyFieldsFilter", filter).setDefaultFilter(filter).setFailOnUnknownId(false);

            result = mapperFilter.writer(fp).writeValueAsString(json);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to serialize json object: " + json, e);
            throw new RuntimeException("Invalid arguments.", e);
        }

        return result;
    }

    public static String merge(String base, String delta) throws IOException {

        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        TypeReference<Map<String,Object>> typeRef = new TypeReference<Map<String, Object>>() {};

        if (isEmpty(delta)) {
            return base;
        } else if (isEmpty(base)) {
            return delta;
        } else {
            Map<String,Object> baseMap = mapper.readValue(base, typeRef);
            Map<String,Object> deltaMap = mapper.readValue(delta, typeRef);
            Map<String,Object> resultMap = merge( baseMap, deltaMap );
            return serialize(resultMap);
        }
    }

    private static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static Map<String,Object> merge(Map<String,Object> baseMap, Map<String,Object> deltaMap) {
        if ( baseMap == null ) {
            baseMap = new LinkedHashMap<String,Object>();
        }
        if ( deltaMap == null ) {
            deltaMap = new LinkedHashMap<String, Object>();
        }
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>(baseMap);
        for ( String key : deltaMap.keySet() ) {
            Object value = deltaMap.get(key);
            if ( value == null ) {
                resultMap.put(key,null);
            } else if ( value instanceof Map ) {
                if ( baseMap.containsKey(key) ) {
                    resultMap.put(key, merge( (Map<String,Object>)baseMap.get(key), (Map<String,Object>)value));
                } else {
                    resultMap.put(key, value);
                }
            } else if ( value instanceof List ) {
                if ( baseMap.containsKey(key) ) {
                    // Concat the delta list onto the base list
                    List<Object> resultList = (List<Object>) resultMap.get(key);
                    for ( Object object : (List<Object>)value ) {
                        resultList.add(object);
                    }
                } else {
                    resultMap.put(key, value);
                }
            } else {
                resultMap.put(key, value);
            }

        }
        return resultMap;
    }
}
