package com.project.legendsofleague.domain.cartItem.dto;

import lombok.Getter;

@Getter
public class CartItemResponseDto {
    private Long itemId;

    private String name;

    private Integer price;

    private String category;

    private String thumbnailImage;

    private Integer count;

}
