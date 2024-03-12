package com.project.legendsofleague.domain.order.domain;

import static com.project.legendsofleague.domain.order.domain.OrderStatus.CANCEL;
import static com.project.legendsofleague.domain.order.domain.OrderStatus.PENDING;
import static com.project.legendsofleague.domain.order.domain.OrderStatus.REFUND;
import static com.project.legendsofleague.domain.order.domain.OrderStatus.SUCCESS;

import com.project.legendsofleague.common.BaseEntity;
import com.project.legendsofleague.domain.member.domain.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate;

    private String orderCode; //UUID로 부여

    private Integer totalOrderPrice = 0; //전체 주문 가격 (할인이 모두 끝난 가격)

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

    public Order(Long id) {
        this.id = id;
    }

    /**
     * orderItem이 1개가 아니어도 이 메소드를 통해 order 객체를 최초에 생성할 수 있다.
     *
     * @param member
     * @param orderItems
     * @return
     */
    public static Order toEntity(Member member, List<OrderItem> orderItems) {
        Order order = new Order();
        order.member = member;
        order.orderDate = LocalDateTime.now(); //아직 결제가 되지 않았더라도 현재로 초기화
        order.orderCode = UUID.randomUUID().toString();
        order.orderStatus = PENDING; //초기에는 준비 상태
        for (OrderItem orderItem : orderItems) {  //cascade = ALL이기 때문에 리스트에 추가해주고 orderItem의 가격도 더해준다. + orderItem의 order를 세팅해준다.
            order.addOrderItem(orderItem);
        }
        return order;
    }

    public static Order toEntity(Member member, OrderItem orderItem) {
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        return toEntity(member, orderItems);
    }


    private void addOrderItem(OrderItem orderItem) {
        this.orderItemList.add(orderItem);
        orderItem.addOrder(this);
    }

    public void changeStatusToSuccess(LocalDateTime time, Integer totalOrderPrice) {
        this.orderStatus = SUCCESS;
        this.orderDate = time;
        this.totalOrderPrice = totalOrderPrice;
    }

    public void changeStatusToCancel() {
        this.orderStatus = CANCEL;
    }


    public void refundOrder() {
        this.orderStatus = REFUND;
        for (OrderItem orderItem : orderItemList) {
            orderItem.getItem().addStock(orderItem.getCount());
        }
    }



}
