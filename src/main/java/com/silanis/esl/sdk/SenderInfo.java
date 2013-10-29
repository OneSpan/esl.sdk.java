package com.silanis.esl.sdk;

import com.silanis.esl.api.model.Sender;

public class SenderInfo {
    private String firstName;
    private String lastName;
    private String company;
    private String title;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany( String company ) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public Sender toAPISender() {
        Sender result = new Sender();
        if (firstName != null ) {
            result.setFirstName( firstName );
        }
        if (lastName != null ) {
            result.setLastName( lastName );
        }
        if ( company != null ) {
            result.setCompany( company );
        }
        if ( title != null ) {
            result.setTitle( title );
        }
        return result;
    }
}
