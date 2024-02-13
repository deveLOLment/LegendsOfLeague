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

    private Long id;

    private String name;

    private Integer price;

    private String description;

    private String category;

    private String thumbnailImage;

    private List<ItemImageReponseDto> itemImageList = new ArrayList<>();

    public static ItemDetailResponseDto toDto(Item item) {
        ItemDetailResponseDto itemDetailResponseDto = new ItemDetailResponseDto();
        itemDetailResponseDto.id = item.getId();
        itemDetailResponseDto.name = item.getName();
        itemDetailResponseDto.price = item.getPrice();
        itemDetailResponseDto.description = item.getDescription();
        itemDetailResponseDto.category = item.getCategory().getDisplayName();
        itemDetailResponseDto.thumbnailImage = item.getThumbnailImage();
        itemDetailResponseDto.itemImageList = item.getItemImageList()
                .stream().map((im) -> ItemImageReponseDto.toDto(im)).toList();

        return itemDetailResponseDto;
    }
}
