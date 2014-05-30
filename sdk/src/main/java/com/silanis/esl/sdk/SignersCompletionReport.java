package com.silanis.esl.sdk;

import java.util.Date;

/**
 * Created by lena on 2014-05-29.
 */
public class SignersCompletionReport {
    private boolean completed;
    private String email;
    private String firstName;
    private Date firstSigned;
    private String id;
    private String lastName;
    private Date lastSigned;

    public SignersCompletionReport(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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

    public Date getFirstSigned() {
        return firstSigned;
    }

    public void setFirstSigned(Date firstSigned) {
        this.firstSigned = firstSigned;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getLastSigned() {
        return lastSigned;
    }

    public void setLastSigned(Date lastSigned) {
        this.lastSigned = lastSigned;
    }
}
