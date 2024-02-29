package com.project.legendsofleague.domain.itemreview.repository;

import static com.project.legendsofleague.domain.item.domain.QItem.item;
import static com.project.legendsofleague.domain.itemreview.domain.QItemReview.itemReview;
import static com.project.legendsofleague.domain.member.domain.QMember.member;

import com.project.legendsofleague.domain.itemreview.domain.ItemReview;
import com.project.legendsofleague.domain.member.domain.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomItemReviewRepositoryImpl implements CustomItemReviewRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ItemReview> queryItemWithItemReview(Long itemId) {
        return queryFactory.selectFrom(itemReview)
            .leftJoin(itemReview.item, item).fetchJoin()
            .leftJoin(itemReview.member, member).fetchJoin()
            .where(item.id.eq(itemId))
            .fetch();
    }

    @Override
    public List<ItemReview> queryItemReviewList(Member findMember) {
        return queryFactory.selectFrom(itemReview)
            .leftJoin(itemReview.item, item).fetchJoin()
            .leftJoin(itemReview.member, member).fetchJoin()
            .where(member.eq(findMember))
            .fetch();
    }
}
