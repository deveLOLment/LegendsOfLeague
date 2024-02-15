package com.project.legendsofleague.domain.member.dto;

public interface OAuth2Response {

    // 서비스 제공처 (네이버, 구글)
    String getProvider();

    // 제공처가 발급해주는 아이디
    String getProviderId();

    // 이메일
    String getEmail();

    // 사용자 실명
    String getName();
}
