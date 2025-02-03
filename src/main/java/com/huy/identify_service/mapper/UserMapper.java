package com.huy.identify_service.mapper;

import com.huy.identify_service.dto.request.UserCreationRequest;
import com.huy.identify_service.dto.request.UserUpdateRequest;
import com.huy.identify_service.dto.response.UserResponse;
import com.huy.identify_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //    mapper is used to map data from request to entity
    User toUser(UserCreationRequest request);

    //    mapper is used to map data from entity to response
    UserResponse toUserResponse(User user);

    //    mapper is used to update data from request to entity
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}

