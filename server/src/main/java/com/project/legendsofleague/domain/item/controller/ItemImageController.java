package com.project.legendsofleague.domain.item.controller;


import com.project.legendsofleague.domain.item.service.ItemImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemImageController {

  private final ItemImageService itemImageService;
}
