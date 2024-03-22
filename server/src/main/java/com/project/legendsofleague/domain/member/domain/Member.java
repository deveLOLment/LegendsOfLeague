package com.project.legendsofleague.domain.member.domain;

import com.project.legendsofleague.common.BaseEntity;
import com.project.legendsofleague.domain.member.dto.OAuth2Response;
import com.project.legendsofleague.domain.member.dto.RegisterDto;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class Member extends BaseEntity {

    // 회원 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    @Schema(name = "Member ID", example = "1")
    Long id;

    // 회원 비밀번호
    @Schema(name = "Password", example = "password123")
    String password;

    // 로그인용 아이디
    // 스프링 시큐리티에서는 username, password를 통해 로그인을 하기 때문에 username이 중복되지 않게 unique 키로 설정한다.
    @Column(unique = true)
    @Schema(name = "Username", example = "exam")
    String username;

    // 닉네임
    @Column(unique = true)
    @Schema(name = "Nickname", example = "venus")
    String nickname;

    // 회원 이메일
    @Schema(name = "Email", example = "exam@naver.com")
    String email;

    // 회원 타입(일반회원, 관리자)
    @Enumerated(EnumType.STRING)
    @Schema(name = "Role", example = "ROLE_USER")
    ROLE role;

//    String role;

    /*
     *연관관계 매핑할 것들, ORDER, CART
     *
     **/


    public Member(Long id) {
        this.id = id;
    }

    // 팩토리 메서드(테스트용)
    public static Member from(RegisterDto dto, BCryptPasswordEncoder bCryptPasswordEncoder) {
        Member member = new Member();
        member.username = dto.getUsername();
        member.password = bCryptPasswordEncoder.encode(dto.getPassword());
        member.role = ROLE.ROLE_USER;
        return member;
    }

    public static Member create(String username, String email, String nickname) {
        Member member = new Member();
        member.username = username;
        member.email = email;
        member.nickname = nickname;
        member.role = ROLE.ROLE_USER;
        return member;
    }

    // 팩토리 메서드(테스트용2)
    public static Member with(String username, String password, String role) {
        Member member = new Member();
        member.username = username;
        member.password = password;
        // 문자열로 넘어온 role을 ENUM 타입으로 다시 변환
        member.role = ROLE.valueOf(role);
        return member;
    }

    public void updateUser(OAuth2Response oAuth2Response) {
        this.nickname = oAuth2Response.getName();
        this.email = oAuth2Response.getEmail();
    }
}
