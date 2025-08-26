package com.ryo.medicineservice.mapper;

import com.ryo.medicineservice.dto.request.PermissionRequest;
import com.ryo.medicineservice.dto.response.PermissionResponse;
import com.ryo.medicineservice.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission requestToPermission(PermissionRequest request);
    PermissionResponse permissionToResponse(Permission permission);
}
