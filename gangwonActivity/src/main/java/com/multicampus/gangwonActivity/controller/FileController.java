package com.multicampus.gangwonActivity.controller;

import com.multicampus.gangwonActivity.dto.request.board.FileUploadRequestDto;
import com.multicampus.gangwonActivity.service.FileService;
import com.multicampus.gangwonActivity.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final S3Service s3Service;

    // 작성글 이미지 업로드 서비스 호출
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value="file") MultipartFile uploadedfile) {
        try {
            String fileUrl = s3Service.uploadFile(uploadedfile);
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    //작성글 이미지 삭제 서비스 호출
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFile(@RequestBody FileUploadRequestDto fileUploadRequestDto) throws IOException {
        String fileName = fileUploadRequestDto.getFileName();
        String imageAddress = fileUploadRequestDto.getFileUrl();
        System.out.println("imageAddress : " + imageAddress);
        s3Service.deleteFile(imageAddress);
        return ResponseEntity.ok().build();

    }


}
