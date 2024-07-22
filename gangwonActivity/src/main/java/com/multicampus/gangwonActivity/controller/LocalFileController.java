package com.multicampus.gangwonActivity.controller;

import com.multicampus.gangwonActivity.dto.request.board.FileUploadRequestDto;
import com.multicampus.gangwonActivity.service.LocalFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/localfile")
@RequiredArgsConstructor
public class LocalFileController {

    private final LocalFileService localFileService;

    @PostMapping("/upload")
    public String upload(
            @RequestParam(value = "file", required = false) MultipartFile file
    ){
        String url = localFileService.upload(file);
        return url;
    }

    @GetMapping(value="{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Resource getImage(
            @PathVariable("fileName") String fileName
    ){
        Resource resource = localFileService.getImage(fileName);
        return resource;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFile(@RequestBody FileUploadRequestDto fileUploadRequestDto) throws IOException {
        String fileName = fileUploadRequestDto.getFileName();
        String imageAddress = fileUploadRequestDto.getFileUrl();
        System.out.println("DELETE imageAddress : " + imageAddress);
        localFileService.deleteImage(imageAddress);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/deleteifpost")
    public ResponseEntity<?> deleteLocalFile(@RequestBody FileUploadRequestDto fileUploadRequestDto) throws IOException {
        String fileName = fileUploadRequestDto.getFileName();
        String imageAddress = fileUploadRequestDto.getFileUrl();
        System.out.println("DELETE imageAddress : " + imageAddress);
        localFileService.deleteLocalFile(imageAddress);
        return ResponseEntity.ok().build();

    }


}
