package com.example.hotspot_local.controller;

import com.example.hotspot_local.service.S3UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class S3Controller {
	private final S3UploadService s3UploadService;

	@PostMapping("/image")
	public ResponseEntity<String> updateUserImage(@RequestParam("images") MultipartFile multipartFile) {
		String uploadUrl;
		try {
			uploadUrl = s3UploadService.uploadFiles(multipartFile, "viaF");
		} catch (Exception e) {
			return ResponseEntity.ok().body("이미지 업로드에 실패했습니다.");
		}
		return ResponseEntity.ok().body(uploadUrl);
	}

	@PostMapping("/images")
	public ResponseEntity<String> updateUserImages(@RequestParam("images") MultipartFile[] multipartFiles) {
		String uploadUrl = "";
		try {
			for (MultipartFile multipartFile : multipartFiles) {
				uploadUrl += s3UploadService.uploadFiles(multipartFile, "viaF") + "\n";
			}
		} catch (Exception e) {
			return ResponseEntity.ok().body("이미지 업로드에 실패했습니다.");
		}
		return ResponseEntity.ok().body(uploadUrl);
	}

}
