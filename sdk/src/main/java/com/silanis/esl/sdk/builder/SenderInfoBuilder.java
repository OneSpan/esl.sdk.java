package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.SenderInfo;

/**
 * <p>The SenderInfoBuilder class is a convenient class used to create and customize a sender.</p>
 */
public class SenderInfoBuilder {
    private String email;
    private String firstName = null;
    private String lastName = null;
    private String company = null;
    private String title = null;
    private String timezoneId = null;

    /**
     * Defines a new sender and uniquely identify him using his email address.
     * @param email the sender's email address uniquely identifying him. @size(min="6", max="255", valid email address)
     * @return This
     */
    public static SenderInfoBuilder newSenderInfo(String email) {
        return new SenderInfoBuilder(email);
    }

    private SenderInfoBuilder(String email) {
        this.email = email;
    }

    /**
     * Set the Sender's first name and last name. 
     *
     * @param firstName sender's first name @size(min="1", max="255")
     * @param lastName sender's last name @size(min="1", max="255")
     * @return This
     */
    public SenderInfoBuilder withName( String firstName, String lastName ) {
        this.firstName = firstName;
        this.lastName = lastName;
        return this;
    }

    /**
     * Set the Sender's company.
     *
     * @param company name of the company @size(max="255")
     * @return This
     */
    public SenderInfoBuilder withCompany( String company ) {
        this.company = company;
        return this;
    }

    /**
     * Set the sender's title. E.g.: M. Mr. Ms. etc...
     * @param title the sender's title. @size(min="0", max="255")
     * @return This
     */
    public SenderInfoBuilder withTitle( String title ) {
        this.title = title;
        return this;
    }

    public SenderInfoBuilder withTimezoneId(String timezoneId ) {
        this.timezoneId = timezoneId;
        return this;
    }

    /**
     * Builds the actual SenderInfo with the specified values
     *
     * @return the SenderInfo object
     */
    public SenderInfo build() {
        SenderInfo result = new SenderInfo();
        result.setEmail( email );
        result.setFirstName( firstName );
        result.setLastName( lastName );
        result.setCompany( company );
        result.setTitle( title );
        result.setTimezoneId( timezoneId );

        return result;
    }
}
