package com.project.legendsofleague.domain.item.dto;


import lombok.Getter;

import java.util.List;


/**
 * 아이템 생성을 위한 dto
 */
@Getter
public class ItemRequestDto {

    private Long id;

    private String name; //상품 이름

    private Integer price; //상품 가격

    private Integer stock; //상품 재고 (남은 수량)

    private String description; //상품 설명

    private String category;

    /**
     * 이미지 파일이 base64 인코딩 된 문자열로 요청을 보낸다.
     */
    private String thumbnailImage;

    /**
     * 이미지 파일이 base64 인코딩 된 문자열로 요청을 보낸다.
     */
    private List<String> itemImages;

    private boolean isDeleted = false;
}
