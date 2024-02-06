package com.project.legendsofleague.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    // 회원 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    Long id;

    // 회원 비밀번호
    String password;

    // 닉네임
    String nickName;

    // 회원 이메일
    String email;

    // 회원 타입(일반회원, 관리자)
    @Enumerated(EnumType.STRING)
    ROLE role;

    /*
    *연관관계 매핑할 것들, ORDER, CART
    *
    **/

}
