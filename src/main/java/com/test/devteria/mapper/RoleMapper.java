package com.test.devteria.mapper;

import com.test.devteria.dto.request.RoleRequest;
import com.test.devteria.dto.response.RoleResponse;
import com.test.devteria.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequestRequest);

    RoleResponse toRoleResponse(Role role);
}
