package com.demo.demo.service;

import com.demo.demo.dto.AppUserCreateRequest;
import com.demo.demo.entity.AppUser;
import com.demo.demo.entity.Role;

import java.util.List;

public interface AppUserService {

    AppUser createUser(AppUserCreateRequest appUser);

    void addRoleToUser(Role roleName, String username);

    List<AppUser> getAllAppUsers();
}
