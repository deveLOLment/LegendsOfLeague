package com.project.legendsofleague.member.repository;

import com.project.legendsofleague.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 회원가입 시 사용할 username이 이미 DB에 존재하는지 체크
    Boolean existsByUsername(String username);
}
