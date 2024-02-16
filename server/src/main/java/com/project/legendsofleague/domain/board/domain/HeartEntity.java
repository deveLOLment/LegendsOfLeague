package com.project.legendsofleague.domain.board.domain;

import com.project.legendsofleague.domain.board.dto.HeartDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_like")
public class HeartEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "heart_id")
  private Long id;


  @ManyToOne(fetch = FetchType.LAZY)// board와 1대n 관계
  @JoinColumn(name = "board_id")
  private BoardEntity boardEntity;


//  멤머 매핑
//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "member_id")
//  private Member member;



  public static HeartEntity heartSaveEntity(HeartDTO heartDTO, BoardEntity boardEntity){
    HeartEntity heartEntity = new HeartEntity();
    heartEntity.setId(heartEntity.id);
    heartEntity.setBoardEntity(boardEntity);
    return heartEntity;
  }
}
