package com.example.WantedMarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // RoleHierarchy 설정
    @Bean
    public RoleHierarchy roleHierarchy() {

        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();

        hierarchy.setHierarchy("ROLE_ADMIN > ROLE_SUPERVISOR\n" +
                "ROLE_SUPERVISOR > ROLE_USER");

        return hierarchy;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login", "/loginProc", "/join", "/joinProc").permitAll()
                        .requestMatchers("/","/supervisor").hasAnyRole("USER")
//                        .requestMatchers("/supervisor").hasAnyRole("SUPERVISOR")
                        .requestMatchers("/admin").hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                );

        // 로그인 설정
        // loginPage : 로그인 페이지 경로
        http
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                );

        // 개발 환경에서는 csrf를 disable 설정
        // 배포 환경에서는 csrf 공격 방지를 위해 csrf disable 설정을 제거(즉 enable설정)하고 추가적인 설정하기
        // https://substantial-park-a17.notion.site/11-csrf-9efc36841d884fb7b2613d37da440e78
        http
                .csrf((auth) -> auth.disable());


        // 세션 관리
        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(2) // 최대 세션 수 (다중 로그인 수)
                        .maxSessionsPreventsLogin(true)); // 최대 세션 수 초과시
                         // true : 초과시 새로운 로그인 차단
                         // false : 초과시 기존 세션 하나 삭제


        // 세션 고정 보호
        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId());
        //sessionManagement().sessionFixation().none() : 로그인 시 세션 정보 변경 안함(기본값)- 취약점 존재
        //sessionManagement().sessionFixation().newSession() : 로그인 시 세션 새로 생성
        //sessionManagement().sessionFixation().changeSessionId() : 로그인 시 동일한 세션에 대한 id 변경

        return http.build();
    }
}