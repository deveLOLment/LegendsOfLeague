package com.project.legendsofleague.domain.coupon.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCoupon is a Querydsl query type for Coupon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCoupon extends EntityPathBase<Coupon> {

    private static final long serialVersionUID = 2024915054L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCoupon coupon = new QCoupon("coupon");

    public final EnumPath<com.project.legendsofleague.domain.item.domain.ItemCategory> appliedCategory = createEnum("appliedCategory", com.project.legendsofleague.domain.item.domain.ItemCategory.class);

    public final StringPath code = createString("code");

    public final EnumPath<CouponType> couponType = createEnum("couponType", CouponType.class);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> discountPrice = createNumber("discountPrice", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.project.legendsofleague.domain.item.domain.QItem item;

    public final NumberPath<Integer> maxPrice = createNumber("maxPrice", Integer.class);

    public final ListPath<com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon, com.project.legendsofleague.domain.membercoupon.domain.QMemberCoupon> memberCouponList = this.<com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon, com.project.legendsofleague.domain.membercoupon.domain.QMemberCoupon>createList("memberCouponList", com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon.class, com.project.legendsofleague.domain.membercoupon.domain.QMemberCoupon.class, PathInits.DIRECT2);

    public final NumberPath<Integer> minPrice = createNumber("minPrice", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> stock = createNumber("stock", Integer.class);

    public final DatePath<java.time.LocalDate> validityEndDate = createDate("validityEndDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> validityStartDate = createDate("validityStartDate", java.time.LocalDate.class);

    public QCoupon(String variable) {
        this(Coupon.class, forVariable(variable), INITS);
    }

    public QCoupon(Path<? extends Coupon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCoupon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCoupon(PathMetadata metadata, PathInits inits) {
        this(Coupon.class, metadata, inits);
    }

    public QCoupon(Class<? extends Coupon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new com.project.legendsofleague.domain.item.domain.QItem(forProperty("item")) : null;
    }

}

