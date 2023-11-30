package com.example.studentms.controller;

import com.example.studentms.dto.ReplyMessageDTO;
import com.example.studentms.dto.ResponseDTO;
import com.example.studentms.service.ReplyMessageService;
import com.example.studentms.util.varList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rm/replyMessage")
public class ReplyMessageController {
    @Autowired
    private ResponseDTO responseDTO;
    @Autowired
    private ReplyMessageService replyMessageService;

    @PostMapping("/saveReplyMessage")
    public ResponseEntity<ResponseDTO> saveReplyMessage(@RequestBody ReplyMessageDTO replyMessageDTO){
        try{
            String res = replyMessageService.saveReply(replyMessageDTO);
            if(res.equals("00")){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Message Delivery!");
                responseDTO.setContent(replyMessageDTO);
                return  new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("04")) {
                responseDTO.setCode(varList.RSP_TOKEN_INVALID);
                responseDTO.setMessage("Message Not Delivery!");
                responseDTO.setContent(null);
                return  new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            } else {
                responseDTO.setCode(varList.RSP_ERROR);
                responseDTO.setMessage("Something Went Wrong!");
                responseDTO.setContent(null);
                return  new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{receiver}/getReplyMessage")
    public ResponseEntity<ResponseDTO> getChat(@PathVariable String receiver){
        try {
            List<ReplyMessageDTO> replyMessageDTOList = replyMessageService.getChat(receiver);
            if(replyMessageDTOList != null){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Message Delivery!");
                responseDTO.setContent(replyMessageDTOList);
                return  new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(varList.RSP_TOKEN_INVALID);
                responseDTO.setMessage("Message Not Yet!");
                responseDTO.setContent(null);
                return  new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

