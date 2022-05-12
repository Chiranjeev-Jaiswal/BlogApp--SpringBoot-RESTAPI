package com.springbootblogrestapi.Repository;

import com.springbootblogrestapi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User>findByEmail(String email);
    Optional<User>findByUsernameOrEmail(String Username,String email);
    Optional<User>findByUsername(String Username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
