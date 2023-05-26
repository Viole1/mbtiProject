package com.example.mbtiproject.repositories;

import com.example.mbtiproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User findByName(String name);
    @Query("SELECT u.name FROM User u")
    List<String> findAllNames();
    void deleteByNameAndGroupId(String name, Long groupId);
}
