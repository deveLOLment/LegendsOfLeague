package com.project.legendsofleague.domain.itemcomment.repository;

import static com.project.legendsofleague.domain.item.domain.QItem.item;
import static com.project.legendsofleague.domain.itemcomment.domain.QItemComment.itemComment;
import static com.project.legendsofleague.domain.member.domain.QMember.member;

import com.project.legendsofleague.domain.itemcomment.domain.ItemComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomItemCommentRepositoryImpl implements CustomItemCommentRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ItemComment> queryParentCommentList(Long itemId) {
        return queryFactory.selectFrom(itemComment).distinct()
            .leftJoin(itemComment.item, item).fetchJoin()
            .leftJoin(itemComment.member, member).fetchJoin()
            .leftJoin(itemComment.childCommentList).fetchJoin()
            .where(itemComment.item.id.eq(itemId))
            .where(itemComment.parentComment.isNull())
            .fetch();
    }
}
