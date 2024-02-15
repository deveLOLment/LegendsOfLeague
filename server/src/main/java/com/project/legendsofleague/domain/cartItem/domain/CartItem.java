package com.project.legendsofleague.domain.cartItem.domain;


import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer count;


    public static CartItem toEntity(Member member, Item item, Integer count) {
        CartItem cartItem = new CartItem();
        cartItem.item = item;
        cartItem.member = member;
        cartItem.count = count;
        return cartItem;
    }

}
