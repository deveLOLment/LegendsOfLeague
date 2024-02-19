package com.project.legendsofleague.domain.order.dto;

import com.project.legendsofleague.domain.coupon.dto.CouponResponseDto;
import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.order.domain.OrderItem;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Slf4j
public class OrderItemResponseDto {
    private Long id; //item의 id

    private String name;

    private Integer price;

    private Integer count; //주문한 상품의 개수

    private String category;

    private String thumbnailImage;

    private List<CouponResponseDto> couponList = new ArrayList<>();

    public static OrderItemResponseDto toDto(OrderItem orderItem, Map<Long, List<CouponResponseDto>> couponResponseList) {
        OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
        Item item = orderItem.getItem();
        orderItemResponseDto.id = item.getId();
        orderItemResponseDto.name = item.getName();
        orderItemResponseDto.price = orderItem.getOrderPrice();
        orderItemResponseDto.count = orderItem.getCount();
        orderItemResponseDto.category = item.getCategory().getDisplayName();
        orderItemResponseDto.thumbnailImage = item.getThumbnailImage();
        List<CouponResponseDto> couponResponseDtos = couponResponseList.get(item.getId());
        if (couponResponseDtos != null) {
            orderItemResponseDto.couponList = couponResponseDtos;
        }
        return orderItemResponseDto;
    }

    public static OrderItemResponseDto toDto(OrderItem orderItem) {
        OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
        Item item = orderItem.getItem();
        orderItemResponseDto.id = item.getId();
        orderItemResponseDto.name = item.getName();
        orderItemResponseDto.price = orderItem.getOrderPrice();
        orderItemResponseDto.count = orderItem.getCount();
        orderItemResponseDto.category = item.getCategory().getDisplayName();
        orderItemResponseDto.thumbnailImage = item.getThumbnailImage();
        return orderItemResponseDto;
    }
}
