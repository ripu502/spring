package com.demo.demo.dto;

import com.demo.demo.entity.Role;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class AddRoleRequest {
    private String username;
    private Role role;
}
