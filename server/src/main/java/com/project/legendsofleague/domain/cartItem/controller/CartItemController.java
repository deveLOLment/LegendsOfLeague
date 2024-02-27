package com.project.legendsofleague.domain.cartItem.controller;


import com.project.legendsofleague.domain.cartItem.dto.CartItemAddRequestDto;
import com.project.legendsofleague.domain.cartItem.dto.CartItemDeleteRequestDto;
import com.project.legendsofleague.domain.cartItem.dto.CartItemResponseDto;
import com.project.legendsofleague.domain.cartItem.dto.CartItemUpdateRequestDto;
import com.project.legendsofleague.domain.cartItem.service.CartItemService;
import com.project.legendsofleague.domain.member.domain.CurrentMember;
import com.project.legendsofleague.domain.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;


    @Operation(summary = "장바구니 목록을 보여주는 컨트롤러입니다.")
    @GetMapping("/carts")
    public ResponseEntity<List<CartItemResponseDto>> showCartItemList(@CurrentMember Member member) {
        List<CartItemResponseDto> cart = cartItemService.findCartList(member.getId());
        return ResponseEntity.ok(cart);
    }

    @Operation(summary = "장바구니 목록에서 특정 아이템 옵션(수량 등)을 수정하는 컨트롤러입니다.")
    @PostMapping("/cart/{cartItemId}/update")
    public ResponseEntity<List<CartItemResponseDto>> updateCartItem(@CurrentMember Member member, @PathVariable("cartItemId") Long cartItemId, @RequestBody CartItemUpdateRequestDto cartItemUpdateRequestDto) {
        List<CartItemResponseDto> cartItemResponseDtoList = cartItemService.updateCartItem(member.getId(), cartItemUpdateRequestDto);
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
    public ResponseEntity<Long> createCartItem(@CurrentMember Member member, @RequestBody CartItemAddRequestDto cartItemAddRequestDto) {
        Long cartItemId = cartItemService.createCartItem(member.getId(), cartItemAddRequestDto);
        return ResponseEntity.ok(cartItemId);
    }

    @Operation(summary = "장바구니에서 아이템을 삭제하는 컨트롤러입니다.")
    @PostMapping("/cart/delete")
    public ResponseEntity<Void> deleteCartItem(@CurrentMember Member member, @RequestBody List<CartItemDeleteRequestDto> cartItemDeleteRequestList) {
        cartItemService.deleteCartItem(member.getId(), cartItemDeleteRequestList);
        return ResponseEntity.ok(null);
    }
}
