package com.example.exceptions;

public class CommentNotFoundEWxception extends NotFoundException{
    public CommentNotFoundEWxception(String id) {
        super("Comment not found. id: ".concat(id));
    }
}
