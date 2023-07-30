package com.example.springboot.tut.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends CrudRepository<AppUser,Integer> {
    AppUser findByEmail(String email);
}
