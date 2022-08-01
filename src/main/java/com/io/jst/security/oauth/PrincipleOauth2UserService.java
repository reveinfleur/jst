package com.io.jst.security.oauth;

import com.io.jst.model.UserGrade;
import com.io.jst.model.entity.Users;
import com.io.jst.repository.UserRepository;
import com.io.jst.security.PrincipalDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipleOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;


        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            log.info("google login request");
            oAuth2UserInfo = new GoogleLoginInfo(oAuth2User.getAttributes());
        }else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
            log.info("facebook login request");
            oAuth2UserInfo = new FaceBookLoginInfo(oAuth2User.getAttributes());
        }
        String username = oAuth2UserInfo.getProvider()+"_"+oAuth2UserInfo.getProviderId();

        Users users = null;
        users = userRepository.findByEmail(oAuth2UserInfo.getEmail());

        if(users==null) {
            users = Users.builder()
                    .username(username)
                    .email(oAuth2UserInfo.getEmail())
                    .role(UserGrade.COMMON.getValue())
                    .provider(oAuth2UserInfo.getProvider())
                    .providerId(oAuth2UserInfo.getProviderId())
                    .build();
            userRepository.save(users);
        }else {
            log.info("login");
        }


        return new PrincipalDetail(users, oAuth2User.getAttributes());
    }
}
