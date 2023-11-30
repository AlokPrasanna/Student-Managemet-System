package com.example.studentms.repo;

import com.example.studentms.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface AnnouncementRepo extends JpaRepository<Announcement,Integer> {
    //boolean existsByAnnouncementIsNotNull();

    List<Announcement> findByAudienceIn(List<String> audience);
}
