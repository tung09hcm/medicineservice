package com.ryo.medicineservice.controller;


import com.ryo.medicineservice.dto.request.UserCreationRequest;
import com.ryo.medicineservice.dto.response.ApiResponse;
import com.ryo.medicineservice.dto.response.UserCreationResponse;
import com.ryo.medicineservice.mapper.UserMapper;
import com.ryo.medicineservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
    UserMapper userMapper;

    @PostMapping("/createUser")
    ApiResponse<UserCreationResponse> createUser(@RequestBody @Valid UserCreationRequest requestt){
        return ApiResponse.<UserCreationResponse>builder()
                .result(userService.createUser(requestt))
                .build();
    }
}
