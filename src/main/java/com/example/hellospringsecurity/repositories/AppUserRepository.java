package com.example.hellospringsecurity.repositories;

import com.example.hellospringsecurity.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{

    public AppUser findByEmail(String email);
}
