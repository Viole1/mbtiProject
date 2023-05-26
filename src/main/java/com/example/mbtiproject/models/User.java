package com.example.mbtiproject.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Transient
    private List<String> bad = new ArrayList<>();

    @Transient
    private List<String> normal = new ArrayList<>();

    @Transient
    private List<String> ok = new ArrayList<>();

    @Transient
    private List<String> good = new ArrayList<>();

    @Transient
    private List<String> best = new ArrayList<>();

    public void addToBad(User user) { bad.add(user.getName());}
    public void addToNormal(User user) { normal.add(user.getName());}
    public void addToOk(User user) { ok.add(user.getName());}
    public void addToGood(User user) { good.add(user.getName());}
    public void addToBest(User user) { best.add(user.getName());}

}
