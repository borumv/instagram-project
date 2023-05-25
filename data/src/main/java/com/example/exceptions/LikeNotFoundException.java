package com.example.exceptions;

public class LikeNotFoundException extends NotFoundException{
    public LikeNotFoundException(String id) {
        super("Like not found. id: ".concat(id));
    }
}
