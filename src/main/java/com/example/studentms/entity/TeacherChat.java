package com.example.studentms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "teacher_chat_table")
public class TeacherChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatId;
    private String sender;
    private String receiver;
    private String message;
    private boolean readStatus;
}
