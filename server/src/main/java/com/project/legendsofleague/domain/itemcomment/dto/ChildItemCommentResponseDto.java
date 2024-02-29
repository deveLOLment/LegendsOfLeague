package com.project.legendsofleague.domain.itemcomment.dto;

import com.project.legendsofleague.domain.itemcomment.domain.ItemComment;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ChildItemCommentResponseDto {

    private Long id;
    private String content;
    private String nickname;
    private LocalDateTime createdDate;
    private Long parentCommentId;

    public static ChildItemCommentResponseDto from(ItemComment itemComment){

        ChildItemCommentResponseDto childItemCommentResponseDto = new ChildItemCommentResponseDto();
        childItemCommentResponseDto.id = itemComment.getId();
        childItemCommentResponseDto.content = itemComment.getContent();
        childItemCommentResponseDto.nickname = itemComment.getMember().getNickname();
        childItemCommentResponseDto.createdDate = itemComment.getCreatedDate();
        childItemCommentResponseDto.parentCommentId = itemComment.getParentComment().getId();
        return childItemCommentResponseDto;
    }

}
