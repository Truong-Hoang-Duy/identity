package com.test.devteria.mapper;

import com.test.devteria.dto.request.UserRequest;
import com.test.devteria.dto.request.UserUpdateRequest;
import com.test.devteria.dto.response.UserResponse;
import com.test.devteria.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest userRequest);

//    @Mapping(source = "firstName", target = "lastName")   // Dữ liệu firstName thành lastName
//    @Mapping(target = "lastName", ignore = true)          // Bỏ qua lastName
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
