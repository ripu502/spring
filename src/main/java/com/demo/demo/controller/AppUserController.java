package com.demo.demo.controller;

import com.demo.demo.dto.AddRoleRequest;
import com.demo.demo.dto.AppUserCreateRequest;
import com.demo.demo.entity.AppUser;
import com.demo.demo.entity.RoleRequest;
import com.demo.demo.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Taking reference from https://github.com/ripu502-deloitte/bookMyshow
 */


@RestController @RequiredArgsConstructor
@RequestMapping("api/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    /**
     * Register new user to the application with customer role
     * @param appUserCreateRequest
     * @return
     */
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody AppUserCreateRequest appUserCreateRequest)
    {
        appUserService.createUser(appUserCreateRequest);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    @PostMapping("/request/role/{id}")
    public ResponseEntity<String> addRoleToAppUser(@PathVariable(name = "id") Long id){
        appUserService.addRoleToUser(id);
        return new ResponseEntity<>("Added Role", HttpStatus.CREATED);
    }

    /**
     * Temp routes for the testing
     * @return
     */
    @GetMapping @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<AppUser>> getAllAppUser()
    {
        List<AppUser> response = appUserService.getAllAppUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Raising request for the role
     * @param principal
     * @param addRoleRequest
     * @return
     */
    @PostMapping("/request/role")
    public ResponseEntity<String> requestForRole(Principal principal,
                                                 @RequestBody AddRoleRequest addRoleRequest)
    {
        appUserService.addRequestForRole(addRoleRequest.getRole(), principal.getName());
        return new ResponseEntity<>("Requested", HttpStatus.CREATED);
    }

    @GetMapping("/request/role")
    public ResponseEntity<List<RoleRequest>> requestsForRole()
    {
        List<RoleRequest> res = appUserService.getRequestForRoles();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
