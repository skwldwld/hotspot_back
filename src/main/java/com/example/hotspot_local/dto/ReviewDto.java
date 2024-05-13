package com.example.hotspot_local.dto;

import com.example.hotspot_local.controller.request.AboutMap.UserReviewRequest;
import com.example.hotspot_local.domain.Review;
import com.example.hotspot_local.domain.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {

	private String title;
	private String comment;
	private int reviewSpicyLevel;
	private String reviewImage;
	private String foodName;
	private String storeId;
	private int userId;

	public ReviewDto(UserReviewRequest userReviewRequest) {
		this.title = userReviewRequest.getTitle();
		this.comment = userReviewRequest.getComment();
		this.reviewSpicyLevel = userReviewRequest.getReviewSpicyLevel();
		this.reviewImage = userReviewRequest.getReviewImage();
		this.foodName = userReviewRequest.getFoodName();
		this.storeId = userReviewRequest.getStoreId();
		this.userId = Integer.parseInt(userReviewRequest.getUserId());
	}

}
