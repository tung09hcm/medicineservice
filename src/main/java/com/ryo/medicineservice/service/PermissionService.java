package com.ryo.medicineservice.service;

import com.ryo.medicineservice.dto.request.PermissionRequest;
import com.ryo.medicineservice.dto.response.PermissionResponse;
import com.ryo.medicineservice.entity.Permission;
import com.ryo.medicineservice.mapper.PermissionMapper;
import com.ryo.medicineservice.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public PermissionResponse create(PermissionRequest request){
        Permission permission = permissionMapper.requestToPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.permissionToResponse(permission);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<PermissionResponse> getAll(){
        return permissionRepository.findAll().stream().map(permissionMapper::permissionToResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deletePermission(String name){
        permissionRepository.deleteById(name);
    }
}
