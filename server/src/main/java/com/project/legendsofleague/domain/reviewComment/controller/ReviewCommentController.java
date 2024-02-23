package com.project.legendsofleague.domain.reviewComment.controller;

import com.project.legendsofleague.domain.member.domain.CurrentMember;
import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.reviewComment.dto.ReviewCommentRequest;
import com.project.legendsofleague.domain.reviewComment.service.ReviewCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviewComment")
public class ReviewCommentController {

    private final ReviewCommentService reviewCommentService;
    
    //TODO Member 필요
    @PostMapping
    public String writeReviewComment(@CurrentMember Member member, @RequestBody ReviewCommentRequest reviewCommentRequest) {
        reviewCommentService.saveReviewComment(member, reviewCommentRequest);
        return "ok";
    }
}
