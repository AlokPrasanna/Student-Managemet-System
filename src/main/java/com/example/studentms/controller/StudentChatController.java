package com.example.studentms.controller;

import com.example.studentms.dto.ResponseDTO;
import com.example.studentms.dto.StudentChatDTO;
import com.example.studentms.service.StudentChatService;
import com.example.studentms.util.varList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/sc/studentChat")
public class StudentChatController {

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private StudentChatService studentChatService;

    @PostMapping("/saveStudentChat")
    public ResponseEntity<ResponseDTO> saveStudentChat(@RequestBody StudentChatDTO studentChatDTO){
        try {
            int res = studentChatService.saveStudentChat(studentChatDTO);
            if(res == 0){
                responseDTO.setCode(varList.RSP_TOKEN_INVALID);
                responseDTO.setMessage("Message Invalid!");
                responseDTO.setContent(res);
                return new ResponseEntity<>(responseDTO , HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            } else {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Message sent!");
                responseDTO.setContent(res);
                return new ResponseEntity<>(responseDTO , HttpStatus.ACCEPTED);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getstudentMsg/{sender}")
    public ResponseEntity<ResponseDTO> getStudentMessageByStudentId(@PathVariable String sender){
        try{
            List<StudentChatDTO> studentChatDTOList = studentChatService.getAllStudentMessage(sender);
            if(studentChatDTOList != null){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(studentChatDTOList);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Messages Empty");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/{chatId}/updateReadStatus")
    public ResponseEntity<ResponseDTO> updateReadStatus(@PathVariable int chatId){
        try{
            String res = studentChatService.updateReadStatus(chatId);
            if(res.equals("00")){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Read status updated");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO,HttpStatus.ACCEPTED);
            } else if (res.equals("04")) {
                responseDTO.setCode(varList.RSP_TOKEN_INVALID);
                responseDTO.setMessage("Chat Id not found!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO,HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }else if (res.equals("07")) {
                responseDTO.setCode(varList.RSP_FAIL);
                responseDTO.setMessage("Already Updated!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO,HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }else{
                responseDTO.setCode(varList.RSP_ERROR);
                responseDTO.setMessage("Something Went Wrong");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{chatId}/deleteStudentChat")
    public ResponseEntity<ResponseDTO> deleteChat(@PathVariable int chatId){
        try {
            String res = studentChatService.deleteChat(chatId);
            if(res.equals("00")){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Message Delete!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO , HttpStatus.ACCEPTED);
            } else if(res.equals("01")) {
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Message Cant Delete!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO , HttpStatus.NOT_FOUND);
            }else{
                responseDTO.setCode(varList.RSP_ERROR);
                responseDTO.setMessage("Something Went Wrong");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
