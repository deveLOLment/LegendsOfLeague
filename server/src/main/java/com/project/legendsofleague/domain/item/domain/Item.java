package com.project.legendsofleague.domain.item.domain;


import com.project.legendsofleague.common.BaseEntity;
import com.project.legendsofleague.domain.item.dto.ItemRequestDto;
import com.project.legendsofleague.domain.itemreview.domain.ItemReview;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name; //상품 이름

    private Integer price; //상품 가격

    private Integer stock; //상품 재고 (남은 수량)

    private String description; //상품 설명

    @Enumerated(EnumType.STRING)
    private ItemCategory category;

    private String thumbnailImage; //originalFileName은 필요가 없으니까 s3 url만 있으면 된다.

    private final boolean isDeleted = false;

    @OneToMany(mappedBy = "item")
    private final List<ItemImage> itemImageList = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE)
    private final List<ItemReview> itemReviewList = new ArrayList<>();

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

    public static Item forDummyData(String name, ItemCategory category) {
        Item item = new Item();
        item.name = name;
        item.stock = 100;
        item.price = 10000;
        item.category = category;
        item.thumbnailImage = "https://legends-of-league.s3.ap-northeast-2.amazonaws.com/4e36ed3d-e577-4677-acab-eb04d3d47e21.jpeg";

        return item;
    }

    public static Item toEntity(ItemRequestDto itemRequestDto, String thumbnailImage) {
        Item item = new Item();
        item.name = itemRequestDto.getName();
        item.price = itemRequestDto.getPrice();
        item.stock = itemRequestDto.getStock();
        item.description = itemRequestDto.getDescription();
        item.category = itemRequestDto.getCategory();
        item.thumbnailImage = thumbnailImage;

        return item;
    }

    public void removeStock(Integer count) {
        this.stock -= count;
    }

    public void addStock(Integer count) {
        this.stock += count;
    }
}
