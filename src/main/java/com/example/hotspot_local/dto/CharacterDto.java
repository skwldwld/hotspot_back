package com.example.hotspot_local.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterDto {
	private String characterImage;
	private String characterName;
	private String characterInfo;

}
