package com.project.legendsofleague.domain.item.controller;


import com.project.legendsofleague.domain.item.dto.ItemRequestDto;
import com.project.legendsofleague.domain.item.dto.ItemSelectResponseDto;
import com.project.legendsofleague.domain.item.service.ItemService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;


    @PostMapping("/item/test")
    public String itemTest(@RequestBody ItemRequestDto itemRequestDto) throws IOException {
        itemService.saveItem(itemRequestDto);

        return "hello";
    }

    @GetMapping("/items/categories")
    public ResponseEntity<List<String>> getCategories() {
        List<String> categories = itemService.getCategories();
        return new ResponseEntity<List<String>>(categories, HttpStatus.OK);
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemSelectResponseDto>> getItemSelectList() {
        List<ItemSelectResponseDto> itemSelectList = itemService.getItemSelectList();
        return new ResponseEntity<List<ItemSelectResponseDto>>(itemSelectList, HttpStatus.OK);
    }
}
