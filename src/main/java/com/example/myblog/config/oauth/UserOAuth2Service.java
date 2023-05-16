package com.example.myblog.config.oauth;


import com.example.myblog.config.auth.PrincipalDetails;
import com.example.myblog.config.oauth.provider.FacebookUserInfo;
import com.example.myblog.config.oauth.provider.GoogleUserInfo;
import com.example.myblog.config.oauth.provider.OAuth2UserInfo;
import com.example.myblog.model.RoleType;
import com.example.myblog.model.User;
import com.example.myblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserOAuth2Service extends DefaultOAuth2UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{

        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = null;

        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
        else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")){
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        }

        String provider   = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderID();
        String username   = provider + "_" + providerId;
        String password   = bCryptPasswordEncoder.encode("임시비밀번호");
        String email      = oAuth2UserInfo.getEmail();
        String role       = "ROLE_USER";

        String uuid       = UUID.randomUUID().toString().substring(0,6);

        User newUser     = userRepository.findByUsername(username);

        if (newUser == null){
            newUser = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(RoleType.USER)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            userRepository.save(newUser);
        }



        return new PrincipalDetails(newUser, oAuth2User.getAttributes());
    }
}
