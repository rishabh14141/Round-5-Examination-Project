package com.example.examination.Exceptions;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserExceptions {

    private static final String USER_NOT_FOUND = "User %s not found";
    private static final String USER_ALREADY_EXISTS = "User with phoneNumber %s already exists";
    private static final String INVALID_USER = "Invalid User";

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String field) {
            super(String.format(USER_NOT_FOUND, field));
        }
    }

    public static class DuplicateUserException extends RuntimeException {
        public DuplicateUserException(String email) {
            super(String.format(USER_ALREADY_EXISTS, email));
        }
    }

    public static class InvalidUser extends RuntimeException {
        public InvalidUser() {
            super(String.format(INVALID_USER));
        }
    }
}