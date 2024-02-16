package com.project.legendsofleague.domain.board.dto;

import com.project.legendsofleague.domain.board.domain.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
  private Long id;
  private String boardWriter;
  private String boardPass;
  private LocalDateTime boardCreatedTime;
  private LocalDateTime boardUpdatedTime;
  private int boardCategory;
  private String boardTitle;
  private String boardContents;
  private int boardHits;
  private int report;
  private int scrap;

  private List<MultipartFile> boardFile;

  private List<String> originalFileName; // 원본 파일 이름
  private List<String> storedFileName; // 서버 저장용 파일 이름
  private int boardFileAttached; // 파일 첨부 여부(첨부 1, 미첨부 0)


  public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
    BoardDTO boardDTO = new BoardDTO();
    boardDTO.boardWriter = boardEntity.getBoardWriter();
    boardDTO.boardPass = boardEntity.getBoardWriter();
    boardDTO.boardCreatedTime = boardEntity.getCreatedTime();
    boardDTO.boardUpdatedTime = boardEntity.getUpdatedTime();
    boardDTO.boardCategory = boardEntity.getBoardCategory();
    boardDTO.boardTitle = boardEntity.getBoardTitle();
    boardDTO.boardContents = boardEntity.getBoardContents();
    boardDTO.boardHits = boardEntity.getBoardHits();
    boardDTO.report = boardEntity.getReport();
    boardDTO.scrap = boardEntity.getScrap();
    return boardDTO;
  }
}
