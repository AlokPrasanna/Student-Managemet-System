package com.example.studentms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {
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
