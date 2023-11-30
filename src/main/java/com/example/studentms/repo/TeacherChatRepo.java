package com.example.studentms.repo;

import com.example.studentms.entity.TeacherChat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherChatRepo extends JpaRepository<TeacherChat,Integer> {
   boolean existsTeacherChatBySender(String sender);
    List<TeacherChat> getTeacherChatBySender(String sender);
    void deleteTeacherChatByChatId(int chatId);

    TeacherChat findTeacherChatByChatId(int chatId);

    @Transactional
    @Modifying
    @Query("UPDATE TeacherChat s SET s.readStatus = true WHERE s.chatId = ?1")
    void updateStudentReadStatus(int chatId);
}
