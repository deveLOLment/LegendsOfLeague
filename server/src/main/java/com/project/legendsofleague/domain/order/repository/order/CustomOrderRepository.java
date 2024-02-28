package com.project.legendsofleague.domain.order.repository.order;

import com.project.legendsofleague.domain.purchase.domain.Purchase;

import java.util.List;

public interface CustomOrderRepository {

    List<Purchase> queryOrderByMember(Long memberId);
}
