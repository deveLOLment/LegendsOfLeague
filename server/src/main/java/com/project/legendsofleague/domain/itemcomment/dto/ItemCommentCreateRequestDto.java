package com.project.legendsofleague.domain.itemcomment.dto;

import lombok.Getter;

@Getter
public class ItemCommentCreateRequestDto {

    private String content;
    private Long parentCommentId;

}
