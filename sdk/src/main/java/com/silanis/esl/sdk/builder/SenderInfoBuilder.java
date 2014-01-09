package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.SenderInfo;

public class SenderInfoBuilder {
    private String email = null;
    private String firstName = null;
    private String lastName = null;
    private String company = null;
    private String title = null;

    public static SenderInfoBuilder newSenderInfo(String email) {
        return new SenderInfoBuilder(email);
    }

    private SenderInfoBuilder(String email) {
        this.email = email;
    }

    public SenderInfoBuilder withName( String firstName, String lastName ) {
        this.firstName = firstName;
        this.lastName = lastName;
        return this;
    }

    public SenderInfoBuilder withCompany( String company ) {
        this.company = company;
        return this;
    }

    public SenderInfoBuilder withTitle( String title ) {
        this.title = title;
        return this;
    }

    public SenderInfo build() {
        SenderInfo result = new SenderInfo();
        result.setEmail( email );
        result.setFirstName( firstName );
        result.setLastName( lastName );
        result.setCompany( company );
        result.setTitle( title );

        return result;
    }
}
