package com.flower.shop.rest.util;

import java.util.regex.Pattern;

public class RegexMatcher {

    private static final String POSTAL_CODE_REGEX = "^[0-9]{2}-[0-9]{3}$";
    private static final String ONLY_CHAR_REGEX = "^[A-Z]{1}[a-z]+$";
    private static final String EMAIL_REGEX = "^\\w+@[a-z]+[.][a-z]+$";

    public static boolean postalCodeMatcher(String postalCode) {
        return Pattern.matches(POSTAL_CODE_REGEX, postalCode);
    }

    public static boolean noNumbersMatcher(String string) {
        return Pattern.matches(ONLY_CHAR_REGEX, string);
    }

    public static boolean emailMatcher(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

}
