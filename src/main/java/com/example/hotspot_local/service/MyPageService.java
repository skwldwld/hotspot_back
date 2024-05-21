package com.example.hotspot_local.service;

import com.example.hotspot_local.controller.response.AboutMyPage.MemberInfoResponse;
import com.example.hotspot_local.domain.UserCharacter;
import com.example.hotspot_local.repository.UserCharacterRepository;
import com.example.hotspot_local.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {

	private final UserRepository userRepository;
	private final UserCharacterRepository userCharacterRepository;

	public MemberInfoResponse findMemberInfo(String userEmail) {

		String userName = userRepository.findByEmail(userEmail).getUserName();
		int userSpicyLevel = userRepository.findByEmail(userEmail).getPersonalSpicyLevel();

		UserCharacter userCharacter = userCharacterRepository.findByCharacterName(userRepository.findByEmail(userEmail).getNickName());
		String characterName = userCharacter.getCharacterName();
		String characterMyPageImage = userCharacter.getCharacterMyPageImage();

		return new MemberInfoResponse(userName, characterName, characterMyPageImage, userSpicyLevel);
	}
}
