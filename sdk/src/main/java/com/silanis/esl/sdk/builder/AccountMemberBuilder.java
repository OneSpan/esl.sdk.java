package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.AccountMember;
import com.silanis.esl.sdk.Address;

/**
 * AccountMemberBuilder is a convenience class to define individual that will be
 * added programmatically to the e-SignLive account
 */
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

    /**
     * Creates a new AccountMember with a unique email address
     * TODO: need to define the parameter length
     * @param email the new member's email address
     * @return This
     */
    public static AccountMemberBuilder newAccountMember( String email ) {
        AccountMemberBuilder result = new AccountMemberBuilder( email );
        return result;
    }

    /**
     * Set the member's company.
     * TODO: need to define the parameter length
     * @param company
     * @return This
     */
    public AccountMemberBuilder withCompany( String company ) {
        this.company = company;
        return this;
    }

    /**
     * Set the member's first name.
     * TODO: need to define the parameter length
     * @param firstName
     * @return This
     */
    public AccountMemberBuilder withFirstName( String firstName ) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Set the member's last name
     * TODO: need to define the parameter length
     * @param lastName
     * @return
     */
    public AccountMemberBuilder withLastName( String lastName ) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Set the member's preferred language to be used in email communications and the web UI interface.
     * TODO: need to define the parameter length. Shouldn't this be a Locale instead of a String???
     * @param language
     * @return This
     */
    public AccountMemberBuilder withLanguage( String language ) {
        this.language = language;
        return this;
    }

    /**
     * Set the member's phone number
     * TODO: need to define the parameter length
     * @param phoneNumber
     * @return This
     */
    public AccountMemberBuilder withPhoneNumber( String phoneNumber ) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    /**
     * Set the member's title.
     * <p>
     * E.g.: Mr. Ms. Mrs. etc...
     * TODO: need to define the parameter length
     * @param title
     * @return This
     */
    public AccountMemberBuilder withTitle( String title ) {
        this.title = title;
        return this;
    }

    /**
     * Set the member's street address.
     * @param builder
     * @return This
     */
    public AccountMemberBuilder withAddress( AddressBuilder builder ) {
        return withAddress( builder.build() );
    }

    /**
     * Set the member's street address.
     * @see #withAddress(AddressBuilder)
     * @param builder
     * @return
     */
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
