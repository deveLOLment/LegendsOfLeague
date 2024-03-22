package com.project.legendsofleague.domain.itemcomment.service;

import com.project.legendsofleague.common.exception.NotFoundInputValueException;
import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.repository.ItemRepository;
import com.project.legendsofleague.domain.itemcomment.domain.ItemComment;
import com.project.legendsofleague.domain.itemcomment.dto.ItemCommentCreateRequestDto;
import com.project.legendsofleague.domain.itemcomment.dto.ParentItemCommentResponseDto;
import com.project.legendsofleague.domain.itemcomment.repository.ItemCommentRepository;
import com.project.legendsofleague.domain.member.domain.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemCommentService {

    private final ItemCommentRepository itemCommentRepository;
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public List<ParentItemCommentResponseDto> queryItemCommentList(Member member, Long itemId) {
        return itemCommentRepository.queryParentCommentList(itemId).stream()
            .map(ParentItemCommentResponseDto::from)
            .toList();
    }

    @Transactional
    public void createItemComment(Member member, Long itemId,
        ItemCommentCreateRequestDto dto) {

        Item item = itemRepository.findById(itemId).orElseThrow(() -> {
            throw new NotFoundInputValueException();
        });

        ItemComment itemComment = ItemComment.from(dto.getContent(), item, member,
            dto.getParentCommentId());

        itemCommentRepository.save(itemComment);
    }
}
