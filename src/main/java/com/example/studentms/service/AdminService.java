package com.example.studentms.service;

import com.example.studentms.dto.AdminDTO;
import com.example.studentms.entity.Admin;
import com.example.studentms.entity.Student;
import com.example.studentms.repo.AdminRepo;
import com.example.studentms.util.varList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String adminRegister(AdminDTO adminDTO){
        if(adminRepo.existsById(adminDTO.getAdminId())){
            return varList.RSP_DUPLICATED;
        }
        else {
            adminRepo.save(modelMapper.map(adminDTO, Admin.class));
            return varList.RSP_SUCCESS;
        }
    }

    public String loginAdmin(String adminId,String adminPassword){
        if(adminRepo.existsById(adminId)){
            //student in the system
            Admin admin = adminRepo.findById(adminId).orElse(null);
            if(adminPassword.equals(admin.getPassword())){
                if(admin.isAccessibility()){
                    // Admin can access system and password matched
                    return varList.RSP_SUCCESS;
                }else{
                    //Admin cant access system but password matched
                    return varList.RSP_TOKEN_INVALID;
                }
            }else{
                // Admin in the system but password invalid
                return varList.RSP_NOT_AUTHORISED;
            }
        }else{
            // Admin not in the system
            return varList.RSP_NO_DATA_FOUND;
        }
    }

    public AdminDTO getAdminById(String adminId){
        if(adminRepo.existsById(adminId)){
            Optional<Admin> admin = adminRepo.findById(adminId);
            return modelMapper.map(admin, AdminDTO.class);

        }else{
            return null;
        }
    }
    public List<AdminDTO> getAllAmins(){
        List<Admin> adminList = adminRepo.findAll();
        if(adminList.isEmpty()){
            return null;
        }else{
            return modelMapper.map(adminList,new TypeToken<ArrayList<AdminDTO>>(){}.getType());
        }
    }
    public String deleteAdmin(String adminId){
        if(adminRepo.existsById(adminId)){
            adminRepo.deleteById(adminId);
            return varList.RSP_SUCCESS;
        }else {
            return varList.RSP_NO_DATA_FOUND;
        }
    }
    public String giveAccessToSystem(String adminId){
        if(adminRepo.existsById(adminId)){
            Admin admin = adminRepo.findAdminByAdminId(adminId);
            if(admin.isAccessibility()){
                return varList.RSP_FAIL;
            }else {
                adminRepo.updateAccessibilityByAdminId(adminId);
                return varList.RSP_SUCCESS;
            }
        }else {
            return varList.RSP_NO_DATA_FOUND;
        }
    }
    public  String updateAdmin(AdminDTO adminDTO){
        if(adminRepo.existsById(adminDTO.getAdminId())){
           adminRepo.save(modelMapper.map(adminDTO, Admin.class));
           return varList.RSP_SUCCESS;
        }else {
            return varList.RSP_NO_DATA_FOUND;
        }
    }
}
