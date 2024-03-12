package com.project.legendsofleague.domain.itemreview.service;

import com.project.legendsofleague.domain.itemreview.domain.ItemReview;
import com.project.legendsofleague.domain.itemreview.dto.ItemReviewCreateRequestDto;
import com.project.legendsofleague.domain.itemreview.dto.ItemReviewInfoResponseDto;
import com.project.legendsofleague.domain.itemreview.dto.ItemReviewResponseDto;
import com.project.legendsofleague.domain.itemreview.repository.ItemReviewRepository;
import com.project.legendsofleague.domain.member.domain.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ItemReviewService {

    private final ItemReviewRepository itemReviewRepository;


    @Transactional(readOnly = true)
    public ItemReviewInfoResponseDto queryItemReviewInfo(Member member, Long itemId) {
        int[] reviewArr = new int[5];
        List<ItemReviewResponseDto> itemReviewResponseDtoList = new ArrayList<>();
        List<ItemReview> itemReviewList = itemReviewRepository.queryItemWithItemReview(itemId);

        Integer sum = 0;
        for (ItemReview itemReview : itemReviewList) {
            Integer score = itemReview.getScore();
            if(score <= 0 || score > 5) continue;
            sum += score;
            reviewArr[score - 1]++;
            itemReviewResponseDtoList.add(new ItemReviewResponseDto(itemReview));
        }


        double overall = 0;
        if(itemReviewList.size() != 0){
            overall = (double) sum / itemReviewList.size();
        }

        overall = overall * 100 / 100.0; // 소수점 두번째 자리까지 표현

        return new ItemReviewInfoResponseDto(overall, reviewArr, itemReviewResponseDtoList);
    }

    @Transactional
    public void createItemReview(ItemReviewCreateRequestDto itemReviewCreateRequestDto, Member member, Long itemId) {

        //TODO : 어떤 회원만 리뷰를 작성하게 허용할지 선택

        ItemReview itemReview = ItemReview.from(itemReviewCreateRequestDto, itemId, member);
        itemReviewRepository.save(itemReview);
    }

    public List<ItemReviewResponseDto> queryItemReviews(Member member) {
        return itemReviewRepository.queryItemReviewList(member).stream()
            .map(ItemReviewResponseDto::new)
            .collect(Collectors.toList());
    }
}
