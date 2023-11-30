package com.example.studentms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminDTO {
    private String adminId;
    private String nic;
    private String contactNumber;
    private String adminName;
    private String emailAddress;
    private String address;
    private String birthday;
    private String gender;
    private boolean accessibility;
    private boolean rightToWork;
    private String password;
}
