package com.example.studentms.repo;

import com.example.studentms.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher,String> {
}
