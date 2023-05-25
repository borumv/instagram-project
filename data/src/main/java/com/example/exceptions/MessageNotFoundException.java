package com.example.exceptions;

public class MessageNotFoundException extends NotFoundException{
    public MessageNotFoundException(String id) {
        super("Message not found. id: ".concat(id));
    }
}
