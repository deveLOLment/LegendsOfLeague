//package com.project.legendsofleague.domain.member.controller;
//
//import com.project.legendsofleague.domain.member.domain.Member;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Collection;
//import java.util.Iterator;
//
//@RestController
//public class LoginController {
//
////    // 테스트 코드
////    @GetMapping("/")
////    public String home(){
////
////        String id =
////                SecurityContextHolder
////                .getContext()
////                .getAuthentication()
////                .getName();
////
////        Authentication authentication =
////                SecurityContextHolder
////                        .getContext()
////                        .getAuthentication();
////
////        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
////
////        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
////        GrantedAuthority auth = iter.next();
////        String role = auth.getAuthority();
////
////
////       return role;
////    }
//
//    @GetMapping("/login")
//    public Member login(){
//        return null;
//    }
//
//    // 로그아웃 테스트
//    @GetMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response){
//
//        Authentication authentication =
//                SecurityContextHolder.
//                        getContext()
//                        .getAuthentication();
//
//        if(authentication != null){
//            new SecurityContextLogoutHandler().logout(request, response, authentication);
//        }
//
//        return "test";
//    }
//
//}
