package com.test.devteria.controller;

import com.test.devteria.dto.request.RoleRequest;
import com.test.devteria.dto.response.ApiResponse;
import com.test.devteria.dto.response.RoleResponse;
import com.test.devteria.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest RoleRequest) {
        return ApiResponse.<RoleResponse>builder().result(roleService.create(RoleRequest)).build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> findAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission) {
        return ApiResponse.<Void>builder().build();
    }
}
