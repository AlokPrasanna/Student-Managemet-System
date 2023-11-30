package com.example.studentms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class WebController {

    @GetMapping("/studentms")
    public String indexPage(){
        return "Index";
    }

    @GetMapping("/studentms/registerStudent")
    public String studentRegisterPage(){
        return "StudentRegister";
    }

    @GetMapping("/studentms/loginStudent")
    public String studentLoginPage(){
        return "StudentLogin";
    }

    @GetMapping("/studentms/notApproved")
    public String studentNotApprovedPage(){
        return "NotApproved";
    }

    @GetMapping("/studentms/StudentDashboard")
    public String studentDashboard(){
        return "StudentDashboard";
    }

    @GetMapping("/studentms/viewStudentProfile")
    public  String ViewStudentProfile(){
        return "ViewStudentProfile";
    }

    @GetMapping("/studentms/updateStudentProfile")
    public String updateStudentProfile(){
        return "StudentUpdateDetails";
    }

    @GetMapping("/studentms/adminLogin")
    public String adminLogin(){
        return "AdminLogin";
    }

    @GetMapping("/studentms/adminDashboard")
    public String adminDashboard(){
        return "AdminDashboard";
    }

    @GetMapping("/studentms/admin/setStudentAccess")
    public String setStudentAccess(){
        return "SetStudentAccess";
    }

    @GetMapping("/studentms/admin/deleteStudent")
    public String deleteStudent(){
        return "DeleteStudent";
    }

    @GetMapping("/studentms/admin/getStudentDetails")
    public String getStudentDetails(){
        return "GetStudentDetails";
    }

    @GetMapping("/studentms/admin/editAnnouncements")
    public String editAnnouncements(){
        return "EditAnnouncement";
    }

    @GetMapping("/studentms/admin/deleteAnnouncements")
    public String deleteAnnouncement(){
        return "DeleteAnnouncement";
    }

    @GetMapping("/studentms/admin/getAllStudents")
    public String getAllStudents(){
        return "GetAllStudents";
    }

    @GetMapping("/studentms/getStudentAnnouncements")
    public String getstudentAnnouncements(){
        return "StudentAnnouncements";
    }

    @GetMapping("/studentms/teacherRegistration")
    public String teacherRegistration(){
        return "TeacherRegistration";
    }

    @GetMapping("/studentms/teacherLogin")
    public String teacherLogin(){
        return "TeacherLogin";
    }

    @GetMapping("/studentms/teacherNotApproved")
    public String teacherNotApproved(){
        return "TeacherNotApproved";
    }

    @GetMapping("/studentms/teacherDashboard")
    public String teacherDashboard(){
        return "TeacherDashboard";
    }

    @GetMapping("/studentms/teacherAnnouncements")
    public String teacherAnnouncemet(){ return "TeacherAnnouncements";}

    @GetMapping("/studentms/setTimetableData")
    public String setTimetabeData(){
        return "SetTimetableData";
    }

    @GetMapping("/studentms/teacherTimetable")
    public String teacherTimetabe(){
        return "TeacherTimetable";
    }

    @GetMapping("/studentms/studentTimetable")
    public String studentTimetable(){
        return "StudentTimetable";
    }

    @GetMapping("/studentms/teacherInboxPage")
    public String teacherInboxPage(){
        return "TeacherInboxPage";
    }

    @GetMapping("/studentms/studentInboxPage")
    public String studentInboxPage(){
        return "StudentInboxPage";
    }

    @GetMapping("/studentms/StudentMessageHistorypage")
    public String studentMessageHistoryPage(){
        return "StudentMessageHistory";
    }

    @GetMapping("/studentms/TeacherMessgeHistorypage")
    public String teacherMessageHistoryPage(){
        return "TeacherMessageHistory";
    }

    @GetMapping("/studentms/viewTeacherProfile")
    public String viewTeacherProfile(){
        return "ViewTeacherProfile";
    }

    @GetMapping("/studentms/updateTeacherProfile")
    public String updateTeacherProfile(){
        return "TeacherUpdateDetails";
    }

    @GetMapping("/studentms/admin/setTeacherAccess")
    public String giveTeacherAccess(){
        return "setTeacherAccess";
    }

    @GetMapping("/studentms/admin/deleteTeacher")
    public String deleteTeacher(){
        return "DeleteTeacher";
    }

    @GetMapping("/studentms/admin/getTeacherDetails")
    public  String getTeacherDetails(){
        return "GetTeacherDetails";
    }

    @GetMapping("/studentms/admin/getAllTeachers")
    public String getAllteachers(){
        return "GetAllTeachers";
    }

    @GetMapping("/studentms/admin/viewAdminProfile")
    public String viewAdminProfile(){
        return "ViewAdminprofile";
    }

    @GetMapping("/studentms/admin/register")
    public String registerAdmin(){
        return  "AdminRegister";
    }

    @GetMapping("/studentms/admin/manage")
    public String adminManagement(){
        return "AdminManagement";
    }

    @GetMapping("/studentms/admin/updateDetails")
    public String updateAdminDetails(){
        return "AdminUpdateDetails";
    }

    @GetMapping("/studentms/admin/NotApproved")
    public String adminNotApproved(){
        return "AdminNotApproved";
    }

}
