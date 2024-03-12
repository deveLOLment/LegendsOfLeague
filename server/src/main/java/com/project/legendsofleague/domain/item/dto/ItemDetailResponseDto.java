package com.project.legendsofleague.domain.item.dto;

import com.project.legendsofleague.domain.item.domain.Item;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 * 상세 아이템 페이지를 위한 dto
 */
@Getter
public class ItemDetailResponseDto {

    private Long itemId;

    private String name;

    private Integer price;

    private String description;

    private Integer stock;

    private String category;

    private String thumbnailImage;

    private List<ItemImageReponseDto> itemImageList = new ArrayList<>();

    public static ItemDetailResponseDto toDto(Item item) {
        ItemDetailResponseDto itemDetailResponseDto = new ItemDetailResponseDto();
        itemDetailResponseDto.itemId = item.getId();
        itemDetailResponseDto.name = item.getName();
        itemDetailResponseDto.price = item.getPrice();
        itemDetailResponseDto.description = item.getDescription();
        itemDetailResponseDto.stock = item.getStock();
        itemDetailResponseDto.category = item.getCategory().getDisplayName();
        itemDetailResponseDto.thumbnailImage = item.getThumbnailImage();
        itemDetailResponseDto.itemImageList = item.getItemImageList()
                .stream().map(ItemImageReponseDto::toDto).toList();

        return itemDetailResponseDto;
    }
}
