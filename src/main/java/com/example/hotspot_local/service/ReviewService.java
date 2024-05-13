package com.example.hotspot_local.service;

import com.example.hotspot_local.controller.request.AboutMap.UserReviewRequest;
import com.example.hotspot_local.domain.Review;
import com.example.hotspot_local.domain.User;
import com.example.hotspot_local.dto.ReviewDto;
import com.example.hotspot_local.exception.UserException;
import com.example.hotspot_local.repository.ReviewRepository;
import com.example.hotspot_local.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final UserRepository userRepository;


	public void save(UserReviewRequest userReviewRequest) throws NullPointerException {

		UserException.UserLoginCheck(userReviewRequest.getUserId());

		ReviewDto reviewDto = new ReviewDto(userReviewRequest);
		User targetUser = userRepository.findByUserId(Long.parseLong(userReviewRequest.getUserId()));

		reviewRepository.save(Review.from(reviewDto, targetUser));
	}



}
