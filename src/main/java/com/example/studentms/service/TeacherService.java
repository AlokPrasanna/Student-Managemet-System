package com.example.studentms.service;

import com.example.studentms.dto.StudentDTO;
import com.example.studentms.dto.TeacherDTO;
import com.example.studentms.entity.Teacher;
import com.example.studentms.repo.TeacherRepo;
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
public class TeacherService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TeacherRepo teacherRepo;

    public String teacherRegistration(TeacherDTO teacherDTO){
        if(teacherRepo.existsById(teacherDTO.getTeacherId())){
            return varList.RSP_DUPLICATED;
        }else {
            Teacher teacher = teacherRepo.save(modelMapper.map(teacherDTO, Teacher.class));
            return varList.RSP_SUCCESS;
        }
    }
    public String teacherLogin(String teacherId, String password){
        if(teacherRepo.existsById(teacherId)){
            // Id exist
            Teacher teacher = teacherRepo.findById(teacherId).orElse(null);
            if(password.equals(teacher.getPassword())){
                // password correct
                if(teacher.isAccessibility()){
                    // accessibility true
                    return varList.RSP_SUCCESS;
                }
                else {
                    //cant access
                    return varList.RSP_FAIL;
                }

            }else{
                // wrong password
                return varList.RSP_NOT_AUTHORISED;
            }

        }else{
            // Teacher not register
            return varList.RSP_NO_DATA_FOUND;
        }
    }
    public TeacherDTO getTeacherDetails(String teacherId){
        if(teacherRepo.existsById(teacherId)){
            Teacher teacher = teacherRepo.findById(teacherId).orElse(null);
            return modelMapper.map(teacher, TeacherDTO.class);
        }else {
            return null;
        }
    }

    public String updateTeacherDetails(TeacherDTO teacherDTO){
        if(teacherRepo.existsById(teacherDTO.getTeacherId())){
            Teacher teacher = teacherRepo.save(modelMapper.map(teacherDTO, Teacher.class));
            return varList.RSP_SUCCESS;
        }else{
            return varList.RSP_NO_DATA_FOUND;
        }
    }
    public String deleteTeacher(String teacherId){
        if(teacherRepo.existsById(teacherId)){
            teacherRepo.deleteById(teacherId);
            return varList.RSP_SUCCESS;
        }else {
            return  varList.RSP_NO_DATA_FOUND;
        }
    }
    public List<TeacherDTO> getAllNotAppeovedTeachers(){
        List<Teacher> teacherList = teacherRepo.findAll();
        teacherList.removeIf(Teacher::isAccessibility);
        if(teacherList.isEmpty()){
            return null;
        }else{
            return modelMapper.map(teacherList,new TypeToken<ArrayList<TeacherDTO>>(){
            }.getType());
        }
    }
    public List<TeacherDTO> getAllTeachers(){
        List<Teacher> teacherList = teacherRepo.findAll();
        if(teacherList.isEmpty()){
            return null;
        }else {
            return modelMapper.map(teacherList,new TypeToken<ArrayList<TeacherDTO>>(){
            }.getType());
        }
    }
}
