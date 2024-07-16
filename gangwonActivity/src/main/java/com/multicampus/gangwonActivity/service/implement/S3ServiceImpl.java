package com.multicampus.gangwonActivity.service.implement;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.multicampus.gangwonActivity.mapper.BoardMapper;
import com.multicampus.gangwonActivity.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {


    private final AmazonS3 amazonS3;
    private final BoardMapper boardMapper;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucket;

    //S3에 이미지 업로드
    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {

        String fileName = multipartFile.getOriginalFilename();

        String newFileName = UUID.randomUUID()+fileName.substring(fileName.lastIndexOf("."));
        String fileUrl = "https://" + bucket + ".s3.amazonaws.com/" + newFileName;
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(multipartFile.getContentType());
        metadata.setContentLength(multipartFile.getSize());

        amazonS3.putObject(bucket, newFileName, multipartFile.getInputStream(), metadata);


        return fileUrl;
    }

    //S3 이미지 삭제
    @Override
    public void deleteFile(String fileUrl) throws IOException {
        try{
            if(fileUrl != null) {
                int index = fileUrl.lastIndexOf("com/");
                String newFileName = fileUrl.substring(index + 4);
                amazonS3.deleteObject(bucket, newFileName);
                boardMapper.deleteImageFile(fileUrl);
            }
        } catch (SdkClientException e){
            System.out.println("error message" + e.getMessage());
            throw new IOException("S3 File Delete Error");
        }
    }
}
