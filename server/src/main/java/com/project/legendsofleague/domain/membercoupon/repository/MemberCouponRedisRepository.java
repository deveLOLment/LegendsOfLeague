package com.project.legendsofleague.domain.membercoupon.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberCouponRedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    private final String REDIS_KEY_PREFIX = "coupon:";

    //특정 쿠폰의 발급개수를 늘리는 메서드
    public Long increaseMemberCouponCount(Long couponId) {
        String key = getMemberCouponRedisKey(couponId);

        return redisTemplate
            .opsForValue()
            .increment(key);
    }

    //특정 쿠폰의 발급개수를 줄이는 메서드
    public Long decreaseMemberCouponCount(Long couponId) {
        String key = getMemberCouponRedisKey(couponId);

        return redisTemplate
            .opsForValue()
            .decrement(key);
    }

    //DB에 반영하기 위해 쿠폰당 발급개수를 리턴하는 메서드
    public Map<Long, Long> getMemberCouponCounts() {
        Map<Long, Long> memberCouponCountMap = new HashMap<>();
        Set<String> keys = redisTemplate.keys(REDIS_KEY_PREFIX + "*");
        Optional.ofNullable(keys).ifPresent(keyList -> {
            keyList.forEach(key -> {
                Long couponId = parseMemberCouponRedisKey(key);
                Long count = Long.valueOf(
                    Optional.ofNullable(redisTemplate.opsForValue().get(key))
                        .orElse("0"));
                memberCouponCountMap.put(couponId, count);
            });
        });

        return memberCouponCountMap;
    }

    //쿠폰당 발급한 개수를 저장하는 레코드를 삭제하는 메서드
    public void deleteMemberCouponCount() {
        Set<String> keys = redisTemplate.keys(REDIS_KEY_PREFIX + "*");
        Optional.ofNullable(keys).ifPresent(keyList -> {
            keyList.forEach(redisTemplate::delete);
        });
    }

    //CouponId에 맞는 Key값을 만드는 메서드
    private String getMemberCouponRedisKey(Long couponId) {
        return REDIS_KEY_PREFIX + couponId;
    }

    //Key값에서 CouponId를 얻어내는 메서드
    private Long parseMemberCouponRedisKey(String key) {
        return Long.valueOf(key.split(":")[1]);
    }


}
