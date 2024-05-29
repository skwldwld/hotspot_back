package com.example.hotspot_local.controller;

import com.example.hotspot_local.controller.response.OAuth.MemberResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.Collections;
import java.util.Map;

@RestController
public class SecurityController {

	@GetMapping("/logout")  // 이거 하면 user_entity에서 값을 삭제해버릴까? 그렇게 해서 로그인 여부 확인하면 될 듯..
	public ResponseEntity<?> logout(HttpServletRequest request) {
		var session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return ResponseEntity.ok().body("로그아웃되었습니다.");
	}


	@GetMapping("/login")
	public ResponseEntity<MemberResponse> user(@AuthenticationPrincipal OAuth2User principal) {
		MemberResponse memberResponse = new MemberResponse(principal.getAttribute("email").toString(), principal.getAttribute("name").toString());
		return ResponseEntity.ok().body(memberResponse);
	}

}
