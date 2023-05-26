package com.example.mbtiproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "group_id")
    private Long groupId;
    @Column(name = "participant1")
    private String participant1;
    @Column(name = "participant2")
    private String participant2;
    @Column(name = "relationship")
    private Long relationship;
}