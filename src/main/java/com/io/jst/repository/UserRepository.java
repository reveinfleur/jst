package com.io.jst.repository;

import com.io.jst.model.entity.Users;
import com.io.jst.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long>, UserRepositoryCustom {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByPhoneNumber(String username);
    public Users findByEmail(String email);
}
