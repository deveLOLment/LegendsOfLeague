package com.project.legendsofleague.domain.cartItem.service;


import com.project.legendsofleague.domain.cartItem.domain.CartItem;
import com.project.legendsofleague.domain.cartItem.dto.CartItemAddRequestDto;
import com.project.legendsofleague.domain.cartItem.dto.CartItemDeleteRequestDto;
import com.project.legendsofleague.domain.cartItem.dto.CartItemResponseDto;
import com.project.legendsofleague.domain.cartItem.dto.CartItemUpdateRequestDto;
import com.project.legendsofleague.domain.cartItem.repository.CartItemRepository;
import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.repository.ItemRepository;
import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.order.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;


    public List<CartItem> getCartItemList(Long memberId) {
        return cartItemRepository.queryCartItemByMember(memberId);
    }

    public CartItem getCartItem(Long cartItemId) {
        return cartItemRepository.queryCartItem(cartItemId);
    }

    /**
     * 장바구니 목록을 보여주기 위한 메소드
     *
     * @param member
     * @return
     */
    public List<CartItemResponseDto> findCartList(Member member) {
        return cartItemRepository.queryCartItemByMember(member.getId())
                .stream().map(CartItemResponseDto::toDto).toList();
    }

    /**
     * 장바구니에서 특정 아이템의 정보를 수정하기 위한 메소드
     *
     * @param member
     * @param cartItemUpdateRequestDto
     * @return
     */
    @Transactional
    public List<CartItemResponseDto> updateCartItem(Member member, CartItemUpdateRequestDto cartItemUpdateRequestDto) {
        CartItem cartItem = getCartItem(cartItemUpdateRequestDto.getCartItemId());

        //요청한 사용자와 장바구니의 주인이 같지 않다면
        if (!cartItem.getMember().getId().equals(member.getId())) {
            throw new RuntimeException("유효하지 않은 요청입니다.");
        }
        cartItem.updateCartItem(cartItemUpdateRequestDto);

        return cartItemRepository.queryCartItemByMember(member.getId())
                .stream().map(CartItemResponseDto::toDto).toList();
    }

    /**
     * 장바구니에 목록 추가, but 장바구니에 같은 상품이 있으면 예외를 날린다.
     * 쿼리 개수: 3 (아이템 검증, cartItem 검증, insert)
     *
     * @param member
     * @param cartItemAddRequestDto
     * @return
     */
    @Transactional
    public Long createCartItem(Member member, CartItemAddRequestDto cartItemAddRequestDto) {
        Item item = itemRepository.findById(cartItemAddRequestDto.getItemId())
                .orElseThrow(() -> new NotFoundException("유효하지 않은 상품입니다."));

        List<CartItem> cartItems = getCartItemList(member.getId());

        if (checkDuplicateCartItem(item, cartItems)) {
            //장바구니에 같은 아이템이 없다면
            CartItem cartItem = CartItem.toEntity(member, item, cartItemAddRequestDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        } else {
            throw new RuntimeException("이미 장바구니에 아이템이 있습니다.");
        }
    }

    /**
     * 장바구니 목록에서 사용자가 삭제를 원하는 아이템 목록을 삭제하기 위한 로직
     *
     * @param member
     * @param cartItemDeleteRequestList
     */
    @Transactional
    public void deleteCartItem(Member member, List<CartItemDeleteRequestDto> cartItemDeleteRequestList) {
        List<CartItem> cartItems = getCartItemList(member.getId());
        List<Long> cartItemIds = cartItems.stream()
                .map(CartItem::getId).toList();

        //요청한 장바구니 삭제가 유효하다면 삭제 진행
        if (checkCart(cartItemIds, cartItemDeleteRequestList)) {
            for (CartItemDeleteRequestDto cartItemDeleteRequestDto : cartItemDeleteRequestList) {
                cartItemRepository.deleteById(cartItemDeleteRequestDto.getCartItemId());
            }
        } else {
            //장바구니에 없는 목록을 삭제 시도함
            throw new RuntimeException("유효하지 않은 요청입니다.");
        }
    }


    /**
     * 사용자가 장바구니에 없는 목록을 삭제 시도하는 지 체크하기 위한 메소드
     *
     * @return
     */
    private boolean checkCart(List<Long> cartItemIds, List<CartItemDeleteRequestDto> cartItemDeleteRequestList) {
        for (CartItemDeleteRequestDto cartItemDeleteRequestDto : cartItemDeleteRequestList) {
            if (!cartItemIds.contains(cartItemDeleteRequestDto.getCartItemId())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 주문, 결제가 완료된 이후에 결제 아이템 중 장바구니 목록에 포함되어 있는 것이 있다면, 장바구니에서 해당 아이템을 삭제한다.
     * 예상 쿼리: 1+n개 (cartItem 검증, 삭제하는 쿼리) n은 장바구니에 있는 물품 중 주문이 된 아이템의 개수
     *
     * @param member
     * @param orderItems
     */
    @Transactional
    public void deleteOrderedCartItem(Member member, List<OrderItem> orderItems) {

        List<CartItem> cartItems = getCartItemList(member.getId());
        List<Item> items = orderItems.stream()
                .map(OrderItem::getItem).toList();

        if (cartItems.isEmpty()) {
            return;
        }
        for (CartItem cartItem : cartItems) {
            if (items.contains(cartItem.getItem())) {
                cartItemRepository.deleteById(cartItem.getId());
            }
        }
    }

    /**
     * 장바구니에 이미 같은 아이템이 저장되어 있다면 false를 반환
     *
     * @param item
     * @param cartItems
     * @return
     */
    private boolean checkDuplicateCartItem(Item item, List<CartItem> cartItems) {
        Long itemId = item.getId();
        for (CartItem cartItem : cartItems) {
            Long id = cartItem.getItem().getId();
            if (itemId.equals(id)) {
                return false;
            }
        }

        return true;
    }
}
