package com.example.studentms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "reply_message_table")
public class ReplyMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatId;
    private String sender;
    private String receiver;
    private Integer senderTableChatId;
    private String message;
    private boolean receiverCanSee;

}
