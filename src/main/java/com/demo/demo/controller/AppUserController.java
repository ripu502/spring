package com.demo.demo.controller;

import com.demo.demo.dto.AppUserCreateRequest;
import com.demo.demo.entity.AppUser;
import com.demo.demo.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Taking reference from https://github.com/ripu502-deloitte/bookMyshow
 */


@RestController @RequiredArgsConstructor
@RequestMapping("api/users")
public class AppUserController {

    private AppUserService appUserService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody AppUserCreateRequest appUserCreateRequest)
    {
        appUserService.createUser(appUserCreateRequest);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }
}
