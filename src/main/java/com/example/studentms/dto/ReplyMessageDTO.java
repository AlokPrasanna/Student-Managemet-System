package com.example.studentms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyMessageDTO {
    private int chatId;
    private String sender;
    private String receiver;
    private Integer senderTableChatId;
    private String message;
    private boolean receiverCanSee;
}
