package com.project.legendsofleague.domain.board.dto;

import com.project.legendsofleague.domain.board.domain.CommentEntity;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
public class CommentDTO {
  private Long id;
  private String commentWriter;
  private String commentContents;
  private LocalDateTime commentCreatedTime;

  //  계층형
  private Long boardId; // 게시글 ID
//  private Long parentId; // 부모 댓글 ID
//  private List<CommentDTO> childComments; // 자식 댓글 목록


  public static CommentDTO toCommentDTO(CommentEntity commentEntity, Long boardId) {
    CommentDTO commentDTO = new CommentDTO();
    commentDTO.id = commentEntity.getId();
    commentDTO.commentWriter = commentEntity.getCommentWriter();
    commentDTO.commentContents = commentEntity.getCommentContents();
    commentDTO.commentCreatedTime = commentEntity.getCommentCreatedTime();
    commentDTO.boardId = boardId;
    return commentDTO;
  }
}
