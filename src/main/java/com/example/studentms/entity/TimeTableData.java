package com.example.studentms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "time_table_data")
public class TimeTableData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rowId;
    private String teacherId;
    private String teacherName;
    private String subject;
    private String day;
    private String time;
    private String grade;
    private String classRoom;

}
