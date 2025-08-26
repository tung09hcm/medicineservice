package com.ryo.medicineservice.mapper;

import com.ryo.medicineservice.dto.request.RoleRequest;
import com.ryo.medicineservice.dto.response.RoleResponse;
import com.ryo.medicineservice.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
