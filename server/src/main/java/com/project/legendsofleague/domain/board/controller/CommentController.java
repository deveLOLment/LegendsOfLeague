package com.project.legendsofleague.domain.board.controller;

import com.project.legendsofleague.domain.board.dto.CommentDTO;
import com.project.legendsofleague.domain.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//controller RestController 확인
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
  private final CommentService commentService;
//@ModelAttribute , @RequestBody
  @PostMapping("save")
  public ResponseEntity save(@RequestBody CommentDTO commentDTO) {

    Long saveResult = commentService.save(commentDTO);

    if(saveResult != null) {
      List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
      return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }else {
      return new ResponseEntity<>("해당 게시글 존재하지 않습니다", HttpStatus.NOT_FOUND);
    }
  }



}
