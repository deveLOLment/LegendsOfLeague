package com.project.legendsofleague.domain.board.dto;

import com.project.legendsofleague.domain.board.domain.HeartEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HeartDTO {
  private String memberId;
  private Long boardId;


  public static HeartDTO toHeartDTO(HeartEntity heartEntity){
    HeartDTO heartDTO = new HeartDTO();
    heartDTO.setBoardId(heartEntity.getBoardEntity().getId());
    return heartDTO;
  }
}