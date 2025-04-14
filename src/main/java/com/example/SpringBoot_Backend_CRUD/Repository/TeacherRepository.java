package com.example.SpringBoot_Backend_CRUD.Repository;

import com.example.SpringBoot_Backend_CRUD.Entity.Course;
import com.example.SpringBoot_Backend_CRUD.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher , Integer> {
    @Query("SELECT c FROM Course c WHERE c.teacher.id = :teacherId")
    List<Course> findCoursesByTeacherId(@Param("teacherId") int teacherId);
}
