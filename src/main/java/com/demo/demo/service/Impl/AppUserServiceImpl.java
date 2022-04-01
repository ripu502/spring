package com.demo.demo.service.Impl;

import com.demo.demo.dao.AppUserRepo;
import com.demo.demo.dto.AppUserCreateRequest;
import com.demo.demo.entity.AppUser;
import com.demo.demo.entity.Role;
import com.demo.demo.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepo appUserRepo;

    @Override
    public AppUser createUser(AppUserCreateRequest appUserReq) {
        return null;
    }

    @Override
    public void addRoleToUser(Role roleName, String username) {

    }

    @Override
    public List<AppUser> getAllAppUsers() {
        return null;
    }
}
