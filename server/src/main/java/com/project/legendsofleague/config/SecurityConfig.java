package com.project.legendsofleague.config;

import com.project.legendsofleague.domain.member.jwt.CustomSuccessHandler;
import com.project.legendsofleague.domain.member.jwt.InternalFilterExceptionHandler;
import com.project.legendsofleague.domain.member.jwt.JWTFilter;
import com.project.legendsofleague.domain.member.jwt.JWTUtil;
import com.project.legendsofleague.domain.member.jwt.LoginFilter;
import com.project.legendsofleague.domain.member.repository.MemberRepository;
import com.project.legendsofleague.domain.member.service.CustomOAuth2MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MemberRepository memberRepository;

    private final InternalFilterExceptionHandler internalFilterExceptionHandler;

    private final CustomOAuth2MemberService customOAuth2MemberService;

    private final CustomSuccessHandler customSuccessHandler;

    private final AuthenticationConfiguration configuration;

    private final JWTUtil jwtUtil;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // jwt는 세션을 유지하지 않기 때문에 csrf 공격에 대한 설정은 하지 않는다.
        httpSecurity
                .csrf((auth) -> auth.disable());

        // jwt를 통해 로그인을 하기 때문에 form, http basic에 관한 설정은 off

        httpSecurity
                .formLogin((auth) -> auth.disable());

        httpSecurity
                .httpBasic((auth) -> auth.disable());

        // 특정 URL에 대한 접근권한 설정

        // .anyRequest().permitAll());

        // OAuth2
        httpSecurity
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOAuth2MemberService))
                        .successHandler(customSuccessHandler)
                );

        httpSecurity
                .addFilterBefore(new JWTFilter(jwtUtil, memberRepository), LoginFilter.class);

        httpSecurity
                .addFilterBefore(internalFilterExceptionHandler, JWTFilter.class);

        // 로그인 필터 설정
        httpSecurity
                .addFilterAt(new LoginFilter(authenticationManager(configuration), jwtUtil), UsernamePasswordAuthenticationFilter.class);
//                .addFilterBefore(new ExceptionHandlerFilter(), JWTFilter.class);

        // 로그아웃 설정
        httpSecurity
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .deleteCookies("Authorization")
                .permitAll();

        // 경로별 인가 작업
        httpSecurity
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login",
                                "/register", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/").hasAnyRole("ADMIN", "USER")
                        .anyRequest().permitAll()
                );

        // 세션 설정 off
        httpSecurity
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

//        httpSecurity.cor <- 여기서부터 다시

        return httpSecurity.build();
    }

//    @Bean
//    public CustomOAuth2MemberService customOAuth2UserService(){
//        return new CustomOAuth2MemberService();
//    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/", "/login",
//                                "/registerProc", "/swagger-ui/**","/v3/api-docs/**").permitAll()
//                        .requestMatchers("/admin").hasRole("ADMIN")
//                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
//                        .anyRequest().permitAll()
//                );
//
//        http
//                .formLogin((auth) -> auth.loginPage("/login")
//                        .loginProcessingUrl("/loginProc")
//                        .permitAll()
//                );
//
//        http
//                .csrf((auth) -> auth.disable());
//
//        http
//                .sessionManagement((auth) -> auth
//                        .maximumSessions(1)
//                        .maxSessionsPreventsLogin(true));
//
//        http
//                .sessionManagement((auth) -> auth
//                        .sessionFixation().changeSessionId());
//
//        http
//                .logout((auth) -> auth.logoutUrl("/logout")
//                        .logoutSuccessUrl("/"));
//
//        return http.build();
//
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}