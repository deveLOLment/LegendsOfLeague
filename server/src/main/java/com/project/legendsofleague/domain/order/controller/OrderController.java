package com.project.legendsofleague.domain.order.controller;


import com.project.legendsofleague.domain.order.dto.OrderRequestDto;
import com.project.legendsofleague.domain.order.dto.OrderResponseDto;
import com.project.legendsofleague.domain.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;


    /**
     * 상세 아이템에서 주문 버튼을 눌렀을 때 요청되는 url이다.
     *
     * @param orderRequestDto
     * @return
     */
    @Operation(summary = "주문 관련 컨트롤러입니다.(상세 아이템 창에서 주문하기 버튼을 누르면 실행됩니다.)")
    @PostMapping("/order/single")
    public ResponseEntity<Long> orderSingleItem(@RequestBody OrderRequestDto orderRequestDto) {
        Long memberId = 1L; //임의의 멤버 생성
        Long orderId = orderService.createOrder(orderRequestDto, memberId);

        return ResponseEntity.ok(orderId);
    }

    @Operation(summary = "주문 페이지 관련 컨트롤러입니다.")
    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderResponseDto> orderPage(@PathVariable("orderId") Long orderId) {
        Long memberId = 1L;
        orderService.detailOrderPage(memberId, orderId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * 장바구니 페이지에서 주문 버튼을 눌렀을 때 요청되는 url이다.
     *
     * @param orderRequestList
     * @return
     */
//    @Operation(summary = "주문 관련 컨트롤러입니다.(장바구니 창에서 주문하기 버튼을 누르면 실행됩니다.)")
//    @PostMapping("/order/cart")
//    public ResponseEntity<OrderResponseDto> orderCartItems(List<CartItemRequestDto> orderRequestList) {
//
//    }
}
