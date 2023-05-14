package com.example.entities;

public interface BaseEntity<T> {
    T getId();
    void setId(T t);
}
