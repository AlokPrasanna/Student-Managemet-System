package com.example.studentms.repo;

import com.example.studentms.entity.StudentChat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
@Transactional
public interface StudentChatRepo extends JpaRepository<StudentChat,Integer> {
    boolean existsStudentChatBySender(String sender);
    List<StudentChat> getStudentChatBySender(String sender);
    StudentChat findStudentChatByChatId(int chatId);
    void deleteStudentChatByChatId(int chatId);
    @Transactional
    @Modifying
    @Query("UPDATE StudentChat s SET s.readStatus = true WHERE s.chatId = ?1")
    void updateTeacherReadStatus(int chatId);


}
