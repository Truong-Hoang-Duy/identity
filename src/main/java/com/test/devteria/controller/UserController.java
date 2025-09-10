package com.test.devteria.controller;

import com.test.devteria.dto.response.ApiResponse;
import com.test.devteria.dto.request.UserRequest;
import com.test.devteria.dto.request.UserUpdateRequest;
import com.test.devteria.dto.response.UserResponse;
import com.test.devteria.entity.User;
import com.test.devteria.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
//    @Autowired
//    private UserService userService;

    UserService userService;

    @PostMapping()
    public ApiResponse<User> createUser(@RequestBody @Valid UserRequest userRequest) {
        ApiResponse<User> response = new ApiResponse<>();
        response.setResult(userService.createUser(userRequest));
        return response;
    }

    @GetMapping()
    public ApiResponse<List<UserResponse>> getUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Authenticated username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority ->
                log.info("GrantedAuthority: {}", grantedAuthority.getAuthority())
        );

        return ApiResponse.<List<UserResponse>>builder().result(userService.getUsers()).build();
    }

    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable String userId) {
        return userService.getUserId(userId);
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateUser(userId, userUpdateRequest);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "Deleted User";
    }

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder().result(userService.getMyInfo()).build();
    }
}

