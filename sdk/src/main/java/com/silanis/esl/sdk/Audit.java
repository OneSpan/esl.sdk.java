package com.silanis.esl.sdk;

import java.io.Serializable;

public class Audit implements Serializable {

    private static final long serialVersionUID = 1L;

    private String type;
    private String dateTime;
    private String target;
    private String user;
    private String email;
    private String ip;
    private String data;

    public Audit(String type, String dateTime, String target, String user, String email, String ip, String data) {
        this.type = type;
        this.dateTime = dateTime;
        this.target = target;
        this.user = user;
        this.email = email;
        this.ip = ip;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getTarget() {
        return target;
    }

    public String getUser() {
        return user;
    }

    public String getEmail() {
        return email;
    }

    public String getIp() {
        return ip;
    }

    public String getData() {
        return data;
    }

}
