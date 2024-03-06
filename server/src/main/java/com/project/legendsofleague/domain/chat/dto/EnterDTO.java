package com.project.legendsofleague.domain.chat.dto;

import lombok.Data;

import java.util.List;

/**
 * 채팅방 입장 정보를 담은 DTO입니다.
 */
@Data
public class EnterDTO {

    private String username; // 사용자 이름
    private List<ChatDto> previousChat; // 이전 채팅 기록

}
