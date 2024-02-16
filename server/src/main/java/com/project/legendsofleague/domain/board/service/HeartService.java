package com.project.legendsofleague.domain.board.service;

import com.project.legendsofleague.domain.board.domain.BoardEntity;
import com.project.legendsofleague.domain.board.domain.HeartEntity;
import com.project.legendsofleague.domain.board.dto.HeartDTO;
import com.project.legendsofleague.domain.board.repository.BoardRepository;
import com.project.legendsofleague.domain.board.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HeartService {
  private final HeartRepository heartRepository;
  private final BoardRepository boardRepository;



  public Long heartSave(HeartDTO heartDTO) {
    Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(heartDTO.getBoardId());
    log.info("부모 게시글={}", optionalBoardEntity);
    if(optionalBoardEntity.isPresent()) {
      BoardEntity boardEntity = optionalBoardEntity.get();
      HeartEntity heartEntity = HeartEntity.heartSaveEntity(heartDTO, boardEntity);

      System.out.println(boardEntity.getId());

      return heartRepository.save(heartEntity).getId();


    }else{
      return null;
    }
  }

  public HeartDTO findAll(Long heartNumber) {
    HeartEntity heartEntity = heartRepository.findById(heartNumber).get();
    System.out.println("좋아요 엔티티에서 아이디로 찾아온 내역" + heartEntity);

    HeartDTO heartDTO = HeartDTO.toHeartDTO(heartEntity);
    return heartDTO;
  }
}
