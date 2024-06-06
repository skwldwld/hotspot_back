package com.example.hotspot_local.controller;

import com.example.hotspot_local.controller.request.AboutSurvey.UserScoreRequest;
import com.example.hotspot_local.controller.response.AboutSurvey.ResultUserTestResponse;
import com.example.hotspot_local.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class SurveyController {

	private final SurveyService surveyService;

	@GetMapping("/get/survey/result")
	public ResponseEntity<ResultUserTestResponse> survey(@RequestParam String email, @RequestParam int[] surveyScore){
		UserScoreRequest userScoreRequest = new UserScoreRequest(surveyScore, email);
		ResultUserTestResponse resultUserTestResponse = surveyService.getUserScoreRequest(userScoreRequest);
		return ResponseEntity.ok().body(resultUserTestResponse);
	}

	@PatchMapping("/patch/survey/result ")
	public ResponseEntity<ResultUserTestResponse> patchSurvey(@RequestParam String email, @RequestParam int[] surveyScore){
		UserScoreRequest userScoreRequest = new UserScoreRequest(surveyScore, email);
		ResultUserTestResponse resultUserTestResponse = surveyService.patchUserScoreRequest(userScoreRequest);
		return ResponseEntity.ok().body(resultUserTestResponse);
	}

}
