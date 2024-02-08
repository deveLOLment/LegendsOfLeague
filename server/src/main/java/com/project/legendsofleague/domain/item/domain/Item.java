package com.project.legendsofleague.domain.item.domain;


import com.project.legendsofleague.domain.item.dto.ItemRqDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "item_id")
  private Long id;

  private String name; //상품 이름

  private Integer price; //상품 가격

  private Integer stock; //상품 재고 (남은 수량)

  private String description; //상품 설명

  private ItemCategory category;

  private String thumbnailImage; //originalFileName은 필요가 없으니까 s3 url만 있으면 된다.

  private boolean isDeleted = false;

  /**
   * 양방향 매핑서 쿼리 1개를 줄여준다.
   *
   * @param id
   */
  public Item(Long id) {
    this.id = id;
  }

  /**
   * ItemImage는 1대N 매핑이 되어 있음.
   */


  public static Item toEntity(ItemRqDto itemRqDto, String thumbnailImage) {
    Item item = new Item();
    item.name = itemRqDto.getName();
    item.price = itemRqDto.getPrice();
    item.stock = itemRqDto.getStock();
    item.description = itemRqDto.getDescription();
    item.category = itemRqDto.getCategory();
    item.thumbnailImage = thumbnailImage;

    return item;
  }

}
