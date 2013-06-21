package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.RadioButtonOption;
import com.silanis.esl.sdk.RadioButtonSet;

import java.util.ArrayList;
import java.util.List;

public class RadioButtonSetBuilder {
    private String email;
    private String group;
    private final List<RadioButtonOption> options;

    public RadioButtonSetBuilder() {
        this.options = new ArrayList<RadioButtonOption>();
    }

    public RadioButtonSetBuilder(String email) {
        this();
        this.email = email;
    }

    public static RadioButtonSetBuilder newRadioButtonsForSigner( String email ) {
        return new RadioButtonSetBuilder( email );
    }

    public RadioButtonSetBuilder withName( String group ) {
        this.group = group;
        return this;
    }

    public RadioButtonSetBuilder withOption( RadioButtonOptionBuilder builder ) {
        return withOption( builder.build() );
    }

    public RadioButtonSetBuilder withOption( RadioButtonOption option ) {
        options.add( option );
        return this;
    }

    public RadioButtonSet build() {
        RadioButtonSet set = new RadioButtonSet(group);
        set.setEmail( email );
        set.getOptions().addAll( options );
        return set;
    }
}