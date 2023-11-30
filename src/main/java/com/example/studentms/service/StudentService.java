package com.example.studentms.service;

import com.example.studentms.dto.StudentDTO;
import com.example.studentms.entity.Student;
import com.example.studentms.repo.StudentRepo;
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
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ModelMapper modelMapper;

    public String registerStudent(StudentDTO studentDTO){
        if(studentRepo.existsById(studentDTO.getStudent_id())){
           return varList.RSP_DUPLICATED;
        }else{
            studentRepo.save(modelMapper.map(studentDTO, Student.class));
            return varList.RSP_SUCCESS;
        }
    }
    public String loginStudent(String studentId,String studentPassword){
        if(studentRepo.existsById(studentId)){
            //student in the system
            Student student = studentRepo.findById(studentId).orElse(null);
            if(studentPassword.equals(student.getPassword())){
                if(student.isAccessibility()){
                    // Student can access system and password matched
                    return varList.RSP_SUCCESS;
                }else{
                    //student cant access system but password matched
                    return varList.RSP_TOKEN_INVALID;
                }
            }else{
                // student in the system but password invalid
                return varList.RSP_NOT_AUTHORISED;
            }
        }else{
            // student not in the system
            return varList.RSP_NO_DATA_FOUND;
        }
    }
    public String updateStudent(StudentDTO studentDTO){
        if(studentRepo.existsById(studentDTO.getStudent_id())){
            studentRepo.save(modelMapper.map(studentDTO, Student.class));
            return varList.RSP_SUCCESS;
        }else {
            return varList.RSP_NO_DATA_FOUND;
        }
    }
    public StudentDTO searchStudent(String student_id){
        if (studentRepo.existsById(student_id)) {
             Student student = studentRepo.findById(student_id).orElse(null);
             return modelMapper.map(student, StudentDTO.class);
        } else {
            return null;
        }
    }

    public StudentDTO getStudentDetails(String studentId){
        if(studentRepo.existsById(studentId)){
            Student student = studentRepo.findById(studentId).orElse(null);
            return modelMapper.map(student, StudentDTO.class);
        }else {
            return null;
        }

    }

    public List<StudentDTO> getAllNotApprovedStudents(){
        List<Student> studentList = studentRepo.findAll();
        /*for(Student student : studentList){
            if(student.isAccessibility()){
                studentList.remove(student);
            }
        }*/
        studentList.removeIf(Student::isAccessibility);

        return modelMapper.map(studentList,new TypeToken<ArrayList<StudentDTO>>(){
        }.getType());
    }

    public String deleteStudent(StudentDTO studentDTO){

            if (studentRepo.existsById(studentDTO.getStudent_id())) {
                studentRepo.deleteById(studentDTO.getStudent_id());
                return varList.RSP_SUCCESS;
            } else {
                return varList.RSP_NO_DATA_FOUND;
            }
    }

    public List<StudentDTO> getAllStudents(){
        List<Student> allStudent = studentRepo.findAll();
        if(allStudent.isEmpty()){
            return null;
        }
        else {
            return modelMapper.map(allStudent,new TypeToken<ArrayList<StudentDTO>>(){
            }.getType());
        }
    }
    public List<StudentDTO> getStudentListByGradeAndClass(String grade , String classRoom){
        List<Student> studentList = studentRepo.findStudentsByGradeAndStudentClass(grade,classRoom);
        if(studentList.isEmpty()){
            return null;
        }else {
            return modelMapper.map(studentList,new TypeToken<ArrayList<StudentDTO>>(){}.getType());
        }
    }

}
