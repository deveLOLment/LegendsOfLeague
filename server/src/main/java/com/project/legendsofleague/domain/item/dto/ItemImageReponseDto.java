package com.project.legendsofleague.domain.item.dto;

import com.project.legendsofleague.domain.item.domain.ItemImage;
import lombok.Getter;

@Getter
public class ItemImageReponseDto {

    private Long id;

    private String imageUrl;


    public static ItemImageReponseDto toDto(ItemImage itemImage) {
        ItemImageReponseDto itemImageReponseDto = new ItemImageReponseDto();
        itemImageReponseDto.imageUrl = itemImage.getImageUrl();

        return itemImageReponseDto;
    }
}
