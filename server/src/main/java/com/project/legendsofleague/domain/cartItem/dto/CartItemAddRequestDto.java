package com.project.legendsofleague.domain.cartItem.dto;


import lombok.Getter;

/**
 * 장바구니에 아이템을 추가하기 위한 요청 dto
 */
@Getter
public class CartItemAddRequestDto {
    private Long itemId;
    private Integer count;
}
