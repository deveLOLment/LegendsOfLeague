package com.project.legendsofleague.domain.order.repository.order;


import com.project.legendsofleague.domain.order.domain.Order;
import com.project.legendsofleague.domain.order.domain.OrderStatus;
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
public class CustomOrderRepositoryImpl implements CustomOrderRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Order> queryOrderByMember(Long memberId) {
        return queryFactory.selectFrom(order)
                .leftJoin(order.member, member).fetchJoin()
                .leftJoin(order.orderItemList, orderItem).fetchJoin()
                .leftJoin(orderItem.item, item).fetchJoin()
                .where(order.member.id.eq(memberId)
                        .and(order.orderStatus.eq(OrderStatus.SUCCESS)
                                .or(order.orderStatus.eq(OrderStatus.REFUND))))
                .fetch();
    }
}
