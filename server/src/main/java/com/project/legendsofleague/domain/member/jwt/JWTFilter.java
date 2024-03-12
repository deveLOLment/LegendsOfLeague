package com.project.legendsofleague.domain.member.jwt;

import com.project.legendsofleague.domain.member.domain.Member;
import com.project.legendsofleague.domain.member.dto.CustomMemberDetails;
import com.project.legendsofleague.domain.member.repository.MemberRepository;
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

    private final MemberRepository memberRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 쿠키에서 토큰 정보를 가져온다.

        Cookie[] cookies = request.getCookies();
        String token = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Authorization")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        // 토큰 검증
        if (token == null) {
            System.out.println("토큰이 비어있습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        // request에서 Authorization 헤더를 찾는다.
//        String autorization = request.getHeader("Authorization");


        // 헤더 검증
//        if (autorization == null || !autorization.startsWith("Bearer ")) {
//            System.out.println("토큰이 비어있습니다.");
//            filterChain.doFilter(request, response);
//
//            // 메서드 종료
//            return;
//        }
//
//        // 토큰에서 Bearer 부분을 제거
//        String token = autorization.split(" ")[1];

        // 토큰 소멸 시간  검증
        if (jwtUtil.isExpired(token)) {
            System.out.println("token expired");
            Cookie cookie = new Cookie("Authorization", null); // 쿠키 이름을 지정합니다.
            cookie.setMaxAge(0); // 쿠키의 만료 시간을 0으로 설정하여 즉시 만료시킵니다.
            cookie.setPath("/"); // 쿠키의 경로를 설정합니다.
            response.addCookie(cookie); // 쿠키를 응답에 추가합니다.
//            filterChain.doFilter(request, response);
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//            throw new UnavailableException("접근권한없음");

            // 로그인 페이지로 리다이렉트
//            return;
            // 조건에 해당되면 메소드 종료
//            return;
        }

//        String requestURI = request.getRequestURI();
        else {
            String username = jwtUtil.getUserName(token);
            String role = jwtUtil.getRole(token);

//        Member member = Member.with(username,"1234", role);

            Member member = memberRepository.findByUsername(username);

            CustomMemberDetails customMemberDetails = new CustomMemberDetails(member);

            Authentication authToken = new UsernamePasswordAuthenticationToken(customMemberDetails, null, customMemberDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

    // 특정 URL 제외

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String excludePath = "/register";
        String path = request.getRequestURI();
        return path.startsWith(excludePath);
        //TODO 필요한 url 넣어서 사용하면 필터안거침
    }
}
