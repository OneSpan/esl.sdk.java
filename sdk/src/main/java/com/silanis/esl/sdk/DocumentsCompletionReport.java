package com.silanis.esl.sdk;

import java.util.Date;

/**
 * Created by lena on 2014-05-29.
 */
public class DocumentsCompletionReport {
    private boolean completed;
    private Date firstSigned;
    private String id;
    private Date lastSigned;
    private String name;

    public DocumentsCompletionReport(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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

    public Date getLastSigned() {
        return lastSigned;
    }

    public void setLastSigned(Date lastSigned) {
        this.lastSigned = lastSigned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
