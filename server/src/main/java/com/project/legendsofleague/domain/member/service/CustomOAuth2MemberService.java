package com.project.legendsofleague.domain.member.service;

import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.member.dto.CustomOAuth2Member;
import com.project.legendsofleague.domain.member.dto.GoogleResponse;
import com.project.legendsofleague.domain.member.dto.MemberDto;
import com.project.legendsofleague.domain.member.dto.OAuth2Response;
import com.project.legendsofleague.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2MemberService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;


    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId =
                userRequest
                        .getClientRegistration()
                        .getRegistrationId();

        OAuth2Response oAuth2Response = null;

        // 구글과 네이버에서 오는 데이터가 양식이 서로 다르기 때문에 받는 방식을 구분한다.

        if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }

        // 받아온 Response 객체에서 provider, providerId 추출
        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        Member existMember = memberRepository.findByUsername(username);

        // 회원이 존재하지 않을 경우 회원가입 후 로그인처리, 아닐 경우 로그인 처리
        if (existMember == null) {
            // test
//            String name = oAuth2Response.getName();

            Member member = Member.create(username, oAuth2Response.getEmail(), oAuth2Response.getName());
//            Member member = Member.from(oAuth2Response);
            memberRepository.save(member);

            MemberDto memberDto = MemberDto.of(username, oAuth2Response.getName());

            return new CustomOAuth2Member(memberDto);
        } else {

//            Member member = Member.from(oAuth2Response);
            existMember.updateUser(oAuth2Response);
            memberRepository.save(existMember);

            MemberDto memberDto = MemberDto.of(username, oAuth2Response.getName());

            return new CustomOAuth2Member(memberDto);

        }

//        return oAuth2User;

    }
}
