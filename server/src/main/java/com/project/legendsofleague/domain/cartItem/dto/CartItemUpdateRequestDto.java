package com.project.legendsofleague.domain.cartItem.dto;

import lombok.Getter;

@Getter
public class CartItemUpdateRequestDto {
    private Long cartItemId;
    private Integer count;

    //추후에 사이즈 등 다양한 옵션 추가 예정
}
