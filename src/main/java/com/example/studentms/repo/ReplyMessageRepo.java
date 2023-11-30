package com.example.studentms.repo;

import com.example.studentms.entity.ReplyMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyMessageRepo extends JpaRepository<ReplyMessage,Integer> {
    boolean existsReplyMessageByReceiver(String receiver);

    List<ReplyMessage> getReplyMessageByReceiver(String receiver);


    ReplyMessage getReplyMessageByChatId(int chatId);

    /*@Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM ReplyMessage r " +
            "WHERE (:teacherTableChatId IS NULL AND :studentTableChatId IS NOT NULL AND r.studentTableChatId = :studentTableChatId) " +
            "   OR (:teacherTableChatId IS NOT NULL AND :studentTableChatId IS NULL AND r.teacherTableChatId = :teacherTableChatId) " +
            "   OR (:teacherTableChatId IS NOT NULL AND :studentTableChatId IS NOT NULL " +
            "       AND r.teacherTableChatId = :teacherTableChatId AND r.studentTableChatId = :studentTableChatId)")
    boolean existsReplyMessageByTeacherTableChatIdOrStudentTableChatId(
            @Param("teacherTableChatId") Integer teacherTableChatId,
            @Param("studentTableChatId") Integer studentTableChatId);*/
}
