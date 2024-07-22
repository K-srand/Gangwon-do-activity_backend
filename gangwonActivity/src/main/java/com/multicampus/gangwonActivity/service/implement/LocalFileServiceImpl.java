package com.multicampus.gangwonActivity.service.implement;

import com.multicampus.gangwonActivity.mapper.BoardMapper;
import com.multicampus.gangwonActivity.service.LocalFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocalFileServiceImpl implements LocalFileService {

    private final BoardMapper boardMapper;
    @Value("${file.path}")
    private String filePath;
    @Value("${file.url}")
    private String fileUrl;

    @Override
    public String upload(MultipartFile file) {
        if( file.isEmpty()) return null;

        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + extension;
        String savePath = filePath + saveFileName;

        try {
            file.transferTo(new File(savePath));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        String url = fileUrl + saveFileName;

        return url;
    }

    @Override
    public Resource getImage(String fileName) {
        Resource resource = null;
        try {
            resource = new UrlResource("file:" + filePath + fileName);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return resource;
    }

    @Override
    public void deleteImage(String fileUrl) throws IOException{
        try {
            if(fileUrl != null) {

                boardMapper.deleteImageFile(fileUrl);
                // 로컬 파일 시스템에서 이미지 삭제
                deleteLocalFile(fileUrl);

            }
        }catch (Exception e){
            System.out.println("error message" + e.getMessage());
            throw new IOException("DELETE ERROR");
        }
    }

    // 로컬 파일을 삭제하는 메서드
    public void deleteLocalFile(String fileUrl) {
        String prefix = "http://localhost:4040/localfile/";
        int prefixIndex = fileUrl.indexOf(prefix);
        String fileName = "";
        if (prefixIndex != -1) {
            fileName = fileUrl.substring(prefixIndex + prefix.length());
        }
        File file = new File(filePath+fileName);
        if (file.exists()) {
            boolean isDeleted = file.delete();
            if (!isDeleted) {
                System.out.println("Failed to delete file: " + fileName);
            } else {
                System.out.println("File deleted successfully: " + fileName);
            }
        } else {
            System.out.println("File not found: " + fileName);
        }
    }
}
