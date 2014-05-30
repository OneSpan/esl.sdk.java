package com.silanis.esl.sdk;

import com.silanis.esl.sdk.SenderStatus;
import com.silanis.esl.sdk.SenderType;

import java.util.Date;

/**
 * Created by lena on 2014-05-29.
 */
public class Sender {
    private SenderStatus status;
    private SenderType type;
    private String company;
    private Date created;
    private String email;
    private String firstName;
    private String lastName;
    private String language;
    private String phone;
    private String signerType;
    private String title;
    private Date updated;
    private String id;
    private String name;

    public SenderStatus getStatus() {
        return status;
    }

    public void setStatus(SenderStatus status) {
        this.status = status;
    }

    public SenderType getType() {
        return type;
    }

    public void setType(SenderType type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSignerType() {
        return signerType;
    }

    public void setSignerType(String signerType) {
        this.signerType = signerType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
