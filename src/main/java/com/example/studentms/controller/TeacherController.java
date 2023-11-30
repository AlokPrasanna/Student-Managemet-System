package com.example.studentms.controller;

import com.example.studentms.dto.ResponseDTO;
import com.example.studentms.dto.TeacherDTO;
import com.example.studentms.service.TeacherService;
import com.example.studentms.util.varList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/t1/teacher")
@CrossOrigin
@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/teacherReg")
    public ResponseEntity<ResponseDTO> teacherRegistration(@RequestBody TeacherDTO teacherDTO){
        try {
            String res = teacherService.teacherRegistration(teacherDTO);
            if (res.equals("00")){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Registration Success!");
                responseDTO.setContent(teacherDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else if(res.equals("06")) {
                responseDTO.setCode(varList.RSP_DUPLICATED);
                responseDTO.setMessage("Teacher Already Registered");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ALREADY_REPORTED);
            }else if(res.equals("07")){
                responseDTO.setCode(varList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
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
            return new ResponseEntity<>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/teacherLogin")
    public ResponseEntity<ResponseDTO> teacherLogin(@RequestBody TeacherDTO teacherDTO){
        try {
            String res = teacherService.teacherLogin(teacherDTO.getTeacherId(),teacherDTO.getPassword());
            if(res.equals("00")){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Login Susses!");
                responseDTO.setContent(teacherDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("07")) {
                responseDTO.setCode(varList.RSP_FAIL);
                responseDTO.setMessage("Accounr Still Not Approved!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            } else if (res.equals("02")) {
                responseDTO.setCode(varList.RSP_NOT_AUTHORISED);
                responseDTO.setMessage("Invalid Password!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);

            } else if (res.equals("01")) {
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Data Not Found");
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
            return new ResponseEntity<>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTeacherDetails/{teacherId}")
    public ResponseEntity<ResponseDTO> getTeacherDetails(@PathVariable String teacherId){
        try {
            TeacherDTO teacherDTO = teacherService.getTeacherDetails(teacherId);
            if(teacherDTO != null) {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success!");
                responseDTO.setContent(teacherDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }
            else{
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not Found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/teacherUpdate")
    public ResponseEntity<ResponseDTO> updateTeacherDetails(@RequestBody TeacherDTO teacherDTO){
        try {
            String res = teacherService.updateTeacherDetails(teacherDTO);
             if(res.equals("00")){
                 responseDTO.setCode(varList.RSP_SUCCESS);
                 responseDTO.setMessage("Update Success");
                 responseDTO.setContent(teacherDTO);
                 return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
             }else {
                 responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                 responseDTO.setMessage("Not Found");
                 responseDTO.setContent(null);
                 return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
             }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/teacherDelete/{teacherId}")
    public ResponseEntity<ResponseDTO> deleteTeacher(@PathVariable String teacherId){
        try {
            String res = teacherService.deleteTeacher(teacherId);
            if(res.equals("00")){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Delete Success!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not Found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllNotApprovedTeachers")
    public ResponseEntity<ResponseDTO> getAllNotApprovedTeachers(){
        try{
            List<TeacherDTO> teacherDTOList = teacherService.getAllNotAppeovedTeachers();
            if(teacherDTOList != null){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success!");
                responseDTO.setContent(teacherDTOList);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not Found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllTeachers")
    public ResponseEntity<ResponseDTO> getAllTeachers(){
        try{
            List<TeacherDTO> teacherDTOList = teacherService.getAllTeachers();
            if(teacherDTOList != null){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success!");
                responseDTO.setContent(teacherDTOList);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not Found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
