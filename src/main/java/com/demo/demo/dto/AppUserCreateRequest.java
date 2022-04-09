package com.demo.demo.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AppUserCreateRequest {
    private String name;
    private String username;
    private String password;
}
