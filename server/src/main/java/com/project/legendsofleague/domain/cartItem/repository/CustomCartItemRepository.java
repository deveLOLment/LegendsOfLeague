package com.project.legendsofleague.domain.cartItem.repository;


import com.project.legendsofleague.domain.cartItem.domain.CartItem;

import java.util.List;

public interface CustomCartItemRepository {

    List<CartItem> queryCartItemByMember(Long memberId);

    CartItem queryCartItem(Long cartItemId);
}
