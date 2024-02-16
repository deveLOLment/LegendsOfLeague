package com.project.legendsofleague.domain.board.domain;

import com.project.legendsofleague.domain.board.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter

public class CommentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String commentWriter;

  @Column
  private String commentContents;


  @Column
  private LocalDateTime commentCreatedTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id")
  private BoardEntity boardEntity;

//  계층형
//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "parent_id")
//  private CommentEntity parent;
//
//  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY ,
//      cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
//  private List<CommentEntity> child = new ArrayList<>();


  public static CommentEntity toSaveEntity(CommentDTO commentDTO, BoardEntity boardEntity) {
    CommentEntity commentEntity = new CommentEntity();
    commentEntity.commentWriter = commentDTO.getCommentWriter();
    commentEntity.commentContents = commentDTO.getCommentContents();
    commentEntity.boardEntity = boardEntity;
    return commentEntity;
  }
}
