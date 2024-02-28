package com.project.legendsofleague.domain.itemcomment.controller;

import com.project.legendsofleague.domain.itemcomment.dto.ItemCommentCreateRequestDto;
import com.project.legendsofleague.domain.itemcomment.dto.ParentItemCommentResponseDto;
import com.project.legendsofleague.domain.itemcomment.service.ItemCommentService;
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

@RequiredArgsConstructor
@RestController
public class ItemCommentController {

    private final ItemCommentService itemCommentService;

    @Operation(summary = "해당 아이템의 모든 댓글 조회")
    @GetMapping("/items/{itemId}/comments")
    public ResponseEntity<List<ParentItemCommentResponseDto>> queryItemCommentList(@CurrentMember Member member, @PathVariable("itemId") Long itemId) {
        List<ParentItemCommentResponseDto> dtoList = itemCommentService.queryItemCommentList(
            member, itemId);
        return new ResponseEntity<List<ParentItemCommentResponseDto>>(dtoList, HttpStatus.OK);
    }

    @Operation(summary = "해당 아이템에 리뷰 작성")
    @PostMapping("/items/{itemId}/comments")
    public ResponseEntity<Void> createItemComment(@CurrentMember Member member, @PathVariable("itemId") Long itemId, @RequestBody
        ItemCommentCreateRequestDto itemCommentCreateRequestDto) {
        itemCommentService.createItemComment(member, itemId, itemCommentCreateRequestDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
