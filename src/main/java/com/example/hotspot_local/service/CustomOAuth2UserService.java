package com.example.hotspot_local.service;

import com.example.hotspot_local.controller.response.OAuth.GoogleReponse;
import com.example.hotspot_local.controller.response.OAuth.OAuth2Response;
import com.example.hotspot_local.domain.UserEntity;
import com.example.hotspot_local.dto.CustomOAuth2User;
import com.example.hotspot_local.repository.OAuthRepository;
import com.example.hotspot_local.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	//DefaultOAuth2UserService OAuth2UserService의 구현체

	private final OAuthRepository userRepository;

	public CustomOAuth2UserService(OAuthRepository userRepository) {

		this.userRepository = userRepository;
	}



	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println(oAuth2User.getAttributes());

		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		OAuth2Response oAuth2Response = null;
//		if (registrationId.equals("naver")) {

//			oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
//		}
//		else
		if (registrationId.equals("google")) {
			oAuth2Response = new GoogleReponse(oAuth2User.getAttributes());
		}
		else {

			return null;
		}

		String role = "ROLE_USER";

		String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
//		String tmp = oAuth2Response.
		UserEntity existData = userRepository.findByUsername(username);


		if (existData == null) {

			UserEntity userEntity = new UserEntity();
			userEntity.setUsername(oAuth2Response.getName());
			userEntity.setEmail(oAuth2Response.getEmail());
			userEntity.setRole(role);


			userRepository.save(userEntity);
		}
		else {

			existData.setUsername(username);
			existData.setEmail(oAuth2Response.getEmail());

			role = existData.getRole();

			userRepository.save(existData);
		}

		return new CustomOAuth2User(oAuth2Response, role);

	}
}