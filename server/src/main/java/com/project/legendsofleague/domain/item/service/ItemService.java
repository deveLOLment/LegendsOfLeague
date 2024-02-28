package com.project.legendsofleague.domain.item.service;

import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.domain.ItemCategory;
import com.project.legendsofleague.domain.item.domain.ItemImage;
import com.project.legendsofleague.domain.item.dto.*;
import com.project.legendsofleague.domain.item.repository.ItemRepository;
import com.project.legendsofleague.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final S3Util s3Util;
    private final ItemImageService itemImageService;

    public Item getItem(Long itemId) {
        return itemRepository.queryItemById(itemId);
    }

    public PageResponse searchPaging(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<Item> itemPage = itemRepository.findAll(pageable);

        //페이징에서 아이템 값만 가져오기
        List<ItemListResponseDto> content = itemPage.getContent().stream()
                .map(ItemListResponseDto::toDto).toList();

        return PageResponse.builder()
                .content(content)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalElements(itemPage.getTotalElements())
                .totalPages(itemPage.getTotalPages())
                .last(itemPage.isLast())
                .build();
    }

    @Transactional
    public void saveItem(ItemRequestDto itemRequestDto) {
        ItemImage tempThumbnailImage = s3Util.saveFile(itemRequestDto.getThumbnailImage());
        String thumbnailImage = tempThumbnailImage.getImageUrl();
        List<ItemImage> itemImages = s3Util.saveFiles(itemRequestDto.getItemImages());
        Item item = Item.toEntity(itemRequestDto, thumbnailImage);

        itemRepository.save(item);
        itemImageService.saveItemImage(itemImages, item);
    }

    public List<String> getCategories() {
        return Arrays.stream(ItemCategory.values()).map(ItemCategory::name)
                .collect(Collectors.toList());
    }

    public List<ItemSelectResponseDto> getItemSelectList() {
        return itemRepository.findAll()
                .stream()
                .map(ItemSelectResponseDto::new)
                .collect(Collectors.toList());
    }


    public ItemDetailResponseDto getItemDetail(Long itemId) {
        Item item = getItem(itemId);

        //존재하지 않은 아이템이라면
        if (item == null) {
            throw new NotFoundException("존재하지 않은 아이템입니다.");
        }

        ItemDetailResponseDto itemDetailResponseDto = ItemDetailResponseDto.toDto(item);

        return itemDetailResponseDto;
    }
}
