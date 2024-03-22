package com.project.legendsofleague.domain.itemcomment.dto;

import com.project.legendsofleague.domain.itemcomment.domain.ItemComment;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ParentItemCommentResponseDto {

    private Long id;
    private String content;
    private String nickname;
    private LocalDateTime createdDate;
    private List<ChildItemCommentResponseDto> childCommentList = new ArrayList<>();

    public static ParentItemCommentResponseDto from(ItemComment itemComment){
        ParentItemCommentResponseDto parentItemCommentResponseDto = new ParentItemCommentResponseDto();
        parentItemCommentResponseDto.id = itemComment.getId();
        parentItemCommentResponseDto.content = itemComment.getContent();
        parentItemCommentResponseDto.nickname = itemComment.getMember().getNickname();
        parentItemCommentResponseDto.createdDate = itemComment.getCreatedDate();

        parentItemCommentResponseDto.childCommentList = itemComment.getChildCommentList().stream()
            .map(ChildItemCommentResponseDto::from)
            .collect(Collectors.toList());

        return parentItemCommentResponseDto;
    }
}
