package com.example.studentms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Student_details")
public class Student {
    @Id
    private String student_id;
    private String student_name;
    private String grade;
    private String studentClass;
    private String emailAddress;
    private String birthday;
    private String gender;
    private String password;
    private boolean accessibility;
}
