package com.project.legendsofleague.domain.item.service;

import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.domain.ItemCategory;
import com.project.legendsofleague.domain.item.domain.ItemImage;
import com.project.legendsofleague.domain.item.dto.ItemRequestDto;
import com.project.legendsofleague.domain.item.dto.ItemSelectResponseDto;
import com.project.legendsofleague.domain.item.repository.ItemRepository;
import com.project.legendsofleague.util.S3Util;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final S3Util s3Util;
    private final ItemImageService itemImageService;

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
}
