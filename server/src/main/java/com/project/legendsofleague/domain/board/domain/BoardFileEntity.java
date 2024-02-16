package com.project.legendsofleague.domain.board.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class BoardFileEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String originalFileName;

  @Column
  private String storedFileName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id")
  private BoardEntity boardEntity;
}
