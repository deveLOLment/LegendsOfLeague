package com.project.legendsofleague.domain.coupon.domain;

import com.project.legendsofleague.common.BaseEntity;
import com.project.legendsofleague.domain.coupon.dto.CouponCreateDto;
import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.domain.ItemCategory;
import com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseEntity {

    @OneToMany(mappedBy = "coupon", fetch = FetchType.LAZY)
    private final List<MemberCoupon> memberCouponList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    private String name;
    private String description;
    private Integer stock;
    private String code;
    private LocalDate validityStartDate;
    private LocalDate validityEndDate;
    private Integer discountPrice;
    private Integer minPrice;
    private Integer maxPrice;

    @Enumerated(value = EnumType.STRING)
    private ItemCategory appliedCategory;

    @Enumerated(value = EnumType.STRING)
    private CouponType couponType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;


    public Coupon(Long id) {
        this.id = id;
    }

    public static Coupon toEntity(CouponCreateDto dto) {
        Coupon coupon = new Coupon();
        coupon.name = dto.getName();
        coupon.description = dto.getDescription();
        coupon.stock = dto.getStock();
        coupon.validityStartDate = dto.getValidityStartDate();
        coupon.validityEndDate = dto.getValidityEndDate();
        CouponType inputCouponType = CouponType.valueOf(dto.getCouponTypeName());
        coupon.couponType = inputCouponType;
        if (inputCouponType == CouponType.CATEGORY_PERCENT_DISCOUNT
            || inputCouponType == CouponType.CATEGORY_AMOUNT_DISCOUNT) {
            coupon.appliedCategory = ItemCategory.valueOf(dto.getAppliedCategoryName());
        } else {
            coupon.item = new Item(dto.getItemId());
        }
        coupon.discountPrice = dto.getDiscountPrice();
        coupon.minPrice = dto.getMinPrice();
        coupon.maxPrice = dto.getMaxPrice();
        coupon.code = UUID.randomUUID().toString().substring(0, 16);
        return coupon;
    }

    public void decreaseCouponStock(Long count) {
        this.stock -= count.intValue();
    }
}
