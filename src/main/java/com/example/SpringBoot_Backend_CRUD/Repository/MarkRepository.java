package com.example.SpringBoot_Backend_CRUD.Repository;

import com.example.SpringBoot_Backend_CRUD.Entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepository extends JpaRepository<Mark , Integer> {
}
