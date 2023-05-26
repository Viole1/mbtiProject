package com.example.mbtiproject.repositories;

import com.example.mbtiproject.models.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
    Relationship findByFirstAndSecond(String type1, String type2);
}
