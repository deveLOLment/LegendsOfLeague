package com.project.legendsofleague.domain.item.controller;


import com.project.legendsofleague.domain.item.dto.ItemDetailResponseDto;
import com.project.legendsofleague.domain.item.dto.ItemRequestDto;
import com.project.legendsofleague.domain.item.dto.ItemSelectResponseDto;
import com.project.legendsofleague.domain.item.dto.page.PageRequestDto;
import com.project.legendsofleague.domain.item.dto.page.PageResponseDto;
import com.project.legendsofleague.domain.item.service.ItemService;
import com.project.legendsofleague.domain.member.domain.CurrentMember;
import com.project.legendsofleague.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

//    @GetMapping("/shop")
//    public ResponseEntity<PageResponseDto> showItemList(@RequestParam PageRequestDto pageRequestDto) {
//        PageResponseDto allPage = itemService.getAllPage(pageRequestDto);
//
//
//        return ResponseEntity.ok(allPage);
//    }


    @GetMapping("/shop")
    public ResponseEntity<PageResponseDto> showItemList(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                        @RequestParam(name = "sort", defaultValue = "createdTime", required = false) String sort,
                                                        @RequestParam(name = "category", required = false) String category,
                                                        @RequestParam(name = "keyword", required = false) String keyword,
                                                        @RequestParam(name = "order", defaultValue = "desc", required = false) String order) {
        PageRequestDto pageRequestDto = new PageRequestDto(page, sort, category, keyword, order);
        PageResponseDto allPage = itemService.getAllPage(pageRequestDto);

        log.info("=====================================================");
        log.info("page={}", page);
        log.info("sort={}", sort);
        log.info("category={}", category);
        log.info("keyword={}", keyword);
        log.info("order={}", order);
        log.info("=====================================================");


        return ResponseEntity.ok(allPage);
    }


    @PostMapping("/item/test")
    public String itemTest(@RequestBody ItemRequestDto itemRequestDto) throws IOException {
        itemService.saveItem(itemRequestDto);

        return "hello";
    }

    @GetMapping("/items/categories")
    public ResponseEntity<List<String>> getCategories(@CurrentMember Member member) {
        List<String> categories = itemService.getCategories();
        return new ResponseEntity<List<String>>(categories, HttpStatus.OK);
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemSelectResponseDto>> getItemSelectList(@CurrentMember Member member) {
        List<ItemSelectResponseDto> itemSelectList = itemService.getItemSelectList();
        return new ResponseEntity<List<ItemSelectResponseDto>>(itemSelectList, HttpStatus.OK);
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<ItemDetailResponseDto> getItemDetail(@CurrentMember Member member, @PathVariable("itemId") Long itemId) {
        ItemDetailResponseDto itemDetail = itemService.getItemDetail(itemId);

        return ResponseEntity.ok(itemDetail);
    }
}
