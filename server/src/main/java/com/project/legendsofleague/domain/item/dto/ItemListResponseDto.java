package com.project.legendsofleague.domain.item.dto;

import com.project.legendsofleague.domain.item.domain.Item;
import lombok.Getter;

/**
 * 쇼핑몰 아이템 리스트를 보여주기 위한 dto
 */
@Getter
public class ItemListResponseDto {

    private Long id;

    private String name;

    private String category;
    private String thumbnailImage;
    private Integer price;


    public static ItemListResponseDto toDto(Item item) {
        ItemListResponseDto itemListResponseDto = new ItemListResponseDto();
        itemListResponseDto.id = item.getId();
        itemListResponseDto.name = item.getName();
        itemListResponseDto.category = item.getCategory().getDisplayName();
        itemListResponseDto.thumbnailImage = item.getThumbnailImage();
        itemListResponseDto.price = item.getPrice();

        return itemListResponseDto;
    }
}
