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
    @Mapping(source = "profile.nickName", target = "nickName")
    @Mapping(source = "profile.photo", target = "photoUrl")
//    @Mapping(source = "followers.size()", target = "countFollowers")
//    @Mapping(source = "followings.size()", target = "countFollowings")
//    @Mapping(source = "posts.size()", target = "countPosts")
    UserDTO userToUserDTO(User user);
}
