package com.silanis.esl.sdk.internal;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silanis.esl.api.model.Error;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.EslException;
import java.util.ArrayList;
import java.util.List;

public class Serialization {

    private static final Support support = new Support();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object object){

        return JacksonUtil.serializeDirty(object);
    }


    public static <T> T fromJson(String json, Class<T> clazz){
        try{
            return mapper.readValue(json, clazz);
        } catch (Exception e){
            Error errorMessage = JacksonUtil.deserialize(json, Error.class);
            support.logError(errorMessage);
            throw new EslException(json);
        }
    }

    public static <T>List<T> fromJsonToList(String json, Class<T> clazz){

        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz));
        } catch (JsonMappingException me) {
            List<T> obj = new ArrayList<T>();
            obj.add(fromJson(json, clazz));
            return obj;
        } catch (Exception e){
            Error errorMessage = JacksonUtil.deserialize(json, Error.class);
            support.logError(errorMessage);
            throw new EslException(json);
        }

    }
}
