package com.project.legendsofleague.domain.cartItem.dto;


import lombok.Getter;


/**
 * 장바구니 목록 아이템 생성을 위한 dto
 */
@Getter
public class CartItemRequestDto {
    private Long itemId;
    private Integer count;
}
