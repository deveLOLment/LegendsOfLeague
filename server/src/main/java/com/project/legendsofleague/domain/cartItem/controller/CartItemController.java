package com.project.legendsofleague.domain.cartItem.controller;


import com.project.legendsofleague.domain.cartItem.dto.CartItemRequestDto;
import com.project.legendsofleague.domain.cartItem.service.CartItemService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    /**
     * 장바구니에 아이템을 담는 컨트롤러
     *
     * @param cartItemRequestDto
     * @return
     */
    @Operation(summary = "아이템을 장바구니에 추가하는 컨트롤러입니다.")
    @PostMapping("/cart/add")
    public ResponseEntity<Long> createCartItem(@RequestBody CartItemRequestDto cartItemRequestDto) {
        Long memberId = 1L;

        Long cartItemId = cartItemService.createCartItem(memberId, cartItemRequestDto);

        return ResponseEntity.ok(cartItemId);
    }
}
