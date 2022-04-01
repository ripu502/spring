package com.demo.demo.dto;

import lombok.Data;

@Data
public class AppUserCreateRequest {
    private String name;
    private String username;
    private String password;
}
