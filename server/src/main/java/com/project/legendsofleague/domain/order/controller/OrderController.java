package com.project.legendsofleague.domain.order.controller;


import com.project.legendsofleague.domain.cartItem.dto.CartItemOrderRequestDto;
import com.project.legendsofleague.domain.member.dto.CustomMemberDetails;
import com.project.legendsofleague.domain.order.dto.OrderRequestDto;
import com.project.legendsofleague.domain.order.dto.OrderResponseDto;
import com.project.legendsofleague.domain.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;


    /**
     * 주문 목록을 보여주는 컨트롤러입니다.
     */
    @Operation(summary = "주문 목록을 보여주기 위한 컨트롤러입니다.")
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDto>> showOrderList(@AuthenticationPrincipal(errorOnInvalidType = true) CustomMemberDetails customMemberDetails) {
        Long memberId = customMemberDetails.getMember().getId();

        List<OrderResponseDto> orderList = orderService.findOrderList(memberId);
        for (OrderResponseDto orderResponseDto : orderList) {
            log.info("order={}", orderResponseDto.getId());
        }

        return ResponseEntity.ok(orderList);
    }


    /**
     * 상세 아이템에서 주문 버튼을 눌렀을 때 요청되는 url이다.
     *
     * @param orderRequestDto
     * @return
     */
    @Operation(summary = "주문 관련 컨트롤러입니다.(상세 아이템 창에서 주문하기 버튼을 누르면 실행됩니다.)")
    @PostMapping("/order/single")
    public ResponseEntity<Long> orderSingleItem(@AuthenticationPrincipal(errorOnInvalidType = true) CustomMemberDetails customMemberDetails, @RequestBody OrderRequestDto orderRequestDto) {
        Long memberId = customMemberDetails.getMember().getId();


        Long orderId = orderService.createOrder(orderRequestDto, memberId);
        customMemberDetails.getUsername();
        return ResponseEntity.ok(orderId);
    }

    /**
     * 장바구니 페이지에서 주문 버튼을 눌렀을 때 요청되는 url이다.
     *
     * @param cartItemRequestList
     * @return
     */
    @Operation(summary = "주문 관련 컨트롤러입니다.(장바구니 창에서 주문하기 버튼을 누르면 실행됩니다.)")
    @PostMapping("/order/cart")
    public ResponseEntity<Long> orderCartItems(@RequestBody List<CartItemOrderRequestDto> cartItemRequestList) {
        Long memberId = 1L;
        Long orderId = orderService.createOrderFromCart(cartItemRequestList, memberId);

        return ResponseEntity.ok(orderId);
    }

    @Operation(summary = "주문 페이지 관련 컨트롤러입니다.")
    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderResponseDto> orderPage(@PathVariable("orderId") Long orderId) {
        Long memberId = 1L;
        OrderResponseDto orderResponseDto = orderService.detailOrderPage(memberId, orderId);

        return ResponseEntity.ok(orderResponseDto);
    }
}
