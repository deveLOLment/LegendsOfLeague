package com.project.legendsofleague.domain.order.repository.order;


import static com.project.legendsofleague.domain.member.domain.QMember.member;
import static com.project.legendsofleague.domain.order.domain.QOrder.order;
import static com.project.legendsofleague.domain.purchase.domain.QPurchase.purchase;

import com.project.legendsofleague.domain.order.domain.OrderStatus;
import com.project.legendsofleague.domain.purchase.domain.Purchase;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomOrderRepositoryImpl implements CustomOrderRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Purchase> queryOrderByMember(Long memberId) {
        return queryFactory.selectFrom(purchase).distinct()
            .leftJoin(purchase.order, order).fetchJoin()
            .leftJoin(order.orderItemList).fetchJoin()
//                .leftJoin(orderItem.item, item).fetchJoin()
            .leftJoin(order.member, member).fetchJoin()
            .where(order.member.id.eq(memberId)
                .and(order.orderStatus.eq(OrderStatus.SUCCESS)
                    .or(order.orderStatus.eq(OrderStatus.REFUND))))
            .fetch();
    }
}
