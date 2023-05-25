package com.example.exceptions;

public class PostNotFoundException extends NotFoundException{
    public PostNotFoundException(String id) {
      super("Post not found. id: ".concat(id));
    }
}
