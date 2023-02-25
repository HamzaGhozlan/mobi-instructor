package com.psut.validators;

import io.micrometer.common.util.StringUtils;

import java.util.Set;
import java.util.regex.Pattern;

public class PasswordValidator {
    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 20;
    private static final String[] SPECIAL_CHARACTERS =
            {"@", "-", "_", "+", "^", "%", "#", "$", "!", "(", ")", "=", "?", "[", "]", "&", "<", ">"};

    public void validate(String password, Set<String> violations) {
        boolean isValidPassword = !StringUtils.isBlank(password) &&
                validLength(password) &&
                containsNumbers(password) &&
                containsLowerCase(password) &&
                containsUpperCase(password) &&
                containsSpecialCharacter(password);

        if (!isValidPassword) {
            violations.add("invalid.password");
        }
    }

    private boolean validLength(String password) {
        return password.length() >= MIN_LENGTH &&
                password.length() <= MAX_LENGTH;
    }

    private boolean containsNumbers(String password) {
        return Pattern.matches(".*[1-9].*", password);
    }

    private boolean containsLowerCase(String password) {
        return Pattern.matches(".*[a-z].*", password);
    }

    private boolean containsUpperCase(String password) {
        return Pattern.matches(".*[A-Z].*", password);
    }

    private boolean containsSpecialCharacter(String password) {
        for (String specialChar : SPECIAL_CHARACTERS) {
            if (password.contains(specialChar))
                return true;
        }
        return false;
    }
}
