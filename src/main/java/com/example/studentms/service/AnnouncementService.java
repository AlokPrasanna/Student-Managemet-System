package com.example.studentms.service;

import com.example.studentms.dto.AnnouncementDTO;
import com.example.studentms.entity.Announcement;
import com.example.studentms.repo.AnnouncementRepo;
import com.example.studentms.util.varList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Transactional
public class AnnouncementService {
    @Autowired
    private AnnouncementRepo announcementRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveAnnouncement (AnnouncementDTO announcementDTO){
       if(announcementDTO.getAnnouncement()!=null && announcementDTO.getAdminId() != null && announcementDTO.getAudience() !=null){
           announcementRepo.save(modelMapper.map(announcementDTO, Announcement.class));
           return varList.RSP_SUCCESS;
       }
       else{
           return varList.RSP_TOKEN_INVALID;
       }
    }

    public List<AnnouncementDTO> getAnnouncements(){
        List<Announcement> announcements = announcementRepo.findAll();
        if(announcements.isEmpty()){
            return null;
        }else {
            return modelMapper.map(announcements,new TypeToken<ArrayList<AnnouncementDTO>>(){
            }.getType());
        }
    }
    public String editeAnnouncement(AnnouncementDTO announcementDTO){
        if(announcementRepo.existsById(announcementDTO.getAnnouncementId())){
            announcementRepo.save(modelMapper.map(announcementDTO, Announcement.class));
            return varList.RSP_SUCCESS;
        }
        else{
            return varList.RSP_TOKEN_INVALID;
        }
    }
    public String deleteAnnouncement(int announcementId){
        if(announcementRepo.existsById(announcementId)){
            announcementRepo.deleteById(announcementId);
            return varList.RSP_SUCCESS;
        }
        else {
            return varList.RSP_NO_DATA_FOUND;
        }
    }
    public List<AnnouncementDTO> getStudentAnnouncements(){
        List<Announcement> announcementList = announcementRepo.findByAudienceIn(Arrays.asList("StudentOnly","Both"));
        if(announcementList.isEmpty()){
            return null;
        }else {
            return modelMapper.map(announcementList,new TypeToken<ArrayList<AnnouncementDTO>>(){
            }.getType());
        }
    }

    public List<AnnouncementDTO> getTeacherAnnouncements(){
        List<Announcement> announcementList = announcementRepo.findByAudienceIn(Arrays.asList("TeacherOnly","Both"));
        if(announcementList.isEmpty()){
            return null;
        }else {
            return modelMapper.map(announcementList, new TypeToken<ArrayList<AnnouncementDTO>>(){
            }.getType());
        }
    }
}
