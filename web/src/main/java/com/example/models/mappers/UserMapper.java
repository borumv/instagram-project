package com.example.models.mappers;

import com.example.entities.User;
import com.example.models.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(uses = {UserDTO.class, User.class}, componentModel = "spring")
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper( UserMapper.class );
//    UserDTO sourceToDestination(User source);
//    User destinationToSource(SimpleDestination destination);
    @Mapping(source = "profile.nickName", target = "nickName")
    @Mapping(source = "profile.photo", target = "photoUrl")
    UserDTO userToUserDTO(User user);
}
