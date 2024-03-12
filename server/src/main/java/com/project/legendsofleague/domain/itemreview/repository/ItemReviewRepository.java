package com.project.legendsofleague.domain.itemreview.repository;

import com.project.legendsofleague.domain.itemreview.domain.ItemReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemReviewRepository extends JpaRepository<ItemReview, Long>,
    CustomItemReviewRepository {

}
