package com.project.legendsofleague.domain.order.repository.order;

import com.project.legendsofleague.domain.purchase.domain.Purchase;
import java.util.List;
import java.util.Optional;

public interface CustomOrderRepository {

    List<Purchase> queryOrderByMember(Long memberId);

    Optional<Purchase> queryOrderByOrderId(Long orderId);
}
