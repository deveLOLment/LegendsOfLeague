package com.project.legendsofleague.domain.member.jwt;

import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.member.dto.CustomMemberDetails;
import com.project.legendsofleague.domain.member.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    private final MemberRepository memberRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 쿠키에서 토큰 정보를 가져온다.
//        Cookie[] cookies = request.getCookies();
//        String token = null;
//
//        if(cookies != null) {
//            for (Cookie cookie : cookies) {
//                if(cookie.getName().equals("Authorization")) {
//                    token = cookie.getValue();
//                    break;
//                }
//            }
//        }

        // request에서 Authorization 헤더를 찾는다.
        String autorization = request.getHeader("Authorization");


        // 헤더 검증
        if (autorization == null || !autorization.startsWith("Bearer ")) {
            System.out.println("토큰이 비어있습니다.");
            filterChain.doFilter(request, response);

            // 메서드 종료
            return;
        }

        // 토큰에서 Bearer 부분을 제거
        String token = autorization.split(" ")[1];

        // 토큰 소멸 시간  검증
        if (jwtUtil.isExpired(token)) {
            System.out.println("token expired");
            filterChain.doFilter(request, response);

            // 조건에 해당되면 메소드 종료
            return;
        }


        String username = jwtUtil.getUserName(token);
        String role = jwtUtil.getRole(token);


//        Member member = Member.with(username,"1234", role);

        Member member = memberRepository.findByUsername(username);

        CustomMemberDetails customMemberDetails = new CustomMemberDetails(member);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customMemberDetails, null, customMemberDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
