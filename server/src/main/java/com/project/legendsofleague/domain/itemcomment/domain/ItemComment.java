package com.project.legendsofleague.domain.itemcomment.domain;

import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.member.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private ItemComment parentComment;

    @OneToMany(mappedBy = "parentComment")
    private List<ItemComment> childCommentList = new ArrayList<>();

    public ItemComment(Long id) {
        this.id = id;
    }

    public static ItemComment from(String content, Item item, Member member, Long parentCommentId) {
        ItemComment itemComment = new ItemComment();
        itemComment.content = content;
        itemComment.item = item;
        itemComment.member = member;
        itemComment.createdDate = LocalDateTime.now();
        if(parentCommentId != -1L){
            itemComment.parentComment = new ItemComment(parentCommentId);
        }
        return itemComment;
    }


}
