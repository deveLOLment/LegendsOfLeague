package com.project.legendsofleague.domain.board.controller;

import com.project.legendsofleague.domain.board.domain.HeartEntity;
import com.project.legendsofleague.domain.board.dto.HeartDTO;
import com.project.legendsofleague.domain.board.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.OutputKeys;

@RestController
@RequiredArgsConstructor
@RequestMapping("/heart")
public class HeartController {
 private final HeartService heartService;

 @PostMapping("/heartAction")
 public ResponseEntity heartAction(@RequestBody HeartDTO heartDTO) {

  Long heartNumber = heartService.heartSave(heartDTO);

  if(heartNumber != null) {
   HeartDTO heartDTOs = heartService.findAll(heartNumber);
   System.out.println(heartDTOs);

   return new ResponseEntity(heartDTOs, HttpStatus.OK);
  }else {
   return new ResponseEntity("좋아요", HttpStatus.NOT_FOUND);
  }
 }



}
