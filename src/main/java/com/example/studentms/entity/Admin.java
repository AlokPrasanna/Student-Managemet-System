package com.example.studentms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "admin_details")
public class Admin {
    @Id
    private String adminId;
    private String nic;
    private String adminName;
    private String contactNumber;
    private String emailAddress;
    private String address;
    private String birthday;
    private String gender;
    private boolean accessibility;
    private boolean rightToWork;
    private String password;
}
