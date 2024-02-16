//package com.project.legendsofleague.domain.board.service;
//
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import com.amazonaws.services.s3.transfer.Upload;
//import com.project.legendsofleague.config.S3Config;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartRequest;
//
//import java.io.File;
//import java.util.UUID;
//
//@Service
//public class ImageService {
//
//  private S3Config s3Config;
//
//  @Autowired
//  public ImageService(S3Config s3Config) {
//    this.s3Config = s3Config;
//  }
//
//  private String localLocation = "C:/dev_source/File_Store/BattleCording/";
//
//
//
//  public String imageUpload(MultipartRequest request) {
//
//    //request 인자에서 이미지 파일을 뽑아냄
//    MultipartFile file = request.getFile("upload");
//
//
//    //뽑아낸 이미지 파일에서 이름 및 확장자 추출
//    String fileName = file.getOriginalFilename();
//    String ext = fileName.substring(fileName.indexOf("."));
//
//    //이미지 파일 이름 유일성을 위해 uuid 생성
//    String uuidFileName = UUID.randomUUID() +ext;
//
//    //서버 환경에 저장할 경로 생성
//    String localPath = localLocation + uuidFileName;
//
//    //서버 환경에 이미지 파일 저장
//    File localFile = new File(localPath);
//    file.transferTo(localFile);
//
//
//  }
//}
