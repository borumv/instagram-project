package com.example.exceptions;

import org.testcontainers.shaded.com.google.common.base.Strings;

public class UserNotFoundException extends NotFoundException{
    public UserNotFoundException(String id) {
        super("User not found. id: ".concat(id));
    }
}
