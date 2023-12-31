package com.swuProject.secound.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log
public class FileService {

    // 이미지 업로드
    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception {
        //UUID uuid = UUID.randomUUID();
        //String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        String savedFileName = originalFileName;

        String fileUploadFullUrl = uploadPath + "/" + savedFileName;

        // 경로 지정 후 저장
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileData);
        fos.close();

        return savedFileName;
    }

    // 이미지 삭제
    public void deleteFile(String filePath) throws Exception {

        File deleteFile = new File(filePath);

        if(deleteFile.exists()) {
            deleteFile.delete(); // 삭제
            log.info("파일을 삭제하였습니다.");
        } else {
            log.info("파일이 존재하지 않습니다.");
        }
    }

}

