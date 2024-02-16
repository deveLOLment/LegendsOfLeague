package com.project.legendsofleague.domain.board.domain;

import com.project.legendsofleague.domain.board.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "board_id")
  private Long id;

//  작성자
  @Column
  private String boardWriter;

//  생성 날짜
  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdTime;

//  수정 날짜
  @UpdateTimestamp
  @Column(insertable = false)
  private LocalDateTime updatedTime;

//  카테고리 분류, 번호마다 게시판 분류 할 예정
  @Column
  private int boardCategory;

//  제목
  @Column
  private String boardTitle;

//  내용
  @Column
  private String boardContents;

  // 조회수
  @Column
  private int boardHits;

//  신고 여부
  @Column
  private int report;

//  스크랩 여부
  @Column
  private int scrap;

  // 파일 첨부 여부
  @Column
  private int boardFileAttached;


  @OneToMany(mappedBy = "boardEntity" , cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();

  @OneToMany(mappedBy = "boardEntity" , cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<CommentEntity> commentEntityList = new ArrayList<>();


  public static BoardEntity toSaveBoard(BoardDTO boardDTO) {
    BoardEntity boardEntity = new BoardEntity();
    boardEntity.boardWriter = boardDTO.getBoardWriter();
    boardEntity.boardCategory = boardDTO.getBoardCategory();
    boardEntity.boardTitle = boardDTO.getBoardTitle();
    boardEntity.boardContents = boardDTO.getBoardContents();
    boardEntity.boardHits = 0;
    boardEntity.report = 0;
    boardEntity.scrap = 0;
    boardEntity.boardFileAttached = 0;
    return boardEntity;
  }


  public static BoardEntity toSaveFile(BoardDTO boardDTO) {
    BoardEntity boardEntity = new BoardEntity();
    boardEntity.boardWriter = boardDTO.getBoardWriter();
    boardEntity.boardCategory = boardDTO.getBoardCategory();
    boardEntity.boardTitle = boardDTO.getBoardTitle();
    boardEntity.boardContents = boardDTO.getBoardContents();
    boardEntity.boardHits = 0;
    boardEntity.report = 0;
    boardEntity.scrap = 0;
    boardEntity.boardFileAttached = 1;
    return boardEntity;
  }


  public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
    BoardEntity boardEntity = new BoardEntity();
    boardEntity.id = boardDTO.getId();
    boardEntity.boardWriter = boardDTO.getBoardWriter();
    boardEntity.boardCategory = boardDTO.getBoardCategory();
    boardEntity.boardTitle = boardDTO.getBoardTitle();
    boardEntity.boardContents = boardDTO.getBoardContents();
    boardEntity.boardHits = boardEntity.getBoardHits();
    boardEntity.report = boardEntity.getReport();
    boardEntity.scrap = boardEntity.getScrap();
    boardEntity.boardFileAttached = boardEntity.getBoardFileAttached();
    return boardEntity;

  }
}
