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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
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
    public List<User> getUsers() {
        return userService.getUsers();
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
}

