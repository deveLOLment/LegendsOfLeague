package com.project.legendsofleague.domain.item.controller;


import com.project.legendsofleague.domain.item.dto.ItemDetailResponseDto;
import com.project.legendsofleague.domain.item.dto.ItemListResponseDto;
import com.project.legendsofleague.domain.item.dto.ItemRequestDto;
import com.project.legendsofleague.domain.item.dto.ItemSelectResponseDto;
import com.project.legendsofleague.domain.item.service.ItemService;
import com.project.legendsofleague.domain.member.domain.CurrentMember;
import com.project.legendsofleague.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/shop")
    public ResponseEntity<Page<ItemListResponseDto>> showItemList(@CurrentMember Member member,
                                                                  @PageableDefault(size = 15, direction = Sort.Direction.DESC) Pageable pageable,
                                                                  @RequestParam(name = "keyword", required = false) String keyword,
                                                                  @RequestParam(name = "category", required = false) String category,
                                                                  @RequestParam(name = "sort", required = false) String sort,
                                                                  @RequestParam(name = "order", required = false) String order) {
        return null;
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
