package com.example.models.dto;


import lombok.Data;

@Data
public class UserDTO {
    private String firstName;
    private String lastName;
    private String nickName;
    private String photoUrl;
    private Long id;
}
