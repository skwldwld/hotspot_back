package com.example.hotspot_local.controller;

import com.example.hotspot_local.controller.response.AboutMyPage.MemberInfoResponse;
import com.example.hotspot_local.controller.response.AboutMyPage.MemberReviewListResponse;
import com.example.hotspot_local.dto.ReviewDto;
import com.example.hotspot_local.repository.StoreRepository;
import com.example.hotspot_local.service.MyPageService;
import com.example.hotspot_local.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MyPageController {

	private final MyPageService myPageService;
	private final ReviewService reviewService;
	private final StoreRepository storeRepository;

	@GetMapping("/auth/mypage/memberInfo")
	public ResponseEntity<MemberInfoResponse> memberInfo(@RequestParam String userEmail) {
		MemberInfoResponse memberInfoResponse = myPageService.findMemberInfo(userEmail);
		return ResponseEntity.ok().body(memberInfoResponse);
	}

	@GetMapping("/auth/mypage/reviewlist")
	public ResponseEntity<MemberReviewListResponse> reviewList(@RequestParam String userEmail) {
		MemberReviewListResponse reviewList = reviewService.findReviewByUser(userEmail);
		return ResponseEntity.ok().body(reviewList);
	}

//	MVP 아님 -> 이번엔 미구현
//	@GetMapping("/auth/mypage/scraplist")
//	public ResponseEntity<Void> scrapList(@RequestParam String userEmail) {
//		return ResponseEntity.ok().build();
//	}

//	@GetMapping("/auth/mypage")
//	public ResponseEntity<MemberInfoResponse> myPage(@RequestParam String userEmail) {
//		MemberInfoResponse memberInfoResponse = myPageService.findMemberInfo(userEmail);
//		MemberReviewListResponse reviewList = reviewService.findReviewByUser(userEmail);
//		memberInfoResponse.setReviewList(reviewList.getReviewList());
//		return ResponseEntity.ok().body(memberInfoResponse);
//	}

}
