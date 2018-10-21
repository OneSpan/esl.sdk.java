package com.silanis.esl.sdk;

public class SenderInfo {
    private String firstName;
    private String lastName;
    private String company;
    private String title;
    private String email;
    private String timezoneId;

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

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getTimezoneId() { return timezoneId; }

    public void setTimezoneId(String timezoneId) { this.timezoneId = timezoneId; }
}
