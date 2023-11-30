package com.example.studentms.controller;

import com.example.studentms.dto.AdminDTO;
import com.example.studentms.dto.ResponseDTO;
import com.example.studentms.dto.StudentDTO;
import com.example.studentms.service.AdminService;
import com.example.studentms.util.varList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/a1/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/reg")
    public ResponseEntity<ResponseDTO> adminRegister(@RequestBody AdminDTO adminDTO){
        try {
            String res = adminService.adminRegister(adminDTO);
            if (res.equals("00")){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Registration Success");
                responseDTO.setContent(adminDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else if(res.equals("06")) {
                responseDTO.setCode(varList.RSP_DUPLICATED);
                responseDTO.setMessage("Admin Already Registered");
                responseDTO.setContent(adminDTO);
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
    public ResponseEntity<ResponseDTO>loginStudent(@RequestBody AdminDTO adminDTO){
        try {
            String res = adminService.loginAdmin(adminDTO.getAdminId(),adminDTO.getPassword());
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
    @GetMapping("/getAdminById/{adminId}")
    public ResponseEntity<ResponseDTO> getAdminById(@PathVariable String adminId){
        try{
            AdminDTO adminDTO = adminService.getAdminById(adminId);
            if(adminDTO != null){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(adminDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not Found!");
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

    @GetMapping("/getAllAdmins")
    public ResponseEntity<ResponseDTO> getAllAdmins(){
        try{
            List<AdminDTO> adminDTOList = adminService.getAllAmins();
            if(adminDTOList != null){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(adminDTOList);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not Found!");
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

    @DeleteMapping("/{adminId}/deleteAdmin")
    public ResponseEntity<ResponseDTO> deleteAdmin(@PathVariable String adminId){
        try {
            String res = adminService.deleteAdmin(adminId);
            if(res.equals("00")){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Admin Delete Success");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else if(res.equals("01")){
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not Found Admin");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            }else {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Something went wrong!");
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

    @PutMapping("/{adminId}/giveAdminAccess")
    public ResponseEntity<ResponseDTO> giveAdminAccessToSystem(@PathVariable String adminId){
        try {
            String res = adminService.giveAccessToSystem(adminId);
            if(res.equals("00")){
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Now Admin Can Access The System! ");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else if(res.equals("07")){
                responseDTO.setCode(varList.RSP_FAIL);
                responseDTO.setMessage("Already Can Access!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }else if(res.equals("01")){
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Admin Register By That Id!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }else{
                responseDTO.setCode(varList.RSP_ERROR);
                responseDTO.setMessage("Something Went Wrong!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch (Exception exception){
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateAdminDetails")
    public ResponseEntity<ResponseDTO> updateAdminDetails(@RequestBody AdminDTO adminDTO) {
        try {
            String res = adminService.updateAdmin(adminDTO);
            if (res.equals("00")) {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Update Admin Details Success!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("01")) {
                responseDTO.setCode(varList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not Found Admin");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            } else {
                responseDTO.setCode(varList.RSP_SUCCESS);
                responseDTO.setMessage("Something went wrong!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }
        } catch (Exception exception) {
            responseDTO.setCode(varList.RSP_ERROR);
            responseDTO.setMessage(exception.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
