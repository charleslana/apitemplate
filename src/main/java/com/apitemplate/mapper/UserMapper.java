package com.apitemplate.mapper;

import com.apitemplate.domain.user.User;
import com.apitemplate.dto.UserRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserRequestDTO toDto(User entity);

    User toEntity(UserRequestDTO dto);
}
