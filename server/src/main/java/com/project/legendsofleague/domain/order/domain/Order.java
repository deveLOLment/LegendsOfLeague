package com.project.legendsofleague.domain.order.domain;

import com.project.legendsofleague.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate;

    private String orderCode; //UUID로 부여

    private Integer totalOrderPrice; //전체 주문 가격

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItemList = new ArrayList<>();

    public Order(Long id) {
        this.id = id;
    }

    public static Order toEntity(Member member) {
        Order order = new Order();
        order.member = member;
        order.orderDate = LocalDateTime.now();
        order.orderCode = UUID.randomUUID().toString();
        order.orderStatus = OrderStatus.PENDING; //초기에는 준비 상태
        return order;
    }

    public Integer getTotalPrice() {
        Integer totalPrice = 0;
        for (OrderItem orderItem : orderItemList) {
            totalPrice += (orderItem.getOrderPrice() * orderItem.getCount());
        }
        return totalPrice;
    }


}
