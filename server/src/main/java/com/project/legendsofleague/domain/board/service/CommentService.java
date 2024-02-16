package com.project.legendsofleague.domain.board.service;

import com.project.legendsofleague.domain.board.domain.BoardEntity;
import com.project.legendsofleague.domain.board.domain.CommentEntity;
import com.project.legendsofleague.domain.board.dto.CommentDTO;
import com.project.legendsofleague.domain.board.repository.BoardRepository;
import com.project.legendsofleague.domain.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentRepository commentRepository;
  private final BoardRepository boardRepository;



  public Long save(CommentDTO commentDTO) {
    Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());
    if(optionalBoardEntity.isPresent()) {
      BoardEntity boardEntity = optionalBoardEntity.get();
      CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);
      return commentRepository.save(commentEntity).getId();

    }else {
      return null;
    }
  }

  public List<CommentDTO> findAll(Long boardId) {
    // select * from comment_table where board_id=? order by id desc;
    BoardEntity boardEntity = boardRepository.findById(boardId).get();
    List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);

    List<CommentDTO> commentDTOList = new ArrayList<>();
    for(CommentEntity commentEntity : commentEntityList) {
      CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardId);
      commentDTOList.add(commentDTO);
    }
    return commentDTOList;
  }
}
