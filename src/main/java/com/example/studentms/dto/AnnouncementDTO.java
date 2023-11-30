package com.example.studentms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDTO {
    private  int announcementId;
    private String adminId;
    private String announcement;
    private String audience;
}
