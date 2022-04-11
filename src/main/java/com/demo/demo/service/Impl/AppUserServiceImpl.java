package com.demo.demo.service.Impl;

import com.demo.demo.dao.AppUserRepo;
import com.demo.demo.dto.AppUserCreateRequest;
import com.demo.demo.entity.AppUser;
import com.demo.demo.entity.Product;
import com.demo.demo.entity.Role;
import com.demo.demo.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor @Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    @Autowired
    private AppUserRepo appUserRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepo.findUserByUsername(username);
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

        User springUser = new User(user.getUsername(), user.getPassword(), authorities);
        return springUser;
    }

    @Override
    public AppUser createUser(AppUserCreateRequest appUserReq) {
        Set<Role> roleSet = new HashSet<>(); roleSet.add(Role.ROLE_CUSTOMER);
        List<Product> productList = new ArrayList<>();

        AppUser user = new AppUser();

        user.setUsername(appUserReq.getUsername());
        user.setPassword(passwordEncoder.encode(appUserReq.getPassword()));
        user.setRoleSet(roleSet);
        user.setProducts(productList);

        appUserRepo.save(user);

        return user;
    }

    @Override
    public void addRoleToUser(Role roleName, String username) {

    }

    @Override
    public List<AppUser> getAllAppUsers() {
        List<AppUser> users = appUserRepo.findAll();
        return users;
    }

    @Override
    public void addRequestForRole(Role roleName, String username) {
    }

}
