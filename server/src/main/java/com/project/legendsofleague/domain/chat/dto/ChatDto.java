package com.project.legendsofleague.domain.chat.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 채팅 메시지에 대한 DTO입니다.
 */
@Data
@Builder
public class ChatDto {

    private MessageType type; // 메시지 타입
    private String roomId; // 방 아이디: Match 아이디
    private String sender; // 채팅을 보낸 사람
    private String content; // 메시지
    private LocalDateTime time; // 채팅 발송 시간

}
