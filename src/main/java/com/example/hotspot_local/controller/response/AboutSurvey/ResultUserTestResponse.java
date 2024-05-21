package com.example.hotspot_local.controller.response.AboutSurvey;

import com.example.hotspot_local.dto.UserCharacterDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class ResultUserTestResponse {

	private UserCharacterDto myCharacter;
	private ArrayList<UserCharacterDto> otherCharacter;
}
