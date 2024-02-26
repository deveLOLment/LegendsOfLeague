package com.project.legendsofleague.domain.order.repository;

import com.project.legendsofleague.domain.order.domain.OrderItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.legendsofleague.domain.item.domain.QItem.item;
import static com.project.legendsofleague.domain.member.domain.QMember.member;
import static com.project.legendsofleague.domain.order.domain.QOrder.order;
import static com.project.legendsofleague.domain.order.domain.QOrderItem.orderItem;


@Repository
@RequiredArgsConstructor
public class CustomOrderItemRepositoryImpl implements CustomOrderItemRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<OrderItem> queryOrderItemByOrder(Long orderId) {
        //order가 null이어도 가지고 온다.
        return queryFactory.selectFrom(orderItem)
                .leftJoin(orderItem.order, order).fetchJoin()
                .leftJoin(orderItem.item, item).fetchJoin()
                .leftJoin(order.member, member).fetchJoin()
                .where(orderItem.order.id.eq(orderId))
                .fetch();
    }
}
