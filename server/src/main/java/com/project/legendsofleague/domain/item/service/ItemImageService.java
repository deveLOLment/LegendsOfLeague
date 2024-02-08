package com.project.legendsofleague.domain.item.service;


import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.domain.ItemImage;
import com.project.legendsofleague.domain.item.repository.ItemImageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemImageService {

  private final ItemImageRepository itemImageRepository;

  @Transactional
  public void saveItemImage(List<ItemImage> itemImages, Item item) {
    for (ItemImage itemImage : itemImages) {
      itemImage.connectItem(item);
      itemImageRepository.save(itemImage);
    }
  }
}
