package com.project.legendsofleague.domain.member.repository;

import com.project.legendsofleague.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 회원가입 시 사용할 username이 이미 DB에 존재하는지 체크
    Boolean existsByUsername(String username);

    // username으로 회원 조회
    Member findByUsername(String username);

//    Member findByUsernameAndPassword(String username, String password);
}
