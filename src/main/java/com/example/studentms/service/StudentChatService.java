package com.example.studentms.service;

import com.example.studentms.dto.StudentChatDTO;
import com.example.studentms.entity.StudentChat;
import com.example.studentms.repo.StudentChatRepo;
import com.example.studentms.repo.StudentRepo;
import com.example.studentms.util.varList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentChatService {
    @Autowired
    private StudentChatRepo studentChatRepo;

    @Autowired
    private ModelMapper modelMapper;

    public int saveStudentChat(StudentChatDTO studentChatDTO){
        if(studentChatDTO.getSender().isEmpty() || studentChatDTO.getReceiver().isEmpty() || studentChatDTO.getMessage().isEmpty() || studentChatDTO.getSender().equals(studentChatDTO.getReceiver())){
            return 0;
        }else {
            StudentChat studentSavedChat = studentChatRepo.save(modelMapper.map(studentChatDTO, StudentChat.class));
            return studentSavedChat.getChatId();
        }
    }
    public List<StudentChatDTO> getAllStudentMessage(String sender){
        if(studentChatRepo.existsStudentChatBySender(sender)){
            // studentId exist
            List<StudentChat> studentChatList = studentChatRepo.getStudentChatBySender(sender);
            return modelMapper.map(studentChatList, new TypeToken<ArrayList<StudentChatDTO>>(){}.getType());
        }else {
            // studentId not in the server
            return null;
        }
    }
    public String updateReadStatus(int chatId){
        if(studentChatRepo.existsById(chatId)){
            StudentChat studentChat = studentChatRepo.findStudentChatByChatId(chatId);
            if(studentChat.isReadStatus()){
                return varList.RSP_FAIL;
            }else{
                studentChatRepo.updateTeacherReadStatus(chatId);
                return varList.RSP_SUCCESS;
            }
        }else {
            return varList.RSP_TOKEN_INVALID;
        }
    }
    public String deleteChat(int chatId){
        if(studentChatRepo.existsById(chatId)){
            studentChatRepo.deleteStudentChatByChatId(chatId);
            return varList.RSP_SUCCESS;
        }else {
        return varList.RSP_NO_DATA_FOUND;
        }
    }
}
