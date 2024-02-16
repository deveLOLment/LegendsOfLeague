package com.project.legendsofleague.domain.board.controller;

import com.project.legendsofleague.domain.board.dto.BoardDTO;
import com.project.legendsofleague.domain.board.dto.CommentDTO;
import com.project.legendsofleague.domain.board.service.BoardService;
import com.project.legendsofleague.domain.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
  private final BoardService boardService;
  private final CommentService commentService;


  @PostMapping("/save")
  public String save(@RequestBody BoardDTO boardDTO){

    System.out.println("boardDTO 보내는 정보 = " + boardDTO);

    boardService.save(boardDTO);

    return "list";
  }

  @GetMapping("/")
  public String findAll(Model model) {
    List<BoardDTO> boardDTOList = boardService.findAll();
    System.out.println("boardList 게시글 목록" + boardDTOList);


    model.addAttribute("boardList 게시글 목록", boardDTOList);
    return "list";
  }

  @GetMapping("/{id}")
  public String findById(@PathVariable Long id, Model model,
                         @PageableDefault(page=1) Pageable pageable) {

    boardService.updateHits(id);
    BoardDTO boardDTO = boardService.findById(id);
    /* 댓글 목록 가져오기 */
    List<CommentDTO> commentDTOList = commentService.findAll(id);
    model.addAttribute("commentList", commentDTOList);
    model.addAttribute("board", boardDTO);
    model.addAttribute("page", pageable.getPageNumber());
    return "detail";
  }

  @PostMapping("/update")
  public String updpate(@ModelAttribute BoardDTO boardDTO, Model model) {
    BoardDTO board = boardService.update(boardDTO);
    model.addAttribute("board",board);
    return "detail";
  }

  @GetMapping("/update/{id}")
  public String updateFrom(@PathVariable Long id, Model model) {
    BoardDTO boardDTO = boardService.findById(id);
    model.addAttribute("boardUpdate", boardDTO);
    return "update";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable Long id) {
    boardService.delete(id);
    return "redirect:/board/";
  }

  // 검색
  @GetMapping("/searchPage")
  public String searchPaging(@RequestBody){

  }





}
