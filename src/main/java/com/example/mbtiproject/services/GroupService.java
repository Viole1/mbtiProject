package com.example.mbtiproject.services;

import com.example.mbtiproject.models.Groups;
import com.example.mbtiproject.models.User;
import com.example.mbtiproject.repositories.GroupsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class GroupService {
    private final GroupsRepository groupsRepository;

    @Transactional
    public void deleteGroup(String name, Long groupId){
        groupsRepository.deleteByParticipant1AndGroupId(name, groupId);
        groupsRepository.deleteByParticipant2AndGroupId(name, groupId);
    }
}
