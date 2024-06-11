package com.example.WantedMarket.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;

    // protected 생성자: 외부에서 객체 생성 방지
    protected User() {}

    // 정적 팩토리 메서드
    public static User createUser(String username, String password, String role) {
        User user = new User();
        user.username = username;
        user.password = password;
        user.role = role;
        return user;
    }
}
