package com.project.legendsofleague.domain.item.service;

import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.domain.ItemCategory;
import com.project.legendsofleague.domain.item.domain.ItemImage;
import com.project.legendsofleague.domain.item.domain.QItem;
import com.project.legendsofleague.domain.item.dto.ItemDetailResponseDto;
import com.project.legendsofleague.domain.item.dto.ItemListResponseDto;
import com.project.legendsofleague.domain.item.dto.ItemRequestDto;
import com.project.legendsofleague.domain.item.dto.ItemSelectResponseDto;
import com.project.legendsofleague.domain.item.dto.page.PageRequestDto;
import com.project.legendsofleague.domain.item.dto.page.PageResponseDto;
import com.project.legendsofleague.domain.item.repository.ItemRepository;
import com.project.legendsofleague.util.S3Util;
import com.querydsl.core.BooleanBuilder;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    public PageResponseDto getAllPage(PageRequestDto pageRequestDto) {
        Pageable pageable = pageRequestDto.getPageable(Sort.by(pageRequestDto.getSort()));
        BooleanBuilder booleanBuilder = getSearch(pageRequestDto);

        Page<ItemListResponseDto> itemInfoPage = itemRepository.findAll(booleanBuilder, pageable)
                .map(ItemListResponseDto::toDto);

        return new PageResponseDto(pageable, itemInfoPage);
    }

    private BooleanBuilder getSearch(PageRequestDto pageRequestDto) {
        String category = pageRequestDto.getCategory();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QItem qItem = QItem.item;
        String keyword = pageRequestDto.getKeyword();

        // 검색어가 없는 경우
        if (StringUtils.isEmpty(keyword)) {
            // 카테고리가 전체인 경우는 추가적인 조건 없이 반환
            if (StringUtils.isEmpty(category)) {
                return booleanBuilder;
            }
        }

        // 검색 조건이 있는 경우
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        // 카테고리 조건 추가
        if (!StringUtils.isEmpty(category)) {
            conditionBuilder.and(qItem.category.eq(ItemCategory.valueOf(category)));
        }

        // 키워드 조건 추가
        if (!StringUtils.isEmpty(keyword)) {
            conditionBuilder.and(qItem.name.contains(keyword)
                    .or(qItem.description.contains(keyword)));
        }

        // 모든 조건 통합
        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
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
