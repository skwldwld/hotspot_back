package com.example.hotspot_local.domain;

import com.example.hotspot_local.dto.ReviewDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "user") // 순환참조 방지
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_email", referencedColumnName = "Email")
	@JsonBackReference  // 순환참조 방지
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
