package com.project.legendsofleague.domain.itemreview.repository;

import com.project.legendsofleague.domain.itemreview.domain.ItemReview;
import com.project.legendsofleague.domain.member.domain.Member;
import java.util.List;

public interface CustomItemReviewRepository {

    List<ItemReview> queryItemWithItemReview(Long itemId);

    List<ItemReview> queryItemReviewList(Member member);
}
