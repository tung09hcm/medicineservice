package com.ryo.medicineservice.repository;

import com.ryo.medicineservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByDeletedFalse();
    User findById(String id);
    User findByUsername(String username);
}
