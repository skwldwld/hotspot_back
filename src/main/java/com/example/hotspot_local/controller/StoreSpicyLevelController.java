package com.example.hotspot_local.controller;

import com.example.hotspot_local.controller.request.AboutMap.StoreSpicyLevelRequest;
import com.example.hotspot_local.controller.request.AboutMap.UserReviewRequest;
import com.example.hotspot_local.controller.response.AboutMap.StoreReviewInfo;
import com.example.hotspot_local.repository.StoreRepository;
import com.example.hotspot_local.service.ReviewService;
import com.example.hotspot_local.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class StoreSpicyLevelController {

	private final ReviewService reviewService;

	@GetMapping("/store/spicy-level")
	public ResponseEntity<StoreReviewInfo> getStoreSpicyLevel(@RequestParam String storeId) {
		StoreReviewInfo storeReviewInfo = reviewService.getStoreSpicyLevel(storeId);
		return ResponseEntity.ok().body(storeReviewInfo);
	}

}
