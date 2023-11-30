package com.example.studentms.controller;

import com.example.studentms.dto.AnnouncementDTO;
import com.example.studentms.dto.ResponseDTO;
import com.example.studentms.service.AnnouncementService;
import com.example.studentms.util.varList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("api/aa/announcement")
public class AnnouncementController {
    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private AnnouncementService announcementService;

    @PostMapping("/postAnnouncement")
    public ResponseEntity<ResponseDTO> saveAnnouncement(@RequestBody AnnouncementDTO announcementDTO) {
        try {
            String res = announcementService.saveAnnouncement(announcementDTO);
            if (res.equals("00")) {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Announcement Create Success");
                responseDTO.setContent(announcementDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("04")) {
                responseDTO.setCode(varList.RSP_TOKEN_INVALID);
                responseDTO.setMessage("Can't Accept");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NO_CONTENT);
            }else {
                responseDTO.setCode(varList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception exception) {
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAnnouncements")
    public ResponseEntity<ResponseDTO> getAnnouncement(){
        try{
            List<AnnouncementDTO> announcementDTOList = announcementService.getAnnouncements();
            if(announcementDTOList.isEmpty()){
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not Announcements Posted");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }
            else{
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success!");
                responseDTO.setContent(announcementDTOList);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/editAnnouncement")
    public ResponseEntity<ResponseDTO> editeAnnouncement(@RequestBody AnnouncementDTO announcementDTO){
        try {
            String res = announcementService.editeAnnouncement(announcementDTO);
            if(res.equals("00")){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("04")) {
                responseDTO.setCode(varList.RSP_TOKEN_INVALID);
                responseDTO.setMessage("There is no announcement change.");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }else{
                responseDTO.setCode(varList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAnnouncement/{Id}")
    public ResponseEntity<ResponseDTO> deleteAnnouncement(@PathVariable int Id){
        try {
            String res = announcementService.deleteAnnouncement(Id);
            if(res.equals("00")){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Announcement Delete Success!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else if(res.equals("01")){
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("This announce not in the database");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }else {
                responseDTO.setCode(varList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getStudentAnnouncements")
    public ResponseEntity<ResponseDTO> getStudentAnnouncement(){
        try {
            List<AnnouncementDTO> announcementDTOList = announcementService.getStudentAnnouncements();
            if(announcementDTOList == null){
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No announcements for Students yet");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }else {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(announcementDTOList);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTeacherAnnouncements")
    public ResponseEntity<ResponseDTO> getTeacherAnnouncement(){
        try {
            List<AnnouncementDTO> announcementDTOList = announcementService.getTeacherAnnouncements();
            if(announcementDTOList == null){
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No announcements for Students yet");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }else {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(announcementDTOList);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
