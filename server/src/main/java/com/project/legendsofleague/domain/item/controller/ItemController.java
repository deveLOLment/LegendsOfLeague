package com.project.legendsofleague.domain.item.controller;


import com.project.legendsofleague.domain.item.dto.ItemRqDto;
import com.project.legendsofleague.domain.item.service.ItemService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemController {

  private final ItemService itemService;

  //  @PostMapping("/item/test")
//  public ResponseEntity<Item> itemTest(@RequestBody ItemRqDto itemRqDto) {
//    Item item = Item.toEntity(new ItemRqDto());
//    System.out.println("hello~");
//    log.info("item={}", itemRqDto.getName());
//    return new ResponseEntity<>(HttpStatus.OK);
//  }

  @PostMapping("/item/test")
  public String itemTest(@RequestBody ItemRqDto itemRqDto) throws IOException {
    itemService.saveItem(itemRqDto);

    return "hello";
  }
}
