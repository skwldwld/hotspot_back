package com.example.hotspot_local.domain;

import com.example.hotspot_local.dto.ReviewDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long reviewId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String comment;

	@Column(nullable = false)
	private int reviewSpicyLevel;

	private String reviewImage;

	private String foodName;

	private String storeId;

//	private String userToken;   // userId로 구분할 듯! userToken은 발급 여부만 확인하는 걸로..
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	public static Review from(ReviewDto reviewDto, User user) {
		return Review.builder()
				.title(reviewDto.getTitle())
				.comment(reviewDto.getComment())
				.reviewSpicyLevel(reviewDto.getReviewSpicyLevel())
				.reviewImage(reviewDto.getReviewImage())
				.foodName(reviewDto.getFoodName())
				.storeId(reviewDto.getStoreId())
				.user(user)
				.build();
	}




}
