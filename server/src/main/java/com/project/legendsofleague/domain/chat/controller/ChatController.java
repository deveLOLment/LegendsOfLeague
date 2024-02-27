package com.project.legendsofleague.domain.chat.controller;

import com.project.legendsofleague.domain.chat.dto.ChatDto;
import com.project.legendsofleague.domain.chat.dto.SenderDTO;
import com.project.legendsofleague.domain.chat.repository.ChatRepository;
import com.project.legendsofleague.domain.member.domain.CurrentMember;
import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.member.dto.CustomMemberDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {

    /**
     * WebSocket 메시지 전송을 위한 템플릿
     */
    private final SimpMessageSendingOperations template;

    /**
     * 채팅 관련 데이터처리 리포지러티
     */
    private final ChatRepository repository;

    @GetMapping("/chat/previousChat")
    public ResponseEntity<List<ChatDto>> getPreviousChat() {
        log.info("getPreviousChat 호출");

        List<ChatDto> previousChats = repository.findPreviousChats();

        return new ResponseEntity<>(previousChats, HttpStatus.OK);
    }

    @GetMapping("/chat/getUser")
    public ResponseEntity<SenderDTO> getUser(@CurrentMember Member member) {
        log.info("getUser 호출");

        SenderDTO senderDTO = new SenderDTO();
        senderDTO.setUsername(member.getUsername());

        return new ResponseEntity<>(senderDTO, HttpStatus.OK);
    }

    /**
     * WebSocket 으로 들어온 메시지를 처리하는 메서드입니다.
     * 사용자가 채팅에 참여할 때 호출됩니다.
     *
     * @param chat WebSocket 으로 받은 채팅 정보
     */
    @MessageMapping("/chat/enterUser")
    public void enterUser(@Payload ChatDto chat) {
        log.info("chatDto = {}", chat);

        repository.save(chat);

        template.convertAndSend("/sub/chat", chat);
    }

    /**
     * WebSocket 으로 받은 메시지를 그대로 전송하는 메서드입니다.
     *
     * @param chat WebSocket 으로 받은 채팅 정보
     */
    @MessageMapping("/chat/sendMessage")
    public void sendMessage(@Payload ChatDto chat) {
        log.info("chatDto = {}", chat);

        chat.setTime(LocalDateTime.now());
        repository.save(chat);

        template.convertAndSend("/sub/chat", chat);
    }

}
