package com.project.legendsofleague.domain.chat.repository;

import com.project.legendsofleague.domain.chat.dto.ChatDto;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class ChatRepository {

    List<ChatDto> chatList;

    @PostConstruct
    public void init() {
        chatList = new ArrayList<>();
    }

    public void save(ChatDto chat) {
        chatList.add(chat);
    }

    public List<ChatDto> findPreviousChats() {
        return chatList;
    }
}
