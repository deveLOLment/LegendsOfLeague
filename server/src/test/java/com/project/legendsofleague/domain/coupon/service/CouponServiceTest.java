package com.project.legendsofleague.domain.coupon.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.project.legendsofleague.domain.coupon.domain.Coupon;
import com.project.legendsofleague.domain.coupon.domain.CouponType;
import com.project.legendsofleague.domain.coupon.dto.CouponCreateDto;
import com.project.legendsofleague.domain.coupon.repository.CouponRepository;
import com.project.legendsofleague.domain.item.domain.Item;
import com.project.legendsofleague.domain.item.domain.ItemCategory;
import com.project.legendsofleague.domain.item.dto.ItemRqDto;
import com.project.legendsofleague.domain.item.repository.ItemRepository;
import com.project.legendsofleague.domain.membercoupon.domain.MemberCoupon;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CouponServiceTest {

    private final Long itemId = 1L;
    private final Long memberCouponId = 2L;

    @InjectMocks
    CouponService couponService;
    @Mock
    CouponRepository couponRepository;
    @Mock
    ItemRepository itemRepository;
    private Item item;

    private Coupon categoryAmountCoupon, categoryPercentCoupon, itemAmountCoupon, itemPercentCoupon;
    private MemberCoupon categoryAmountMemberCoupon, categoryPercentMemberCoupon, itemAmountMemberCoupon, itemPercentMemberCoupon;

    /*
     public Boolean checkValidity(Map<Long, MemberCoupon> memberCouponMap,
        List<ItemCouponAppliedDto> itemCouponList) TEST
     */

    @BeforeEach
    public void setup() {

        LocalDate now = LocalDate.now();

        /**
         * item 기본정보 세팅
         */
        item = Item.toEntity(
            new ItemRqDto(itemId, "item1", 10000, 10, "description1", ItemCategory.CLOTHING,
                "thumbnail1", new ArrayList<>(), false), "thumbnail1");

        /**
         * 쿠폰 기본정보 세팅
         */
        categoryAmountCoupon = Coupon.toEntity(
            new CouponCreateDto("categoryAmountCoupon", "desc", 5, 3000, null,
                now.minusDays(10), now.plusDays(10), 2000, ItemCategory.CLOTHING.name(),
                CouponType.CATEGORY_AMOUNT_DISCOUNT.name(), itemId));

        categoryPercentCoupon = Coupon.toEntity(
            new CouponCreateDto("categoryPercentCoupon", "desc", 5, 3000, 5000,
                now.minusDays(10), now.plusDays(10), 10, ItemCategory.CLOTHING.name(),
                CouponType.CATEGORY_PERCENT_DISCOUNT.name(), itemId));

        itemAmountCoupon = Coupon.toEntity(
            new CouponCreateDto("itemAmountCoupon", "desc", 5, 3000, null,
                now.minusDays(10), now.plusDays(10), 2000, ItemCategory.CLOTHING.name(),
                CouponType.ITEM_AMOUNT_DISCOUNT.name(), itemId));

        itemPercentCoupon = Coupon.toEntity(
            new CouponCreateDto("itemPercentCoupon", "desc", 5, 3000, 5000,
                now.minusDays(10), now.plusDays(10), 10, ItemCategory.CLOTHING.name(),
                CouponType.ITEM_PERCENT_DISCOUNT.name(), itemId));

    }

    /*
    checkPriceWithoutCoupon TEST
     */
    @DisplayName("checkPriceWithoutCoupon - 가격 검증이 성공한 경우")
    @Test
    public void checkPriceWithoutCouponTest_success() {
        int itemPrice = 10000;
        int quantity = 2;
        Item mockItem = mock(Item.class);
        when(mockItem.getPrice()).thenReturn(itemPrice);

        int calculatedPrice = 20000;
        boolean result = couponService.checkPriceWithoutCoupon(mockItem, calculatedPrice, quantity);

        assertThat(result).isTrue();
    }

    @DisplayName("checkPriceWithoutCoupon - 가격 검증이 실패한 경우")
    @Test
    public void checkPriceWithoutCouponTest_fail() {
        int itemPrice = 10000;
        int quantity = 2;
        Item mockItem = mock(Item.class);
        when(mockItem.getPrice()).thenReturn(itemPrice);

        int calculatedPrice = 10000;
        boolean result = couponService.checkPriceWithoutCoupon(mockItem, calculatedPrice, quantity);

        assertThat(result).isFalse();
    }

    /*
    checkCategoryAppliedCoupon TEST
     */
    @DisplayName("checkCategoryAppliedCoupon - 아이템&정량 쿠폰이 입력되어 무조건 true를 리턴하는 경우")
    @Test
    public void checkCategoryAppliedCouponTest_itemAmountAppliedCoupon() {
        Coupon mockCoupon = mock(Coupon.class);
        when(mockCoupon.getCouponType()).thenReturn(CouponType.ITEM_AMOUNT_DISCOUNT);

        boolean result = couponService.checkCategoryAppliedCoupon(item, mockCoupon);

        assertThat(result).isTrue();
    }

    @DisplayName("checkCategoryAppliedCoupon - 아이템&퍼센트 쿠폰이 입력되어 무조건 true를 리턴하는 경우")
    @Test
    public void checkCategoryAppliedCouponTest_itemPercentAppliedCoupon() {
        Coupon mockCoupon = mock(Coupon.class);
        when(mockCoupon.getCouponType()).thenReturn(CouponType.ITEM_PERCENT_DISCOUNT);

        boolean result = couponService.checkCategoryAppliedCoupon(item, mockCoupon);

        assertThat(result).isTrue();
    }

    @DisplayName("checkCategoryAppliedCoupon - 카테고리&정량 - 구매하려는 아이템의 카테고리와 쿠폰의 적용 카테고리가 다른 경우")
    @Test
    public void checkCategoryAppliedCouponTest_categoryAmount_differentCategoryCoupon() {
        Coupon mockCoupon = mock(Coupon.class);
        when(mockCoupon.getCouponType()).thenReturn(CouponType.CATEGORY_AMOUNT_DISCOUNT);
        when(mockCoupon.getAppliedCategory()).thenReturn(ItemCategory.CLOTHING);

        Item mockItem = mock(Item.class);
        when(mockItem.getCategory()).thenReturn(ItemCategory.ACCESSORIES);

        boolean result = couponService.checkCategoryAppliedCoupon(mockItem, mockCoupon);

        assertThat(result).isFalse();
    }

    @DisplayName("checkCategoryAppliedCoupon - 카테고리&정량 -구매하려는 아이템의 카테고리와 쿠폰의 적용 카테고리가 같은 경우")
    @Test
    public void checkCategoryAppliedCouponTest_categoryAmount_sameCategoryCoupon() {
        Coupon mockCoupon = mock(Coupon.class);
        when(mockCoupon.getCouponType()).thenReturn(CouponType.CATEGORY_AMOUNT_DISCOUNT);
        when(mockCoupon.getAppliedCategory()).thenReturn(ItemCategory.ACCESSORIES);

        Item mockItem = mock(Item.class);
        when(mockItem.getCategory()).thenReturn(ItemCategory.ACCESSORIES);

        boolean result = couponService.checkCategoryAppliedCoupon(mockItem, mockCoupon);

        assertThat(result).isTrue();
    }

    @DisplayName("checkCategoryAppliedCoupon - 카테고리&퍼센트 - 구매하려는 아이템의 카테고리와 쿠폰의 적용 카테고리가 다른 경우")
    @Test
    public void checkCategoryAppliedCouponTest_categoryPercent_differentCategoryCoupon() {
        Coupon mockCoupon = mock(Coupon.class);
        when(mockCoupon.getCouponType()).thenReturn(CouponType.CATEGORY_PERCENT_DISCOUNT);
        when(mockCoupon.getAppliedCategory()).thenReturn(ItemCategory.CLOTHING);

        Item mockItem = mock(Item.class);
        when(mockItem.getCategory()).thenReturn(ItemCategory.ACCESSORIES);

        boolean result = couponService.checkCategoryAppliedCoupon(mockItem, mockCoupon);

        assertThat(result).isFalse();
    }

    @DisplayName("checkCategoryAppliedCoupon - 카테고리&퍼센트 -구매하려는 아이템의 카테고리와 쿠폰의 적용 카테고리가 같은 경우")
    @Test
    public void checkCategoryAppliedCouponTest_categoryPercent_sameCategoryCoupon() {
        Coupon mockCoupon = mock(Coupon.class);
        when(mockCoupon.getCouponType()).thenReturn(CouponType.CATEGORY_PERCENT_DISCOUNT);
        when(mockCoupon.getAppliedCategory()).thenReturn(ItemCategory.ACCESSORIES);

        Item mockItem = mock(Item.class);
        when(mockItem.getCategory()).thenReturn(ItemCategory.ACCESSORIES);

        boolean result = couponService.checkCategoryAppliedCoupon(mockItem, mockCoupon);

        assertThat(result).isTrue();
    }

    /*
    checkItemAppliedCoupon TEST
     */
    @DisplayName("checkItemAppliedCoupon - 카테고리&정량 -  무조건 true를 리턴하는 경우")
    @Test
    public void checkItemAppliedCouponTest_categoryAmount_returnTrue() {
        Coupon mockCoupon = mock(Coupon.class);
        when(mockCoupon.getCouponType()).thenReturn(CouponType.CATEGORY_AMOUNT_DISCOUNT);

        Item mockItem = mock(Item.class);

        couponService.checkItemAppliedCoupon(mockItem, mockCoupon);

        assertThat(true).isTrue();
    }

    @DisplayName("checkItemAppliedCoupon - 카테고리&퍼센트 -  무조건 true를 리턴하는 경우")
    @Test
    public void checkItemAppliedCouponTest_categoryPercent_returnTrue() {
        Coupon mockCoupon = mock(Coupon.class);
        when(mockCoupon.getCouponType()).thenReturn(CouponType.CATEGORY_PERCENT_DISCOUNT);

        Item mockItem = mock(Item.class);

        couponService.checkItemAppliedCoupon(mockItem, mockCoupon);

        assertThat(true).isTrue();
    }

    @DisplayName("checkItemAppliedCoupon - 아이템&퍼센트 - 쿠폰의 item과 구매하려는 item이 다른 경우")
    @Test
    public void checkItemAppliedCouponTest_itemPercent_diffItem() {
        Coupon mockCoupon = mock(Coupon.class);
        Item mockItem = mock(Item.class);
        Item newMockItem = mock(Item.class);
        when(mockCoupon.getCouponType()).thenReturn(CouponType.ITEM_PERCENT_DISCOUNT);
        when(mockCoupon.getItem()).thenReturn(newMockItem);

        Boolean result = couponService.checkItemAppliedCoupon(mockItem, mockCoupon);

        assertThat(result).isFalse();
    }

    @DisplayName("checkItemAppliedCoupon - 아이템&퍼센트 - 쿠폰의 item과 구매하려는 item이 같은 경우")
    @Test
    public void checkItemAppliedCouponTest_itemPercent_sameItem() {
        Coupon mockCoupon = mock(Coupon.class);
        Item mockItem = mock(Item.class);
        when(mockCoupon.getCouponType()).thenReturn(CouponType.ITEM_PERCENT_DISCOUNT);
        when(mockCoupon.getItem()).thenReturn(mockItem);

        Boolean result = couponService.checkItemAppliedCoupon(mockItem, mockCoupon);

        assertThat(result).isTrue();
    }

    /*
    checkMinPrice TEST
     */
    @DisplayName("checkMinPrice - 카테고리&정량 - 무조건 true 리턴")
    @Test
    public void checkMinPriceTest_categoryAmount_returnTrue() {
        Coupon mockCoupon = mock(Coupon.class);
        when(mockCoupon.getCouponType()).thenReturn(CouponType.CATEGORY_AMOUNT_DISCOUNT);

        Boolean result = couponService.checkMinPrice(mockCoupon, -1);

        assertThat(result).isTrue();
    }

    @DisplayName("checkMinPrice - 아이템&정량 - 무조건 true 리턴")
    @Test
    public void checkMinPriceTest_itemAmount_returnTrue() {
        Coupon mockCoupon = mock(Coupon.class);
        when(mockCoupon.getCouponType()).thenReturn(CouponType.ITEM_AMOUNT_DISCOUNT);

        Boolean result = couponService.checkMinPrice(mockCoupon, -1);

        assertThat(result).isTrue();
    }

    @DisplayName("checkMinPrice - 아이템&퍼센트 - 무조건 true 리턴")
    @Test
    public void checkMinPriceTest_itemPercent_returnTrue() {
        int minPrice = 10000;
        Coupon mockCoupon = mock(Coupon.class);
        when(mockCoupon.getCouponType()).thenReturn(CouponType.ITEM_PERCENT_DISCOUNT);
        when(mockCoupon.getMinPrice()).thenReturn(minPrice);

        Boolean result = couponService.checkMinPrice(mockCoupon, -1);

        assertThat(result).isTrue();
    }


}