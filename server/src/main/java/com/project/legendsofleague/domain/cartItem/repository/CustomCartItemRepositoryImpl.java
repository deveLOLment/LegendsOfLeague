package com.project.legendsofleague.domain.cartItem.repository;


import com.project.legendsofleague.domain.cartItem.domain.CartItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.legendsofleague.domain.cartItem.domain.QCartItem.cartItem;
import static com.project.legendsofleague.domain.item.domain.QItem.item;
import static com.project.legendsofleague.domain.member.domain.QMember.member;

@Repository
@RequiredArgsConstructor
public class CustomCartItemRepositoryImpl implements CustomCartItemRepository {

    private final JPAQueryFactory queryFactory;


    /**
     * cartItem 리스트를 가져오되, 연관된 멤버, 아이템까지 fetch join을 통해 함께 가져온다.
     *
     * @param memberId
     * @return
     */
    @Override
    public List<CartItem> queryCartItemByMember(Long memberId) {
        return queryFactory
                .selectFrom(cartItem)
                .leftJoin(cartItem.member, member).fetchJoin()
                .leftJoin(cartItem.item, item).fetchJoin()
                .where(cartItem.member.id.eq(memberId))
                .fetch();
    }

    @Override
    public CartItem queryCartItem(Long cartItemId) {
        return queryFactory
                .selectFrom(cartItem)
                .leftJoin(cartItem.member, member).fetchJoin()
                .leftJoin(cartItem.item, item).fetchJoin()
                .where(cartItem.id.eq(cartItemId))
                .fetchOne();
    }
}
