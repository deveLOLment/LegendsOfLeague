package com.project.legendsofleague.domain.cartItem.repository;

import com.project.legendsofleague.domain.cartItem.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long>, CustomCartItemRepository {
}
