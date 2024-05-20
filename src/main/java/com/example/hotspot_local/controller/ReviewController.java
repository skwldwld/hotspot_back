package com.example.hotspot_local.controller;

import com.example.hotspot_local.controller.request.AboutMap.UserReviewRequest;
import com.example.hotspot_local.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

	private final ReviewService reviewService;

	@PostMapping("/post/store/review")
	public ResponseEntity<Void> review(@RequestBody UserReviewRequest userReviewRequest) {
		reviewService.save(userReviewRequest);
		return ResponseEntity.ok().build();
	}



}
