package com.project.legendsofleague.domain.member.jwt;

import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.member.dto.CustomMemberDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
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


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 쿠키에서 토큰 정보를 가져온다.
        Cookie[] cookies = request.getCookies();
        String token = null;

        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("Authorization")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        // 토큰 검증
        if (token == null){
            System.out.println("토큰이 비어있습니다.");
            filterChain.doFilter(request, response);
            return;
        }

//        String authorization = request.getHeader("Authorization");
        // Authorization 헤더 검증
//        if (authorization == null || !authorization.startsWith("Bearer ")) {
//
//            System.out.println("token null");
//            filterChain.doFilter(request, response);
//
//            // 조건에 해당되면 메소드를 종료시킨다.
//            return;
//        }

//        String token = authorization.split(" ")[1];

        // 토큰의 'Bearer'를 제거
        String jwtToken = token.substring(7);


        // 토큰 소멸 시간  검증
        if(jwtUtil.isExpired(jwtToken)) {
            System.out.println("token expired");
            filterChain.doFilter(request, response);

            // 조건에 해당되면 메소드 종료
            return;
        }


        String username = jwtUtil.getUserName(jwtToken);
        String role = jwtUtil.getRole(jwtToken);

        Member member = Member.with(username,"1234", role);

        CustomMemberDetails customMemberDetails = new CustomMemberDetails(member);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customMemberDetails, null, customMemberDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
