package com.example.studentms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
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
