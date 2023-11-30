package com.example.studentms.service;

import com.example.studentms.dto.TeacherChatDTO;
import com.example.studentms.entity.TeacherChat;
import com.example.studentms.repo.TeacherChatRepo;
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
public class TeacherChatService {
    @Autowired
    private TeacherChatRepo teacherChatRepo;
    @Autowired
    private ModelMapper modelMapper;

    public int saveTeacherChat(TeacherChatDTO teacherChatDTO){
        if(teacherChatDTO.getSender().isEmpty() || teacherChatDTO.getReceiver().isEmpty() || teacherChatDTO.getMessage().isEmpty() || teacherChatDTO.getSender().equals(teacherChatDTO.getReceiver())){
            return 0;
        }else {
            TeacherChat teacherSavedChat =  teacherChatRepo.save(modelMapper.map(teacherChatDTO, TeacherChat.class));
            return teacherSavedChat.getChatId();
        }
    }
   public List<TeacherChatDTO> getChat(String sender){
        if(teacherChatRepo.existsTeacherChatBySender(sender)){
            List<TeacherChat> teacherChatList = teacherChatRepo.getTeacherChatBySender(sender);
            if(teacherChatList.isEmpty()){
                return null;
            }else {
                return modelMapper.map(teacherChatList,new TypeToken<ArrayList<TeacherChatDTO>>(){}.getType());
            }
        }else {
            return null;
        }
   }
   public String updateReadStatus(int chatId){
        if(teacherChatRepo.existsById(chatId)){
            TeacherChat teacherChat = teacherChatRepo.findTeacherChatByChatId(chatId);
            if(teacherChat.isReadStatus()){
                return varList.RSP_FAIL;
            }else {
                teacherChatRepo.updateStudentReadStatus(chatId);
                return varList.RSP_SUCCESS;
            }
        }else {
            return varList.RSP_TOKEN_INVALID;
        }
   }
   public String deleteChat(int chatId){
        if(teacherChatRepo.existsById(chatId)){
            teacherChatRepo.deleteTeacherChatByChatId(chatId);
            return varList.RSP_SUCCESS;
        }else {
            return varList.RSP_NO_DATA_FOUND;
        }
   }
}
