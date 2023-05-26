package com.example.mbtiproject.services;

import com.example.mbtiproject.models.Groups;
import com.example.mbtiproject.models.User;
import com.example.mbtiproject.repositories.GroupsRepository;
import com.example.mbtiproject.repositories.RelationshipRepository;
import com.example.mbtiproject.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RelationshipRepository relationshipRepository;
    private final GroupsRepository groupsRepository;

    public void saveUser(User user){
        User userFromDB = userRepository.save(user);
        log.info("Saving new User. id: {}; Name: {}; Group_id: {} Type: {} ", user.getId(), user.getName(), user.getGroupId(), user.getType());
        userRepository.save(userFromDB);

        for (Long i = userFromDB.getId() - 1; i > 0; i--) {
            User user2 = userRepository.findById(i).orElse(null);
            if (user2 == null) continue;
            else if (userFromDB.getGroupId().equals(user2.getGroupId()))
                saveGroup(userFromDB, user2, userFromDB.getGroupId());
        }
        log.info("{}'s relationships: bad: {}; normal: {}; ok: {}; good: {}; best: {} ",
                user.getName() ,user.getBad().size(), user.getNormal().size(),
                user.getOk().size(), user.getGood().size(), user.getBest().size());
    }

    @Transactional
    public void deleteUser(String name, Long groupId){
        userRepository.deleteByNameAndGroupId(name, groupId);
    }
    public Long getRelationship(String type1, String type2){
        Long relationshipId = relationshipRepository.findByFirstAndSecond(type1, type2).getRelationship();
        return relationshipId;
    }
    public void saveGroup(User user1, User user2, Long groupId){
        Groups group = new Groups();
        Long relationship = getRelationship(user1.getType(), user2.getType());

        group.setGroupId(groupId);
        group.setParticipant1(user1.getName());
        group.setParticipant2(user2.getName());
        group.setRelationship(relationship);
        groupsRepository.save(group);

        updateLists(relationship, user1, user2);
    }

    public void updateLists(Long relationship, User user1, User user2){
        switch (relationship.intValue()){
            case 0:{
                user1.addToBad(user2);
                user2.addToBad(user1);
                break;
            }
            case 1:{
                user1.addToNormal(user2);
                user2.addToNormal(user1);
                break;
            }
            case 2:{
                user1.addToOk(user2);
                user2.addToOk(user1);
                break;
            }
            case 3:{
                user1.addToGood(user2);
                user2.addToGood(user1);
                break;
            }
            case 4:{
                user1.addToBest(user2);
                user2.addToBest(user1);
                break;
            }
        }
    }
}
