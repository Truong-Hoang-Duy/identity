package com.test.devteria.service;

import com.test.devteria.dto.request.UserRequest;
import com.test.devteria.dto.request.UserUpdateRequest;
import com.test.devteria.dto.response.UserResponse;
import com.test.devteria.entity.User;
import com.test.devteria.exception.AppException;
import com.test.devteria.exception.ErrorCode;
import com.test.devteria.mapper.UserMapper;
import com.test.devteria.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor  // Tạo các constructor khi có biến là final
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
//    @Autowired
//    private final UserRepository userRepository;

    UserRepository userRepository;
    UserMapper userMapper;

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
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUserId(String userId) {
        return userMapper.toUserResponse(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest userUpdateRequest) {
        User user =  userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user, userUpdateRequest);

//        user.setPassword(userUpdateRequest.getPassword());
//        user.setFirstName(userUpdateRequest.getFirstName());
//        user.setLastName(userUpdateRequest.getLastName());
//        user.setDob(userUpdateRequest.getDob());


        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
