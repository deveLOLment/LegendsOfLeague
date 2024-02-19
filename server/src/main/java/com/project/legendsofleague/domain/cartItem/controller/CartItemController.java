package com.project.legendsofleague.domain.cartItem.controller;


import com.project.legendsofleague.domain.cartItem.dto.CartItemAddRequestDto;
import com.project.legendsofleague.domain.cartItem.dto.CartItemDeleteRequestDto;
import com.project.legendsofleague.domain.cartItem.dto.CartItemResponseDto;
import com.project.legendsofleague.domain.cartItem.dto.CartItemUpdateRequestDto;
import com.project.legendsofleague.domain.cartItem.service.CartItemService;
import com.project.legendsofleague.domain.member.dto.CustomMemberDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;


    @Operation(summary = "장바구니 목록을 보여주는 컨트롤러입니다.")
    @GetMapping("/carts")
    public ResponseEntity<List<CartItemResponseDto>> showCartItemList(@AuthenticationPrincipal(errorOnInvalidType = true) CustomMemberDetails customMemberDetails) {
        Long memberId = customMemberDetails.getMember().getId();
        List<CartItemResponseDto> cart = cartItemService.findCartList(memberId);

        return ResponseEntity.ok(cart);
    }

    @Operation(summary = "장바구니 목록에서 특정 아이템 옵션(수량 등)을 수정하는 컨트롤러입니다.")
    @PostMapping("/cart/{cartItemId}/update")
    public ResponseEntity<List<CartItemResponseDto>> updateCartItem(@AuthenticationPrincipal(errorOnInvalidType = true) CustomMemberDetails customMemberDetails,
                                                                    @PathVariable("cartItemId") Long cartItemId, @RequestBody CartItemUpdateRequestDto cartItemUpdateRequestDto) {
        Long memberId = customMemberDetails.getMember().getId();

        List<CartItemResponseDto> cartItemResponseDtoList = cartItemService.updateCartItem(memberId, cartItemUpdateRequestDto);

        return ResponseEntity.ok(cartItemResponseDtoList);
    }

    /**
     * 장바구니에 아이템을 담는 컨트롤러
     *
     * @param cartItemAddRequestDto
     * @return
     */
    @Operation(summary = "아이템을 장바구니에 추가하는 컨트롤러입니다.")
    @PostMapping("/cart/add")
    public ResponseEntity<Long> createCartItem(@RequestBody CartItemAddRequestDto cartItemAddRequestDto) {
        Long memberId = 1L;

        Long cartItemId = cartItemService.createCartItem(memberId, cartItemAddRequestDto);

        return ResponseEntity.ok(cartItemId);
    }

    @Operation(summary = "장바구니에서 아이템을 삭제하는 컨트롤러입니다.")
    @PostMapping("/cart/delete")
    public ResponseEntity<Void> deleteCartItem(@RequestBody List<CartItemDeleteRequestDto> cartItemDeleteRequestList) {
        Long memberId = 1L;
        cartItemService.deleteCartItem(memberId, cartItemDeleteRequestList);
        return ResponseEntity.ok(null);
    }
}
