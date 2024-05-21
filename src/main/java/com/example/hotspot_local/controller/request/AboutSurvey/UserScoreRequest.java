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
// email이 비어 있으면, 그냥 결과만 반환.
// email이 안 비어 있으면, 결과 + db에다가 저장!
}