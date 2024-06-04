package com.example.hotspot_local.controller;

import com.example.hotspot_local.controller.response.AboutMyPage.MemberInfoResponse;
import com.example.hotspot_local.controller.response.AboutMyPage.MemberReviewListResponse;
import com.example.hotspot_local.controller.response.OAuth.MemberResponse;
import com.example.hotspot_local.dto.ReviewDto;
import com.example.hotspot_local.repository.StoreRepository;
import com.example.hotspot_local.service.MyPageService;
import com.example.hotspot_local.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/auth/mypage")
	public ResponseEntity<MemberResponse> myPage(@SessionAttribute("email") String email) {
		MemberResponse memberResponse = new MemberResponse(email);
//    System.out.println(memberResponse);
		return ResponseEntity.ok().body(memberResponse);
	}

}
