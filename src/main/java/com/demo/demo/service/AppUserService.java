package com.demo.demo.service;

import com.demo.demo.dto.AppUserCreateRequest;
import com.demo.demo.entity.AppUser;
import com.demo.demo.entity.Role;
import com.demo.demo.entity.RoleRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AppUserService{

    AppUser createUser(AppUserCreateRequest appUser);

    void addRoleToUser(String roleName, String username);

    List<AppUser> getAllAppUsers();

    void addRequestForRole(String roleName, String username);

    List<RoleRequest> getRequestForRoles();
}
