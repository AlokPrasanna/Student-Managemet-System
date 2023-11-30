package com.example.studentms.repo;

import com.example.studentms.entity.TimeTableData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeTableDataRepo extends JpaRepository<TimeTableData,Integer> {
    List<TimeTableData>  findAllByTeacherId(String teacherId);
    List<TimeTableData> findAllByGradeAndClassRoom(String grade , String classRoom);
    boolean existsTimeTableDataByDayAndTimeAndGradeAndClassRoom(String day,String time, String grade , String classRoom);

    boolean existsTimeTableDataByTeacherIdAndDayAndTime(String teacherId, String day, String time);

    boolean existsTimeTableDataBySubjectAndGradeAndClassRoom(String subject,String grade, String classRoom);
    List<TimeTableData> findAllBySubjectAndGradeAndClassRoom( String subject,String grade, String classRoom);
}
