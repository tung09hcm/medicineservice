package com.ryo.medicineservice.mapper;

import com.ryo.medicineservice.dto.request.UserCreationRequest;
import com.ryo.medicineservice.dto.response.UserCreationResponse;
import com.ryo.medicineservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User requestToUser(UserCreationRequest request);
    UserCreationResponse userToUserCreationResponse(User user);
}
