package com.silanis.esl.sdk;

import com.silanis.esl.api.model.Entity;

/**
 * Created by chi-wing on 5/28/14.
 */
public class ServerError {
    private int code;
    private Entity entity;
    private String message;
    private String messageKey;
    private String name;
    private String technical;

    public ServerError(){}

    public ServerError(int code, Entity entity, String message, String messageKey, String name, String technical) {
        this.code = code;
        this.entity = entity;
        this.message = message;
        this.messageKey = messageKey;
        this.name = name;
        this.technical = technical;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechnical() {
        return technical;
    }

    public void setTechnical(String technical) {
        this.technical = technical;
    }
}
