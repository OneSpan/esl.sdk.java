package com.silanis.esl.sdk;

public class AccountMember {
    private Address address;
    private String company;
    private String email;
    private String firstName;
    private String lastName;
    private String language;
    private String phoneNumber;
    private String title;

    private SenderStatus status;

    public void setAddress( Address address ) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setCompany( String company ) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLanguage( String language ) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setPhoneNumber( String phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public SenderStatus getStatus() {
        return status;
    }

    public void setStatus(SenderStatus status) {
        this.status = status;
    }
}
