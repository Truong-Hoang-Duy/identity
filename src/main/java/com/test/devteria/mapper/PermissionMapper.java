package com.test.devteria.mapper;

import com.test.devteria.dto.request.PermissionRequest;
import com.test.devteria.dto.response.PermissionResponse;
import com.test.devteria.dto.response.UserResponse;
import com.test.devteria.entity.Permission;
import com.test.devteria.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permissionRequest);
    PermissionResponse toPermissionResponse(Permission permission);
}
