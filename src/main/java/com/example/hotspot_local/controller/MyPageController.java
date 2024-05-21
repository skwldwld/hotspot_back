package com.example.hotspot_local.controller;

import com.example.hotspot_local.dto.ReviewDto;
import com.example.hotspot_local.repository.StoreRepository;
import com.example.hotspot_local.service.MyPageService;
import com.example.hotspot_local.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class MyPageController {

	private final MyPageService myPageService;
	private final ReviewService reviewService;
	private final StoreRepository storeRepository;

//	@GetMapping("/auth/mypage/memberInfo")
//	public ResponseEntity<>

	@GetMapping("/auth/mypage/reviewlist")
	public ResponseEntity<ArrayList<ReviewDto>> reviewList(@RequestParam String userEmail) {
		ArrayList<ReviewDto> reviewList = reviewService.findReviewByUser(userEmail);
		return ResponseEntity.ok().body(reviewList);
	}

	@GetMapping("/auth/mypage/scraplist")
	public ResponseEntity<Void> scrapList(@RequestParam String userEmail) {
		return ResponseEntity.ok().build();
	}

}
