package com.test.devteria.service;

import com.test.devteria.dto.request.UserRequest;
import com.test.devteria.dto.request.UserUpdateRequest;
import com.test.devteria.dto.response.UserResponse;
import com.test.devteria.entity.User;
import com.test.devteria.enums.Role;
import com.test.devteria.exception.AppException;
import com.test.devteria.exception.ErrorCode;
import com.test.devteria.mapper.UserMapper;
import com.test.devteria.repository.RoleRepository;
import com.test.devteria.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor  // Tạo các constructor khi có biến là final
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
//    @Autowired
//    private final UserRepository userRepository;

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public User createUser(UserRequest userRequest) {

        if(userRepository.existsByUsername(userRequest.getUsername())) {
            throw new AppException(ErrorCode.USER_ALREADY_EXIST);
//            throw new RuntimeException("Test");
        }

//        User user = new User();
//        user.setUsername(userRequest.getUsername());
//        user.setPassword(userRequest.getPassword());
//        user.setFirstName(userRequest.getFirstName());
//        user.setLastName(userRequest.getLastName());
//        user.setDob(userRequest.getDob());

        User user = userMapper.toUser(userRequest); // Same as above
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);   Bean thay thế
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        //user.setRoles(roles);

        return userRepository.save(user);
    }

    // PreAuthorize: Kiểm tra trước role = admin, nếu bằng thì mới chạy hàm
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    // PostAuthorize chạy hàm trước, sau đó kiểm tra role = admin
//    @PostAuthorize("hasRole('ADMIN')")
    @PostAuthorize("returnObject.username == authentication.name") //Kiểm tra id trên url có bằng với giá trị hay không
    public UserResponse getUserId(String userId) {
        return userMapper.toUserResponse(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest userUpdateRequest) {
        User user =  userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user, userUpdateRequest);
        user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));

        var roles = roleRepository.findAllById(userUpdateRequest.getRoles());
        user.setRoles(new HashSet<>(roles));

//        user.setPassword(userUpdateRequest.getPassword());
//        user.setFirstName(userUpdateRequest.getFirstName());
//        user.setLastName(userUpdateRequest.getLastName());
//        user.setDob(userUpdateRequest.getDob());


        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public UserResponse getMyInfo() {
        var securityContext = SecurityContextHolder.getContext();
        String name = securityContext.getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }
}
