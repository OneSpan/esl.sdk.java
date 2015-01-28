package com.silanis.esl.sdk.builder;

import java.util.Map;

/**
 * Created by schoi on 1/28/15.
 */
public class GroupSummaryBuilder {
    private Map<String, Object> data;
    private String email;
    private String id;
    private String name;

    private GroupSummaryBuilder( String email ) {
        this.email = email;
    }

    /**
     * Create a new group summary.
     *
     * @param email the email address uniquely identifying the group summary. @size(min="6", max="255", valid email address)
     * @return the group summary builder itself
     */
    public static GroupSummaryBuilder newGroupSummary( String email ) {
        return new GroupSummaryBuilder( email );
    }

    /**
     * Set the group summary's id.
     *
     * @param id the group summary's id
     * @return the group summary builder itself
     */
    public GroupSummaryBuilder withId( String id ) {
        this.id = id;
        return this;
    }

    /**
     * Set the group summary's name.
     *
     * @param name the group summary's name
     * @return the group summary builder itself
     */
    public GroupSummaryBuilder withName( String name ) {
        this.name = name;
        return this;
    }

    /**
     * Set the group summary's data.
     *
     * @param data the group summary's data
     * @return the group summary builder itself
     */
    public GroupSummaryBuilder withData( Map<String, Object> data ) {
        this.data = data;
        return this;
    }

    /**
     * Builds the group summary object.
     *
     * @return the group summary
     */
    public com.silanis.esl.sdk.GroupSummary build() {
        com.silanis.esl.sdk.GroupSummary result = new com.silanis.esl.sdk.GroupSummary();
        result.setEmail( email );
        result.setId( id );
        result.setName( name );
        result.setData( data );
        return result;
    }
}
