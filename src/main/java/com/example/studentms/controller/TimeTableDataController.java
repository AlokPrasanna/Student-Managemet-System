package com.example.studentms.controller;

import com.example.studentms.dto.ResponseDTO;
import com.example.studentms.dto.TimeTableDataDTO;
import com.example.studentms.service.TimeTableDataService;
import com.example.studentms.util.varList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/td/timetableData")
public class TimeTableDataController {

    @Autowired
    private TimeTableDataService timeTableDataService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/saveTimetableData")
    public ResponseEntity<ResponseDTO> saveTimeTableData(@RequestBody TimeTableDataDTO timeTableDataDTO){
        try {
            String res = timeTableDataService.saveTimeTableData(timeTableDataDTO);
            if (res.equals("00")) {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("TimeTableData Save Success");
                responseDTO.setContent(timeTableDataDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("06")) {
                responseDTO.setCode(varList.RSP_DUPLICATED);
                responseDTO.setMessage("Already Filled That Time slot");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ALREADY_REPORTED);
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

    @PutMapping("/updateTimetabeData")
    public ResponseEntity<ResponseDTO> updateTimeTableData(@RequestBody TimeTableDataDTO timeTableDataDTO){
        try {
            String res = timeTableDataService.updateTimeTableData(timeTableDataDTO);
            if (res.equals("00")) {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("TimeTableData Save Success");
                responseDTO.setContent(timeTableDataDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("04")) {
                responseDTO.setCode(varList.RSP_TOKEN_INVALID);
                responseDTO.setMessage("Can't Accept");
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

    @DeleteMapping("/deleteTimetableData/{rowId}")
    public ResponseEntity<ResponseDTO> deleteTimeTableData(@PathVariable int rowId){
        try {
            String res = timeTableDataService.deleteTimeTableData(rowId);
            if(res.equals("00")){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Timetable Data Delete Success!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else if(res.equals("01")){
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("This data not in the database");
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

    @GetMapping("/getAllTimetableData")
    public ResponseEntity<ResponseDTO> getAllTimeTableData() {
        try {
            List<TimeTableDataDTO> timeTableDataDTOList = timeTableDataService.getAllTimetableData();
            if (timeTableDataDTOList == null) {
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Data Found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            } else {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success!");
                responseDTO.setContent(timeTableDataDTOList);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }

        } catch (Exception exception) {
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getTeacherTimetableData/{teacherId}")
    public ResponseEntity<ResponseDTO> getTeacherTimeTableData(@PathVariable String teacherId){
        try {
            List<TimeTableDataDTO> timeTableDataDTOList = timeTableDataService.teacherTimetableData(teacherId);
            if(timeTableDataDTOList == null){
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Data Found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            }else {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success!");
                responseDTO.setContent(timeTableDataDTOList);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getStudentTimetableData/{grade}/{classRoom}")
    public ResponseEntity<ResponseDTO> getStudentTimetableData(@PathVariable String grade, @PathVariable String classRoom){
        try{
            List<TimeTableDataDTO> timeTableDataDTOList = timeTableDataService.studentTimetabeData(grade,classRoom);
            if(timeTableDataDTOList == null){
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Data Found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            }else {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success!");
                responseDTO.setContent(timeTableDataDTOList);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTeacherIdByTimetableData/{subject}/{grade}/{classRoom}")
    public ResponseEntity<ResponseDTO> getTeacherId(@PathVariable String subject, @PathVariable String grade, @PathVariable String classRoom){
        try{
            String teacherId = timeTableDataService.getTeacherIdBySubjectAndClassRoomAndGrade(subject,grade,classRoom);
            if(teacherId.equals("0")){
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Invalid Inputs!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }else {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success!");
                responseDTO.setContent(teacherId);
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
