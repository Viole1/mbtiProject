package com.example.mbtiproject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "relationships", schema = "mbtiproject")
public class Relationship {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "first")
    private String first;
    @Column(name = "second")
    private String second;
    @Column(name = "relationship")
    private Long relationship;
}
