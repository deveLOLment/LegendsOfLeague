package com.project.legendsofleague.domain.item.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "item_id")
  private Long id;

  private String name; //상품 이름

  private Integer price; //상품 가격

  private Integer stock; //상품 재고 (남은 수량)

  private String description; //상품 설명

  private ItemCategory category;

  @ColumnDefault("false")
  @Column(columnDefinition = "TINYINT(1)")
  private boolean deleteYn;

  /**
   * ThumbnailImage는 1대1매핑, ItemImage는 1대N 매핑이 되어 있음.
   */

}
