package com.example.hotspot_local.service;

import com.example.hotspot_local.controller.request.AboutSurvey.UserScoreRequest;
import com.example.hotspot_local.controller.response.AboutSurvey.ResultUserTestResponse;
import com.example.hotspot_local.domain.User;
import com.example.hotspot_local.domain.UserCharacter;
import com.example.hotspot_local.domain.UserEntity;
import com.example.hotspot_local.dto.UserCharacterDto;
import com.example.hotspot_local.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class SurveyService {

	private final UserCharacterRepository userCharacterRepository;
	private final OAuthRepository oAuthRepository;
	private final UserRepository UserRrepository;
	private final ReviewRepository reviewRepository;
	private final StoreRepository storeRepository;

	public ResultUserTestResponse getUserScoreRequest(UserScoreRequest userScoreRequest) {

		int sum = addAllSurveyScore(userScoreRequest.getSurveyScore());
		String characterName = getCharacterName(sum);

	    UserCharacterDto myCharacter = UserCharacterDto.form(userCharacterRepository.findByCharacterName(characterName));
		ArrayList<UserCharacterDto> otherCharacter = getOtherCharacter(characterName);

		saveUserScore(userScoreRequest, myCharacter);

		return new ResultUserTestResponse(myCharacter, otherCharacter);
	}

	public void saveUserScore(UserScoreRequest userScoreRequest, UserCharacterDto myCharacter) {

		String userEmail = userScoreRequest.getEmail();
		if(userEmail == null || userEmail.isEmpty()) return;

		UserEntity userEntity = oAuthRepository.findByEmail(userEmail);

		UserRrepository.save(User.from(userEntity.getUsername(), myCharacter.getCharacterName(), myCharacter.getSpicyLevel(),userEntity.getEmail()));
	}

	private ArrayList<UserCharacterDto> getOtherCharacter(String characterName) {

		ArrayList<UserCharacter> userCharacterList = (ArrayList<UserCharacter>) userCharacterRepository.findAll();

		ArrayList<UserCharacterDto> otherCharacter = new ArrayList<>();
		for(UserCharacter userCharacter : userCharacterList) {
			if (!userCharacter.getCharacterName().equals(characterName)) {
				otherCharacter.add(UserCharacterDto.form(userCharacter));
			}
		}

		return otherCharacter;
	}

	private String getCharacterName(int sum) {
		if (sum <= 17) return "맵구" ;
		else if (sum <= 34) return "맵노스";
		else if (sum <= 51) return "맵물주";
		else if (sum <= 68) return "위암플래너";
		else return "실비요정";
	}

	private int addAllSurveyScore(int[] surveyScoreList) {
		int sum = 0;
		for(int surveyScore : surveyScoreList) sum += surveyScore;
		return sum;
	}

}
