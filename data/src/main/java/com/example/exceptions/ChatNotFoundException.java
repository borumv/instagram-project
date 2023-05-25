package com.example.exceptions;

public class ChatNotFoundException extends NotFoundException {
    public ChatNotFoundException(String id) {
        super("Chat not found. id: ".concat(id));
    }
}
