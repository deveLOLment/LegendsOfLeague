package com.project.legendsofleague.domain.itemreview.controller;

import com.project.legendsofleague.domain.itemreview.dto.ItemReviewCreateRequestDto;
import com.project.legendsofleague.domain.itemreview.dto.ItemReviewInfoResponseDto;
import com.project.legendsofleague.domain.itemreview.dto.ItemReviewResponseDto;
import com.project.legendsofleague.domain.itemreview.service.ItemReviewService;
import com.project.legendsofleague.domain.member.domain.CurrentMember;
import com.project.legendsofleague.domain.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemReviewController {

    private final ItemReviewService itemReviewService;

    @Operation(summary = "해당 아이템의 리뷰 전체 조회")
    @GetMapping("/items/{itemId}/reviews")
    public ResponseEntity<ItemReviewInfoResponseDto> queryItemReviewInfo(
        @CurrentMember Member member, @PathVariable("itemId") Long itemId
    ) {
        ItemReviewInfoResponseDto dto = itemReviewService.queryItemReviewInfo(
            member, itemId);
        return new ResponseEntity<ItemReviewInfoResponseDto>(dto, HttpStatus.OK);
    }

    @Operation(summary = "아이템 리뷰 작성")
    @PostMapping("/items/{itemId}/reviews")
    public ResponseEntity<Void> createItemReview( @RequestBody ItemReviewCreateRequestDto itemReviewCreateRequestDto,
        @CurrentMember Member member, @PathVariable("itemId") Long itemId
    ){
        itemReviewService.createItemReview(itemReviewCreateRequestDto, member, itemId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Operation(summary = "회원이 작성한 리뷰 전체 조회")
    @GetMapping("/item-reviews")
    public ResponseEntity<List<ItemReviewResponseDto>> queryItemReviews(
        @CurrentMember Member member
    ){
        List<ItemReviewResponseDto> dtoList = itemReviewService.queryItemReviews(
            member);
        return new ResponseEntity<List<ItemReviewResponseDto>>(dtoList, HttpStatus.OK);
    }
}
