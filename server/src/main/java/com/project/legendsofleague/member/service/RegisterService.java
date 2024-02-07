package com.project.legendsofleague.member.service;

import com.project.legendsofleague.member.domain.Member;
import com.project.legendsofleague.member.dto.RegisterDto;
import com.project.legendsofleague.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(RegisterDto dto){

        // db에 동일한 회원이 존재하는지 확인
        // username이 DB에 존재하는지 확인
        Boolean checkUser = memberRepository.existsByUsername(dto.getUsername());

        // username이 존재하지 않을 경우 회원가입 처리
        if(!checkUser) {
            // 회원정보를 생성할 때 비밀번호를 인코딩 처리해야 하므로 create의 매개변수로 인코더를 넘겨준다.
            Member member = Member.create(dto, bCryptPasswordEncoder);
            memberRepository.save(member);
        }

    }
}
