package com.project.legendsofleague.domain.item.dto.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Getter
public class PageRequestDto {
    private int page;
    private int size;
    private String sort; //정렬 기준 (기본값은 생성일자)
    private String category; //카테고리
    private String keyword; //검색
    private String order; //오름차순, 내림차순

    public PageRequestDto(int page, String sort, String category, String keyword, String order) {
        this.page = page;
        this.size = 15;
        this.sort = sort;
        this.category = category;
        this.keyword = keyword;
        this.order = order;
    }

    public PageRequestDto() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {
        if (this.order.equals("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return PageRequest.of(page - 1, size, sort);
    }
}
