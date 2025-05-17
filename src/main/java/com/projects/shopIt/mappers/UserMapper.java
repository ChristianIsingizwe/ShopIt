package com.projects.shopIt.mappers;

import com.projects.shopIt.dtos.RegisterUserRequest;
import com.projects.shopIt.dtos.UserDto;
import com.projects.shopIt.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
}
