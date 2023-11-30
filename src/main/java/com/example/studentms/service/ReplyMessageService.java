package com.example.studentms.service;

import com.example.studentms.dto.ReplyMessageDTO;
import com.example.studentms.dto.StudentChatDTO;
import com.example.studentms.entity.ReplyMessage;
import com.example.studentms.entity.StudentChat;
import com.example.studentms.repo.ReplyMessageRepo;
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
public class ReplyMessageService {
    @Autowired
    private ReplyMessageRepo replyMessageRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveReply(ReplyMessageDTO replyMessageDTO){
        if(replyMessageDTO.getSender().isEmpty() || replyMessageDTO.getSender().isEmpty() || replyMessageDTO.getMessage().isEmpty() || replyMessageDTO.getSender().equals(replyMessageDTO.getReceiver())){
            return  varList.RSP_TOKEN_INVALID;
        }else {
            replyMessageRepo.save(modelMapper.map(replyMessageDTO, ReplyMessage.class));
            return varList.RSP_SUCCESS;
        }
    }
    public List<ReplyMessageDTO> getChat(String receiver){
        if(replyMessageRepo.existsReplyMessageByReceiver(receiver)){
            List<ReplyMessage> replyMessageList = replyMessageRepo.getReplyMessageByReceiver(receiver);
            if(replyMessageList.isEmpty()){
                return null;
            }else {
                return  modelMapper.map(replyMessageList,new TypeToken<ArrayList<ReplyMessageDTO>>(){}.getType());
            }
        }else{
            return null;
        }
    }

    /*public String saveReply(ReplyMessageDTO replyMessageDTO){
        if (replyMessageDTO.getStudentId() == null || replyMessageDTO.getTeacherId() == null || replyMessageDTO.getTeacherId().isEmpty() || replyMessageDTO.getStudentId().isEmpty()) {
            return varList.RSP_TOKEN_INVALID;
        } else {
            Integer teacherTableChatId = replyMessageDTO.getTeacherTableChatId();
            Integer studentTableChatId = replyMessageDTO.getStudentTableChatId();

            if(teacherTableChatId == null && studentTableChatId == null){
                return varList.RSP_TOKEN_INVALID;
            }else{
                if(studentTableChatId != null){
                    boolean val = replyMessageRepo.existsReplyMessageByStudentTableChatId(studentTableChatId);
                    if(val){
                        return  varList.RSP_DUPLICATED;
                    }else {
                        ReplyMessage replyMessage = modelMapper.map(replyMessageDTO, ReplyMessage.class);
                        replyMessageRepo.save(replyMessage);
                        return varList.RSP_SUCCESS;
                    }
                }else {
                    boolean val = replyMessageRepo.existsReplyMessageByTeacherTableChatId(teacherTableChatId);
                    if(val){
                        return  varList.RSP_DUPLICATED;
                    }else {
                        ReplyMessage replyMessage = modelMapper.map(replyMessageDTO, ReplyMessage.class);
                        replyMessageRepo.save(replyMessage);
                        return varList.RSP_SUCCESS;
                    }
                }
            }


        }
    }
    public List<ReplyMessageDTO> getReplyMessageForStudent(String studentId){
        if(replyMessageRepo.existsReplyMessageByStudentId(studentId)){
            List<ReplyMessage> replyMessageList = replyMessageRepo.getReplyMessageByStudentId(studentId);
            return modelMapper.map(replyMessageList, new TypeToken<ArrayList<ReplyMessageDTO>>(){}.getType());
        }else {
            return null;
        }
    }
    public List<ReplyMessageDTO> getReplyMessageForTeacher(String teacherId){
        if(replyMessageRepo.existsReplyMessageByTeacherId(teacherId)){
            List<ReplyMessage> replyMessageList = replyMessageRepo.getReplyMessageByTeacherId(teacherId);
            return modelMapper.map(replyMessageList, new TypeToken<ArrayList<ReplyMessageDTO>>(){}.getType());
        }else {
            return null;
        }
    }

    public String updateReplyMessage(ReplyMessageDTO replyMessageDTO){
        if(replyMessageRepo.existsById(replyMessageDTO.getChatId())){
            ReplyMessage replyMessage = replyMessageRepo.getReplyMessageByChatId(replyMessageDTO.getChatId());
            if(replyMessage.isReadStatus()){
                return varList.RSP_TOKEN_EXPIRED;
            }else {
                replyMessageRepo.save(modelMapper.map(replyMessageDTO, ReplyMessage.class));
                return varList.RSP_SUCCESS;
            }
        }else{
             return varList.RSP_NO_DATA_FOUND;
        }
    }*/

}
