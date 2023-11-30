package com.example.studentms.repo;

import com.example.studentms.entity.Admin;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepo extends JpaRepository<Admin,String> {
    Admin findAdminByAdminId(String adminId);
    @Transactional
    @Modifying
    @Query("UPDATE Admin a SET a.accessibility = true WHERE a.adminId = ?1")
    void updateAccessibilityByAdminId(String adminId);
}
