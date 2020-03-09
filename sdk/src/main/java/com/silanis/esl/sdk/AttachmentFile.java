package com.silanis.esl.sdk;

import java.io.Serializable;
import java.util.Date;

public class AttachmentFile implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Date insertDate;
    private String name;
    private boolean preview;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPreview() {
        return preview;
    }

    public void setPreview(boolean preview) {
        this.preview = preview;
    }
}
