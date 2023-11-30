package com.example.studentms.service;

import com.example.studentms.dto.TimeTableDataDTO;
import com.example.studentms.entity.TimeTableData;
import com.example.studentms.repo.TimeTableDataRepo;
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
public class TimeTableDataService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TimeTableDataRepo timeTableDataRepo;

    public String saveTimeTableData(TimeTableDataDTO timeTableDataDTO){
        if(timeTableDataRepo.existsTimeTableDataByDayAndTimeAndGradeAndClassRoom(timeTableDataDTO.getDay(),timeTableDataDTO.getTime(),timeTableDataDTO.getGrade(),timeTableDataDTO.getClassRoom()) || timeTableDataRepo.existsTimeTableDataByTeacherIdAndDayAndTime(timeTableDataDTO.getTeacherId(),timeTableDataDTO.getDay(),timeTableDataDTO.getTime())){
                return varList.RSP_DUPLICATED;

        }else {
            timeTableDataRepo.save(modelMapper.map(timeTableDataDTO, TimeTableData.class));
            return varList.RSP_SUCCESS;
        }
    }
    public String updateTimeTableData(TimeTableDataDTO timeTableDataDTO){
        if(timeTableDataRepo.existsById(timeTableDataDTO.getRowId())){
            timeTableDataRepo.save(modelMapper.map(timeTableDataDTO, TimeTableData.class));
            return varList.RSP_SUCCESS;
        }else{
            return varList.RSP_TOKEN_INVALID;
        }
    }
    public String deleteTimeTableData(int rowId){
        if(timeTableDataRepo.existsById(rowId)){
            timeTableDataRepo.deleteById(rowId);
            return varList.RSP_SUCCESS;
        }else {
            return varList.RSP_NO_DATA_FOUND;
        }
    }
    public List<TimeTableDataDTO> getAllTimetableData(){
        List<TimeTableData> timeTableDataList = timeTableDataRepo.findAll();
        if(timeTableDataList.isEmpty()){
            return null;
        }else{
            return modelMapper.map(timeTableDataList, new TypeToken<ArrayList<TimeTableDataDTO>>(){}.getType());
        }
    }
    public List<TimeTableDataDTO> teacherTimetableData(String teacherId){
        List<TimeTableData> timeTableDataList = timeTableDataRepo.findAllByTeacherId(teacherId);
        if(timeTableDataList.isEmpty()){
            return  null;
        }else {
            return modelMapper.map(timeTableDataList, new TypeToken<ArrayList<TimeTableDataDTO>>(){}.getType());
        }
    }
    public  List<TimeTableDataDTO> studentTimetabeData(String grade, String classRoom){
        List<TimeTableData> timeTableDataList = timeTableDataRepo.findAllByGradeAndClassRoom(grade,classRoom);
        if(timeTableDataList.isEmpty()){
            return null;
        }else {
            return modelMapper.map(timeTableDataList, new TypeToken<ArrayList<TimeTableDataDTO>>(){}.getType());
        }
    }
    public String getTeacherIdBySubjectAndClassRoomAndGrade(String subject, String grade,String classRoom){
        if(timeTableDataRepo.existsTimeTableDataBySubjectAndGradeAndClassRoom(subject,grade,classRoom)){
            List<TimeTableData> timeTableDataList = timeTableDataRepo.findAllBySubjectAndGradeAndClassRoom(subject,grade,classRoom);
            return timeTableDataList.get(0).getTeacherId();
        }else {
            return "0";
        }
    }
}
