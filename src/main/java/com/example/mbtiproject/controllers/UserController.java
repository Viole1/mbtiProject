package com.example.mbtiproject.controllers;

import com.example.mbtiproject.models.User;
import com.example.mbtiproject.repositories.UserRepository;
import com.example.mbtiproject.services.GroupService;
import com.example.mbtiproject.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final GroupService groupService;

    @GetMapping("/users/new")
    public String showAddMemberForm(Model model){
        model.addAttribute("user", new User());
        return "addMember";
    }

    @PostMapping("/users")
    public String addMember(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{name}")
    public String personalCabinet(@PathVariable String name, Model model){
        User user1 = userRepository.findByName(name);
        List<String> users = userRepository.findAllNames();

        // проходит по всем юзерам, если они с той же группы и это не один и тот же человек, обновляет списки
        for (int i = 0; i < users.size(); i++){
            if(users.get(i).equals(name)) continue;
            else{
                User user2 = userRepository.findByName(users.get(i));
                Long relationship = userService.getRelationship(user1.getType(), user2.getType());

                userService.updateLists(relationship, user1, user2);
            }
        }

        model.addAttribute("type", user1.getType());
        model.addAttribute("bad", user1.getBad());
        model.addAttribute("normal", user1.getNormal());
        model.addAttribute("ok", user1.getOk());
        model.addAttribute("good", user1.getGood());
        model.addAttribute("best", user1.getBest());

        log.info("{}: bad: {}; normal: {}; ok: {}; good: {}; best: {};", user1.getName(), user1.getBad().size(), user1.getNormal().size(), user1.getOk().size(),
                user1.getGood().size(), user1.getBest().size());
        return "personalCabinet";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/users/delete/{name}")
    public String deleteUser(@PathVariable String name,
                             @RequestParam("groupId") Long groupId){
        log.info("User: {} with groupId: {} was deleted", name, groupId);
        userService.deleteUser(name, groupId);
        groupService.deleteGroup(name, groupId);
        return "redirect:/users";
    }
}
