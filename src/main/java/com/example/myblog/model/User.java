package com.example.myblog.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Data
@Getter
@NoArgsConstructor
@Entity//User 클래스가 MySql에 자동으로 테이블 생성
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //INDENTITY 전략은 기본 키 생성을 프로젝트에 연결된 데이터베이스 위임하는 전략
    private long id;

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;

    private String provider;
    private String providerId;

    public void updateEmail(String email){
        this.email = email;
    }

    public void updatePassword(String password){
        this.password = password;
    }

    public void updateRole(RoleType role) {
        this.role = role;
    }

    public UserResponseDto toDto(){

    }
}