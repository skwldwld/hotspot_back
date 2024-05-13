package com.example.hotspot_local.dto;

import com.example.hotspot_local.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
	private Long userId;
	private String userName;
	private String nickName;
	private String characterImage;
	private int personalSpicyLevel;
	private List<ReviewDto> reviewList;
	private List<StoreDto> storeList;

}
