package com.multicampus.gangwonActivity.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface LocalFileService {

    String upload(MultipartFile file);
    Resource getImage(String fileName);

    void deleteImage(String fileUrl) throws IOException;

    void deleteLocalFile(String fileName);
}
