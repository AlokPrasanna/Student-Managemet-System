package com.example.studentms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "term_test_files")
@Builder
public class TermTestTimetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fileId;
    private String name;
    private String type;
    @Lob
    @Column(length = 1000, columnDefinition = "LONGBLOB")
    private byte[] fileData;
}
