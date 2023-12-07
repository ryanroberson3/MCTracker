package com.nashss.se.mctracker.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class IdUtils {

    private static final Pattern INVALID_CHARACTER_PATTERN = Pattern.compile("[\"'\\\\]");
    private static final int MAX_ID_LENGTH = 3;

    private IdUtils() {
    }

    /**
     * Method to determine if string is a valid string.
     *
     * @param stringToValidate string to see if it is a valid string.
     * @return true if valid or false if not.
     */
    public static boolean isValidString(String stringToValidate) {
        if (StringUtils.isBlank(stringToValidate)) {
            return false;
        } else {
            return !INVALID_CHARACTER_PATTERN.matcher(stringToValidate).find();
        }
    }

    /**
     * Method to generate a gameID.
     *
     * @return generated gameId.
     */
    public static String generateGameId() {
        return "GAME" + RandomStringUtils.randomAlphanumeric(MAX_ID_LENGTH);
    }

}
