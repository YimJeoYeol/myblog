package com.example.myblog.repository;

import com.example.myblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.security.Principal;

public interface UserRepository extends JpaRepository<User, Long> {
    //PrincipalDetailService에서 username 으로 찾기 위함
    //select * from user where username =?
    //Optional<User> findByUsername(String username);
    User findByUsername(String username);

    /*
    * 일반 로그인시
    * */
    //JPA Naming Query 전략
    //select * from user where username = ? and password =?;
    //User findUsernamAndPassword(String username, String password);

}
