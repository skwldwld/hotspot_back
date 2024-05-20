package com.example.hotspot_local.controller.request.AboutSurvey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserScoreRequest {

    private int[] surveyScore = new int[17];
	private String email;

}