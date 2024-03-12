package com.project.legendsofleague.domain.cartItem.dto;

import com.project.legendsofleague.domain.cartItem.domain.CartItem;
import com.project.legendsofleague.domain.item.domain.Item;
import lombok.Getter;

@Getter
public class CartItemResponseDto {

    private Long cartItemId;

    private String name;

    private Integer price;

    private String category;

    private String thumbnailImage;

    private Integer count;
    private Integer stock;


    public static CartItemResponseDto toDto(CartItem cartItem) {
        CartItemResponseDto cartItemResponseDto = new CartItemResponseDto();
        Item item = cartItem.getItem();
        cartItemResponseDto.cartItemId = cartItem.getId();
        cartItemResponseDto.name = item.getName();
        cartItemResponseDto.price = item.getPrice();
        cartItemResponseDto.category = item.getCategory().getDisplayName();
        cartItemResponseDto.thumbnailImage = item.getThumbnailImage();
        cartItemResponseDto.count = cartItem.getCount();
        cartItemResponseDto.stock = item.getStock();

        return cartItemResponseDto;
    }
}
