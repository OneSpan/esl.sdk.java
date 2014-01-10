package com.silanis.esl.api.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: jessica
 * Date: 09/01/14
 * Time: 2:18 PM
 *
 * Validator for email address
 *
 */
public class EmailValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /**
     * Validate email with regular expression
     *
     * @param email email for validation
     * @return true valid email, false invalid email
     */
    public boolean valid(final String email) {

        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
