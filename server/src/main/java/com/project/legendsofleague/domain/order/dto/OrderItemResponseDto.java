package com.project.legendsofleague.domain.order.dto;

import com.project.legendsofleague.domain.coupon.dto.CouponResponseDto;
import com.project.legendsofleague.domain.order.domain.OrderItem;

public class OrderItemResponseDto {
    private Long id;

    private String name;

    private Integer price;

    private Integer count; //주문한 상품의 개수

    private String category;

    private String thumbnailImage;

    private CouponResponseDto couponResponseDto;

    public static OrderItemResponseDto toDto(OrderItem orderItem) {
        OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
        orderItemResponseDto.id = orderItem.getId();
        orderItemResponseDto.name = orderItem.getItem().getName();
        orderItemResponseDto.price = orderItem.getOrderPrice();
        orderItemResponseDto.count = orderItem.getCount();
        orderItemResponseDto.category = String.valueOf(orderItem.getItem().getCategory());
        orderItemResponseDto.thumbnailImage = orderItem.getItem().getThumbnailImage();

        return orderItemResponseDto;
    }
}
