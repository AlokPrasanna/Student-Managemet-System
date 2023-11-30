package com.example.studentms.service;

import com.example.studentms.entity.TermTestTimetable;
import com.example.studentms.repo.TermTestTimetableRepo;
import com.example.studentms.util.StoreFile;
import com.example.studentms.util.varList;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
public class TermTestTimetableService {

    @Autowired
    private TermTestTimetableRepo termTestTimetableRepo;

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return varList.RSP_TOKEN_INVALID;
        }else {
            if(termTestTimetableRepo.existsTermTestTimetableByName(multipartFile.getName())){
                return varList.RSP_DUPLICATED;
            }
            else {
                termTestTimetableRepo.save(TermTestTimetable.builder()
                        .name(multipartFile.getOriginalFilename())
                        .type(multipartFile.getContentType())
                        .fileData(StoreFile.compressFile(multipartFile.getBytes())).build());
                return varList.RSP_SUCCESS;
            }

        }
    }

    public  byte[] downloadFile(String fileName){
        if(termTestTimetableRepo.existsTermTestTimetableByName(fileName)){
            Optional<TermTestTimetable> termTestTimetable = termTestTimetableRepo.findTermTestTimetableByName(fileName);

            return StoreFile.decompressFile(termTestTimetable.orElseThrow().getFileData());
        }else {
            return null;
        }

    }
}
