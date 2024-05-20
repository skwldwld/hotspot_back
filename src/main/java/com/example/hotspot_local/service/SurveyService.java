package com.example.hotspot_local.service;

import com.example.hotspot_local.controller.request.AboutSurvey.UserScoreRequest;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {
	UserScoreRequest userScoreRequest;

	public int getUserScoreRequest(UserScoreRequest userScoreRequest) {
		int sum = 0;
		int[] surveyScoreList = userScoreRequest.getSurveyScore();

		for(int surveyScore : surveyScoreList){
			sum += surveyScore;
		}

		if (sum <= 17) {
			return 1 ;      // 맵구
		} else if (sum <= 34) {
			return 2;       // 맵노스
		} else if (sum <= 51) {
			return 3;      // 맵물주
		} else if (sum <= 68) {
			return 4;   // 위암플래너
		} else {
			return 5;  // 실비요정
		}


		return userScoreRequest;
	}

}
