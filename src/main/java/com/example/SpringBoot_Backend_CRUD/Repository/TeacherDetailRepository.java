package com.example.SpringBoot_Backend_CRUD.Repository;

import com.example.SpringBoot_Backend_CRUD.Entity.Teacher;
import com.example.SpringBoot_Backend_CRUD.Entity.TeacherDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDetailRepository extends JpaRepository<TeacherDetail, Integer> {
}
