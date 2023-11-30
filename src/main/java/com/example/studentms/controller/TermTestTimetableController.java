package com.example.studentms.controller;

import com.example.studentms.dto.ResponseDTO;
import com.example.studentms.service.TermTestTimetableService;
import com.example.studentms.util.varList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/api/tts/termTestTimetable")
public class TermTestTimetableController {
    @Autowired
    private TermTestTimetableService termTestTimetableService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/uploadFile")
    public ResponseEntity<ResponseDTO> uploadFile(@RequestParam("file") MultipartFile multipartFile){
      try {
          String res = termTestTimetableService.uploadFile(multipartFile);
          if(res.equals("00")){
              responseDTO.setCode(varList.RSP_SUCCESS);
              responseDTO.setMessage("File Upload Success!");
              responseDTO.setContent(multipartFile.getOriginalFilename());
              return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
          }else if(res.equals("04")){
              responseDTO.setCode(varList.RSP_TOKEN_INVALID);
              responseDTO.setMessage("File Upload Fail :(");
              responseDTO.setContent(null);
              return new ResponseEntity<>(responseDTO, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
          }else if(res.equals("06")){
              responseDTO.setCode(varList.RSP_DUPLICATED);
              responseDTO.setMessage("Alredy Reported!");
              responseDTO.setContent(null);
              return new ResponseEntity<>(responseDTO, HttpStatus.ALREADY_REPORTED);
          }else{
              responseDTO.setCode(varList.RSP_FAIL);
              responseDTO.setMessage("Something went Wrong!");
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

    @GetMapping("/{fileName}/downloadFile")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        try {
            byte[] file = termTestTimetableService.downloadFile(fileName);
            if (file == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(getMediaType(fileName))
                        .body(file);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private MediaType getMediaType(String fileName) {
        String fileExtension = StringUtils.getFilenameExtension(fileName);
        switch (fileExtension.toLowerCase()) {
            case "pdf":
                return MediaType.valueOf("application/pdf");
            case "jpg":
                return MediaType.valueOf("image/jpg");
            case "jpeg":
                return MediaType.valueOf("image/jpeg");
            case "png":
                return MediaType.valueOf("image/png");
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

}
