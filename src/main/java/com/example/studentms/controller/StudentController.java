package com.example.studentms.controller;

import com.example.studentms.dto.ResponseDTO;
import com.example.studentms.dto.StudentDTO;
import com.example.studentms.service.StudentService;
import com.example.studentms.util.varList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/s1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/reg")
    public ResponseEntity<ResponseDTO> registerStudent(@RequestBody StudentDTO studentDTO){
     try {
         String res = studentService.registerStudent(studentDTO);
         if (res.equals("00")){
           responseDTO.setCode(varList.RSP_SUCCESS);
           responseDTO.setMessage("Registration Success");
           responseDTO.setContent(studentDTO);
           return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
         }else if(res.equals("06")) {
             responseDTO.setCode(varList.RSP_DUPLICATED);
             responseDTO.setMessage("Student Already Registered");
             responseDTO.setContent(studentDTO);
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
         return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
     }
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO>loginStudent(@RequestBody StudentDTO studentDTO){
        try {
            String res = studentService.loginStudent(studentDTO.getStudent_id(),studentDTO.getPassword());
            if (res.equals("00")){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else if(res.equals("04")) {
                responseDTO.setCode(varList.RSP_TOKEN_INVALID);
                responseDTO.setMessage("Account has not approved");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }else if(res.equals("02")){
                responseDTO.setCode(varList.RSP_NOT_AUTHORISED);
                responseDTO.setMessage("Invalid Password!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }else if(res.equals("01")){
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not Found Data");
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
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateStudent(@RequestBody StudentDTO studentDTO){
        try {
            String res = studentService.updateStudent(studentDTO);
            if (res.equals("00")){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not Updated!");
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
    @GetMapping("/searchStudent/{Id}")
    public ResponseEntity<ResponseDTO> searchStudent(@PathVariable String Id){
        try{
            StudentDTO studentDTO = studentService.searchStudent(Id);
            if(studentDTO != null){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Student Not Found");
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
    @GetMapping("/getStudentDetails/{Id}")
    public ResponseEntity<ResponseDTO> getStudentDetails(@PathVariable String Id){
        try{
            StudentDTO studentDTO = studentService.getStudentDetails(Id);
            if(studentDTO != null) {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }
            else{
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Student Not Found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }
        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllNotApprovedStudents")
    public ResponseEntity<ResponseDTO> getAllNotApprovedStudents(){
        try{
            List<StudentDTO> studentDTOList = studentService.getAllNotApprovedStudents();
            if(studentDTOList != null) {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(studentDTOList);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not Students Register");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/deleteStudent")
    public ResponseEntity<ResponseDTO> deleteStudent(@RequestBody StudentDTO studentDTO){
        try{
            String res = studentService.deleteStudent(studentDTO);
            if(res.equals("00")) {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Delete Success!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not Students Register");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<ResponseDTO> getAllStudents(){
        try{
            List<StudentDTO> allStudents = studentService.getAllStudents();
            if(allStudents == null){
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not Students Register");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            }else {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success!");
                responseDTO.setContent(allStudents);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getStudents/grade={grade}/classRoom={classRoom}")
    public ResponseEntity<ResponseDTO> getStudentsByGradeAndClass(@PathVariable String grade, @PathVariable String classRoom){
        try{
            List<StudentDTO> allStudents = studentService.getStudentListByGradeAndClass(grade,classRoom);
            if(allStudents == null){
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not Students Register");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }else {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success!");
                responseDTO.setContent(allStudents);
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
