package com.project.legendsofleague.domain.membercoupon.domain;


import com.project.legendsofleague.domain.coupon.domain.Coupon;
import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.membercoupon.dto.MemberCouponCreateDto;
import com.project.legendsofleague.domain.purchase.domain.Purchase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    name = "member_coupon",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "prevent duplication of membercoupon",
            columnNames = {"member_id", "coupon_id"}
        )
    }
)
public class MemberCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_coupon_id")
    private Long id;

    private boolean isUsed;

    private LocalDate usedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    public MemberCoupon(Long id) {
        this.id = id;
    }

    public static MemberCoupon toEntity(Long memberId,
        MemberCouponCreateDto memberCouponCreateDto) {
        MemberCoupon memberCoupon = new MemberCoupon();
        memberCoupon.member = new Member(memberId);
        memberCoupon.coupon = new Coupon(memberCouponCreateDto.getCouponId());
        memberCoupon.isUsed = false;
        memberCoupon.usedDate = null;
        return memberCoupon;
    }

    public void updatePurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public void updatedUsedHistory(LocalDate usedDate) {
        this.isUsed = true;
        this.usedDate = usedDate;
    }

    public void revertUsedHistory() {
        this.isUsed = false;
    }
}
