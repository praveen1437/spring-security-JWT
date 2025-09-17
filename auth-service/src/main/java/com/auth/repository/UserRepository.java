package com.auth.repository;

import com.auth.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(@Param("userName") String userName);
}
