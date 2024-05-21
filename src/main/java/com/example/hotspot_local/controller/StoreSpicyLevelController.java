package com.example.hotspot_local.controller;

import com.example.hotspot_local.controller.response.AboutMap.StoreReviewInfo;
import com.example.hotspot_local.dto.ReviewDto;
import com.example.hotspot_local.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class StoreSpicyLevelController {

	private final ReviewService reviewService;

	@GetMapping("/get/store/spicy-level")
	public ResponseEntity<StoreReviewInfo> getStoreSpicyLevel(@RequestParam String storeId) {
		StoreReviewInfo storeReviewInfo = reviewService.getStoreSpicyLevel(storeId);
		return ResponseEntity.ok().body(storeReviewInfo);
	}

	@GetMapping("/get/store/reviewList")
	public ResponseEntity<ArrayList<ReviewDto>> getStoreReview(@RequestParam String storeId) {
		ArrayList<ReviewDto> storeReviewInfo = reviewService.getReviewList(storeId);
		return ResponseEntity.ok().body(storeReviewInfo);
	}

}
