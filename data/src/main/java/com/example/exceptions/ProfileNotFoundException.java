package com.example.exceptions;

public class ProfileNotFoundException extends NotFoundException{
    public ProfileNotFoundException(String id) {
        super("Profile not found. id: ".concat(id));
    }
}
