package com.example.studentms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeTableDataDTO {
    private int rowId;
    private String teacherId;
    private String teacherName;
    private String subject;
    private String day;
    private String time;
    private String grade;
    private String classRoom;
}
