package com.project.legendsofleague.domain.purchase.domain;

import com.project.legendsofleague.common.BaseEntity;
import com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon;
import com.project.legendsofleague.domain.order.domain.Order;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase extends BaseEntity {

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.REMOVE)
    private final List<MemberCoupon> memberCouponList = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Long id;
    private String name;
    private Integer quantity;
    /*
     KAKAO : tid
     TOSS : paymentKey
     */
    private String code;

    private Integer totalPrice;

    private LocalDateTime purchaseDate;

    @Enumerated(value = EnumType.STRING)
    private PurchaseStatus purchaseStatus;

    @Enumerated(value = EnumType.STRING)
    private PurchaseType purchaseType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public Purchase(Long id) {
        this.id = id;
    }

    public static Purchase toEntity(Integer quantity, String name, Integer totalPrice,
        PurchaseType purchaseType,
        Order order) {
        Purchase purchase = new Purchase();
        purchase.quantity = quantity;
        purchase.name = name;
        purchase.totalPrice = totalPrice;
        purchase.purchaseType = purchaseType;
        purchase.purchaseStatus = PurchaseStatus.PENDING;
        purchase.order = order;
        purchase.purchaseDate = LocalDateTime.now();
        return purchase;
    }


    public void updatePurchaseCode(String code) {
        this.code = code;
        if (this.purchaseStatus == PurchaseStatus.PENDING) {
            this.purchaseStatus = PurchaseStatus.SUCCESS;
            this.purchaseDate = LocalDateTime.now();
        }
    }


    public void updatePurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }
}
