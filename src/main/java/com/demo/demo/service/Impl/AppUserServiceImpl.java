package com.demo.demo.service.Impl;

import com.demo.demo.dao.AppUserRepo;
import com.demo.demo.dao.RoleRequestRepo;
import com.demo.demo.dto.AppUserCreateRequest;
import com.demo.demo.entity.AppUser;
import com.demo.demo.entity.Product;
import com.demo.demo.entity.Role;
import com.demo.demo.entity.RoleRequest;
import com.demo.demo.exception.BadRequestException;
import com.demo.demo.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor @Service @Slf4j
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private RoleRequestRepo roleRequestRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final Logger LOG = LogManager.getLogger(AppUserServiceImpl.class);


    @Override @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepo.findUserByUsername(username);
        LOG.info("LOG: Some info to be from slf4j");
        LOG.debug("this is for the debug");
        LOG.error("tthis is error");
        log.error("this is final test 101");
        if(user == null)
        {
            throw new UsernameNotFoundException("NO USER OF THIS USER NAME FOUND");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        System.out.println(user);
        System.out.println(user.getRoleSet());
        user.getRoleSet()
                .forEach(role -> authorities
                        .add(new SimpleGrantedAuthority(role.toString())
                        ));

        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public AppUser createUser(AppUserCreateRequest appUserReq) {
        Set<Role> roleSet = new HashSet<>(); roleSet.add(Role.ROLE_CUSTOMER);
        List<Product> productList = new ArrayList<>();

        AppUser user = new AppUser();
        user.setName(appUserReq.getName());
        user.setUsername(appUserReq.getUsername());
        user.setPassword(passwordEncoder.encode(appUserReq.getPassword()));
        user.setRoleSet(roleSet);
        user.setProducts(productList);

        appUserRepo.save(user);

        return user;
    }

    @Override
    public void addRoleToUser(Long id) {
        RoleRequest roleRequest = roleRequestRepo.findById(id).get();
        AppUser user = appUserRepo.findUserByUsername(roleRequest.getUsername());
        user.getRoleSet().add(roleRequest.getRole());
        appUserRepo.save(user);
    }

    @Override
    public List<AppUser> getAllAppUsers() {
        //        throw new BadRequestException("THIS IS JUST for the test");
        return appUserRepo.findAll();
    }

    @Override
    public void addRequestForRole(String roleName, String username) {
        Role role = Role.valueOf(Role.class, roleName);
        RoleRequest newRoleRequest = new RoleRequest();
        newRoleRequest.setRole(role);
        newRoleRequest.setUsername(username);
        roleRequestRepo.save(newRoleRequest);
    }

    @Override
    public List<RoleRequest> getRequestForRoles() {
        return roleRequestRepo.findAll();
    }


}
