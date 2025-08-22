package com.ryo.medicineservice.mapper;

import com.ryo.medicineservice.dto.request.UserCreationRequest;
import com.ryo.medicineservice.dto.response.UserResponse;
import com.ryo.medicineservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User requestToUser(UserCreationRequest request);
    UserResponse userToResponse(User user);
}
