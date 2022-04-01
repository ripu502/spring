package com.demo.demo.dao;

import com.demo.demo.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByUsername(String username);
}
