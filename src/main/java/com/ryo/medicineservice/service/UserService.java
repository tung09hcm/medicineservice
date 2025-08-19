package com.ryo.medicineservice.service;

import com.ryo.medicineservice.dto.request.UserCreationRequest;
import com.ryo.medicineservice.dto.response.UserCreationResponse;
import com.ryo.medicineservice.entity.User;
import com.ryo.medicineservice.mapper.UserMapper;
import com.ryo.medicineservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserCreationResponse createUser(UserCreationRequest request){
        User user = userMapper.requestToUser(request);

        try{
            user = userRepository.save(user);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return userMapper.userToUserCreationResponse(user);
    }
}
