package com.example.hotspot_local.dto;

import com.example.hotspot_local.domain.UserCharacter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCharacterDto {
	private String characterFrontBigImage;
	private String characterFrontSmallImage;
	private String characterBackImage;
	private String characterName;
	private String characterInfo;
	private int spicyLevel;


	public static UserCharacterDto form(UserCharacter userCharacter){
		return UserCharacterDto.builder()
				.characterFrontBigImage(userCharacter.getCharacterFrontBigImage())
				.characterFrontSmallImage(userCharacter.getCharacterFrontSmallImage())
				.characterBackImage(userCharacter.getCharacterBackImage())
				.characterName(userCharacter.getCharacterName())
				.characterInfo(userCharacter.getCharacterInfo())
				.spicyLevel(userCharacter.getSpicyLevel())
				.build();
	}

}
