package com.project.legendsofleague.domain.item.repository;

import com.project.legendsofleague.domain.item.domain.Item;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.project.legendsofleague.domain.item.domain.QItem.item;

@Repository
@RequiredArgsConstructor
public class CustomItemRepositoryImpl implements CustomItemRepository {
    private final JPAQueryFactory queryFactory;


    @Override
    public Item queryItemById(Long itemId) {
        return queryFactory
                .selectFrom(item)
                .leftJoin(item.itemImageList).fetchJoin()
                .where(item.id.eq(itemId))
                .fetchOne();
    }
}
