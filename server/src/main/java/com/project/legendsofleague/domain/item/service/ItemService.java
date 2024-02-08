package com.project.legendsofleague.domain.item.service;

import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.domain.ItemImage;
import com.project.legendsofleague.domain.item.dto.ItemRqDto;
import com.project.legendsofleague.domain.item.repository.ItemRepository;
import com.project.legendsofleague.util.S3Util;
import java.util.List;
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
  public void saveItem(ItemRqDto itemRqDto) {
    ItemImage tempThumbnailImage = s3Util.saveFile(itemRqDto.getThumbnailImage());
    String thumbnailImage = tempThumbnailImage.getImageUrl();
    List<ItemImage> itemImages = s3Util.saveFiles(itemRqDto.getItemImages());
    Item item = Item.toEntity(itemRqDto, thumbnailImage);

    itemRepository.save(item);
    itemImageService.saveItemImage(itemImages, item);
  }
}
