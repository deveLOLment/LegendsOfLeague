package com.project.legendsofleague.domain.item.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Builder
public class PageResponseDto<T> {

    private List<T> content;
    private int totalPage;

    private int page;
    private int size;
    private long totalCount;
    private int start, end;
    private boolean prev, next;
    private List<Integer> pageList;


    public static <T> PageResponseDto<T> of(Page<T> pageInfo) {
        Pageable pageable = pageInfo.getPageable();
        int totalPage = pageInfo.getTotalPages();

        int page = pageable.getPageNumber() + 1;   // 0부터 시작하므로 1을 추가
        int size = pageable.getPageSize();

        int tempEnd = (int) (Math.ceil(page / (double) size)) * size;

        int start = tempEnd - (size - 1);
        int end = Math.min(totalPage, tempEnd);
        boolean prev = start > 1;
        boolean next = totalPage > tempEnd;

        return PageResponseDto.<T>builder()
                .content(pageInfo.getContent())
                .page(page)
                .size(size)
                .totalCount(pageInfo.getTotalElements())
                .start(start)
                .end(end)
                .prev(prev)
                .next(next)
                .build();
    }
    
//    public PageResponseDto(Pageable pageable, Page<T> pageInfo) {
//        content = pageInfo.getContent();
//        totalPage = pageInfo.getTotalPages();       // 페이지 총 갯수
//        totalCount = pageInfo.getTotalElements();  // 로우 총 갯수
//        makePageList(pageable);
//    }


//    private void makePageList(Pageable pageable) {
//        this.page = pageable.getPageNumber() + 1;                       // 0부터 시작하므로 1을 추가
//        this.size = pageable.getPageSize();                             // 한 페이지에 포함될 row 의 최대개수, ex) size = 10, 화면에 보여줄 항목개수가 10개다.
//
//        int tempEnd = (int) (Math.ceil(page / (double) size)) * size;   // 현재 페이지를 기준으로 화면에 출력되어야 하는 마지막 페이지 번호를 우선 처리, ex) size = 5, page = 6이다! => tempEnd = 10 인것!
//
//        start = tempEnd - (size - 1);                                   // start = 6 = 10 - (5 - 1)
//        end = Math.min(totalPage, tempEnd);                             // 실제 데이터가 부족한 경우를 위해 마지막 페이지 번호는 전체 데이터의 개수를 이용해서 다시 계산
//        // end = (6, 10) = 6
//
//        prev = start > 1;                                               // 6 > 1 => true
//        next = totalPage > tempEnd;                                     // 6 > 10 => false
//
//        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());          // 현재 보여줄 목록 ex) 마지막 페이지에 들어갔다. 6
//    }
}
