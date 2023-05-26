package com.example.mbtiproject.repositories;

import com.example.mbtiproject.models.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Long> {
    void deleteByParticipant1AndGroupId(String name, Long groupId);
    void deleteByParticipant2AndGroupId(String name, Long groupId);
}
