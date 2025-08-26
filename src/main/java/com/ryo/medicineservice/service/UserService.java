package com.ryo.medicineservice.service;

import com.ryo.medicineservice.dto.request.UserCreationRequest;
import com.ryo.medicineservice.dto.response.UserResponse;
import com.ryo.medicineservice.entity.User;
import com.ryo.medicineservice.entity.Role;
import com.ryo.medicineservice.exception.AppException;
import com.ryo.medicineservice.exception.ErrorCode;
import com.ryo.medicineservice.mapper.UserMapper;
import com.ryo.medicineservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request){
        User user = userMapper.requestToUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setDeleted(false);

        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.builder()
                .name(com.ryo.medicineservice.enums.Role.ADMIN.name())
                .build());
        user.setRoles(roles);

        try{
            user = userRepository.save(user);
        }
        catch(Exception e){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        return userMapper.userToResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUser(){
        return userRepository.findAllByDeletedFalse().stream().map(userMapper::userToResponse).toList();
    }

    public UserResponse deleteUser(String id){
        var user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        UserResponse userResponse = userMapper.userToResponse(user);
        user.setDeleted(true);
        userRepository.save(user);
        return userResponse;
    }

    public UserResponse getUserById(String id){
        var user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        if(user.getDeleted()){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        return userMapper.userToResponse(user);
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        var user = userRepository.findByUsername(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
        );

        return userMapper.userToResponse(user);

    }
}
