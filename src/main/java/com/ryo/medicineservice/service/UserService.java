package com.ryo.medicineservice.service;

import com.ryo.medicineservice.dto.request.UserCreationRequest;
import com.ryo.medicineservice.dto.response.UserResponse;
import com.ryo.medicineservice.entity.User;
import com.ryo.medicineservice.exception.AppException;
import com.ryo.medicineservice.exception.ErrorCode;
import com.ryo.medicineservice.mapper.UserMapper;
import com.ryo.medicineservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        try{
            user = userRepository.save(user);
        }
        catch(Exception e){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        return userMapper.userToResponse(user);
    }

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
}
