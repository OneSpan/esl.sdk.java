package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Sender;

/**
 * Created by schoi on 3/23/15.
 */
public class DelegationUserBuilder {
    private String email;
    private String firstName;
    private String id;
    private String lastName;
    private String name;

    private DelegationUserBuilder( String email ) {
        this.email = email;
    }

    /**
     * Create a new delegation user.
     *
     * @param email the email address uniquely identifying the delegation user. @size(min="6", max="255", valid email address)
     * @return the delegation user builder itself
     */
    public static DelegationUserBuilder newDelegationUser( String email ) {
        return new DelegationUserBuilder( email );
    }

    /**
     * Create a new delegation user.
     *
     * @param sender
     * @return the delegation user builder itself
     */
    public static DelegationUserBuilder newDelegationUser( Sender sender ) {
        return new DelegationUserBuilder( sender.getEmail() )
                .withFirstName(sender.getFirstName())
                .withId(sender.getId())
                .withLastName(sender.getLastName())
                .withName(sender.getName());
    }

    /**
     * Set the delegation user's first name.
     *
     * @param firstName the delegation user's first name
     * @return the delegation user builder itself
     */
    public DelegationUserBuilder withFirstName( String firstName ) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Set the delegation user's id.
     *
     * @param id the delegation user's id
     * @return the delegation user builder itself
     */
    public DelegationUserBuilder withId( String id ) {
        this.id = id;
        return this;
    }

    /**
     * Set the delegation user's last name.
     *
     * @param lastName the delegation user's last name
     * @return the delegation user builder itself
     */
    public DelegationUserBuilder withLastName( String lastName ) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Set the delegation user's name.
     *
     * @param name the delegation user's name
     * @return the delegation user builder itself
     */
    public DelegationUserBuilder withName( String name ) {
        this.name = name;
        return this;
    }

    /**
     * Builds the delegation user object.
     *
     * @return the delegation user
     */
    public com.silanis.esl.sdk.DelegationUser build() {
        com.silanis.esl.sdk.DelegationUser result = new com.silanis.esl.sdk.DelegationUser();
        result.setEmail( email );
        result.setFirstName(firstName);
        result.setId(id);
        result.setLastName(lastName);
        result.setName(name);
        return result;
    }
}
