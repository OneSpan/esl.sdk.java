package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>FieldValidator class allows you to restrict the range of values accepted by a specific unbound text field.</p>
 * <p>When building a field validator, you first select the type of validator, and then optionally add one or more parameters to it, such as maximum length.</p>
 */
public class FieldValidator {
    private Integer minLength;
    private Integer maxLength;
    private String regex;
    private List<String> options;
    private boolean required;
    private boolean disabled;
    private String group;
    private Integer minimumRequired;
    private String errorMessage;

    public FieldValidator() {
        options = new ArrayList<String>();
    }

    public void setMinLength( int minLength ) {
        this.minLength = minLength;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMaxLength( int maxLength ) {
        this.maxLength = maxLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setRegex( String regex ) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void addOption(String option) {
        if (null == option) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        this.options.add(option);
    }

    public void setRequired( boolean required ) {
        this.required = required;
    }

    public boolean isRequired() {
        return required;
    }

    public void setDisabled( boolean disabled ) {
        this.disabled = disabled;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setGroup( String group ){
        this.group = group;
    }

    public String getGroup(){
        return this.group;
    }

    public void setMinimumRequired( int minimumRequired ) {
        this.minimumRequired = minimumRequired;
    }

    public Integer getMinimumRequired() {
        return minimumRequired;
    }

    public void setErrorMessage( String errorMessage ) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
