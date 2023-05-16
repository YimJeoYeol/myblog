package com.example.myblog.config.auth;

import com.example.myblog.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고, 완료가 되면 UserDetails 타입의 오브젝트를 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
public class PrincipalDetails implements UserDetails, OAuth2User {      //UserDetails = Spring Security 에서 사용자의 정보를 담는 인터페이스

    private User user;
    private Map<String, Object> attributes;

    public PrincipalDetails(User user){
        this.user = user;
    }

    public PrincipalDetails(User user, Map<String, Object> attributes){
        this.user       = user;
        this.attributes = attributes;
    }


    @Override
    public Map<String, Object> getAttributes() {

        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> {
            return "ROLE_" + user.getRole();
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //계정 말료 여부 (true:만료X)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    ///계정 말료 여부 (true:잠김X)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호 만료 여부(true:만료X)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //게정이 사용 가능한지 (true:활성화(사용가능))
    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String getName() {
        return null;
    }
}
