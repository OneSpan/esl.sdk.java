package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.AccountMember;
import com.silanis.esl.sdk.Address;

public class AccountMemberBuilder {

    private Address address;
    private String company;
    private String email;
    private String firstName;
    private String lastName;
    private String language;
    private String phoneNumber;
    private String title;

    private AccountMemberBuilder( String email ) {
        this.email = email;
    }

    public static AccountMemberBuilder newAccountMember( String email ) {
        AccountMemberBuilder result = new AccountMemberBuilder( email );
        return result;
    }

    public AccountMemberBuilder withCompany( String company ) {
        this.company = company;
        return this;
    }

    public AccountMemberBuilder withFirstName( String firstName ) {
        this.firstName = firstName;
        return this;
    }

    public AccountMemberBuilder withLastName( String lastName ) {
        this.lastName = lastName;
        return this;
    }

    public AccountMemberBuilder withLanguage( String language ) {
        this.language = language;
        return this;
    }

    public AccountMemberBuilder withPhoneNumber( String phoneNumber ) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public AccountMemberBuilder withTitle( String title ) {
        this.title = title;
        return this;
    }

    public AccountMemberBuilder withAddress( AddressBuilder builder ) {
        return withAddress( builder.build() );
    }

    public AccountMemberBuilder withAddress( Address address ) {
        this.address = address;
        return this;
    }

    public AccountMember build() {
        AccountMember result = new AccountMember();

        result.setAddress(address);
        result.setCompany(company);
        result.setEmail(email);
        result.setFirstName(firstName);
        result.setLastName(lastName);
        result.setLanguage(language);
        result.setPhoneNumber(phoneNumber);
        result.setTitle(title);

        return result;
    }





}
