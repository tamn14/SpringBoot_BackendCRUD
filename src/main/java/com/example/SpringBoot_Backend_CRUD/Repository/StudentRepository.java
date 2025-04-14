package com.example.SpringBoot_Backend_CRUD.Repository;

import com.example.SpringBoot_Backend_CRUD.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student , Integer> {
}
