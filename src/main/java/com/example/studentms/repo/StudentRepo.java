package com.example.studentms.repo;

import com.example.studentms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student,String> {
    List<Student> findStudentsByGradeAndStudentClass(String grade, String studentClass);
}
