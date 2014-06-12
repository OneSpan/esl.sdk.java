package com.silanis.esl.sdk.builder;

import com.google.common.base.Optional;
import com.silanis.esl.sdk.AccountMember;
import com.silanis.esl.sdk.Address;
import com.silanis.esl.sdk.SenderStatus;

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
    private Optional<SenderStatus> status = Optional.absent();

    private AccountMemberBuilder( String email ) {
        this.email = email;
    }

    /**
     * Creates a new AccountMember with a unique email address
     * @param email the new member's email address (max 255, valid email address)
     * @return This
     */
    public static AccountMemberBuilder newAccountMember( String email ) {
        AccountMemberBuilder result = new AccountMemberBuilder( email );
        return result;
    }

    /**
     * Set the member's company.
     * @param company (max 255, any characters)
     * @return This
     */
    public AccountMemberBuilder withCompany( String company ) {
        this.company = company;
        return this;
    }

    /**
     * Set the member's first name.
     * @param firstName (max 100, any characters)
     * @return This
     */
    public AccountMemberBuilder withFirstName( String firstName ) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Set the member's last name
     * @param lastName (max 100, any characters)
     * @return This
     */
    public AccountMemberBuilder withLastName( String lastName ) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Set the member's preferred language to be used in email communications and the web UI interface.
     * @param language The two-letter abbreviation for the language (ie. "en", "fr", "de", etc.);
     * @return This
     * @deprecated Please use withLanguage( Locale locale ) instead
     */
    @Deprecated
    public AccountMemberBuilder withLanguage( String language ) {
        this.language = language;
        return this;
    }

    /**
     * Set the member's phone number
     * @param phoneNumber (max 40, any characters)
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
     * @param title (max 64, any characters)
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
     * @param address
     * @return This
     */
    public AccountMemberBuilder withAddress( Address address ) {
        this.address = address;
        return this;
    }

    public AccountMemberBuilder withStatus( SenderStatus status ) {
        this.status = Optional.of(status);
        return this;
    }

    /**
     * Build the actual SenderInfo with the specified values
     *
     * @return AccountMember
     */
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
        if ( status.isPresent() ) {
            result.setStatus(status.get());
        }

        return result;
    }

}
