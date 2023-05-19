package com.example.entities.dto;

import com.example.entities.User;
import lombok.Data;

@Data
public class UserDTO {
    Long id;
    String name;

    public static UserDTO toUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getFirstName());
        return userDTO;
    }
}
