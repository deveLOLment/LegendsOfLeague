package com.project.legendsofleague.member.domain;

import com.project.legendsofleague.member.dto.RegisterDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    // 로그인용 아이디
    // 스프링 시큐리티에서는 username, password를 통해 로그인을 하기 때문에 username이 중복되지 않게 unique 키로 설정한다.
   @Column(unique = true)
    String username;

    // 닉네임
    String nickname;

    // 회원 이메일
    String email;

    // 회원 타입(일반회원, 관리자)
    @Enumerated(EnumType.STRING)
    ROLE role;

    /*
    *연관관계 매핑할 것들, ORDER, CART
    *
    **/

    // 팩토리 메서드(테스트용)
    public static Member create(RegisterDto dto, BCryptPasswordEncoder encoder){
        Member member = new Member();
        member.username = dto.getUsername();
        member.password = encoder.encode(dto.getPassword());
        member.role = dto.getRole();
        return member;
    }
}
