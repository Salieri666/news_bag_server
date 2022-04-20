package com.sisprog.keyboard.dao;

import com.sisprog.keyboard.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(value = "select u.* from usr u where u.roles = :role", nativeQuery = true)
    List<User> findUsersByRole(String role);

    @Query(value = "select u.* from usr u where u.roles = :role",
            countQuery = "select count(*) from usr u where u.roles = :role",
            nativeQuery = true)
    Page<User> findUsersByRole(String role, Pageable pageable);
}
