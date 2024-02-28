package com.project.legendsofleague.domain.itemreview.domain;

import com.project.legendsofleague.common.BaseEntity;
import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.itemreview.dto.ItemReviewCreateRequestDto;
import com.project.legendsofleague.domain.member.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Integer score;

    private LocalDateTime createDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static ItemReview from(ItemReviewCreateRequestDto requestDto, Long itemId, Member member) {
        ItemReview itemReview = new ItemReview();
        itemReview.content = requestDto.getContent();
        itemReview.score = requestDto.getScore();
        itemReview.item = new Item(itemId);
        itemReview.member = member;
        itemReview.createDate = LocalDateTime.now();
        return itemReview;
    }

}
