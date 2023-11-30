package com.example.studentms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "teachers_details")
public class Teacher {
    @Id
    private String teacherId;
    private String title;
    private String teacherName;
    private String emailAddress;
    private String contactNumber;
    private String subject;
    private String birthday;
    private String gender;
    private String password;
    private boolean accessibility;

}
