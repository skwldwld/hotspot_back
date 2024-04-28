package com.example.hotspot_local.controller;

import com.example.hotspot_local.controller.response.ResultOfMaps;
import com.example.hotspot_local.service.KakaoMapService;
import com.example.hotspot_local.controller.request.UserLocationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class KakaoMapsApiController {

	private final KakaoMapService kakaoMapService;

	@GetMapping("/search")  // 받는 부분을 UserLocationRequest로 받아서 처리하는 코드로 바꿀 것
	public Mono<ResultOfMaps> searchPlace(/*@RequestParam String query,@RequestParam String category_group_code,*/ @RequestParam double x, @RequestParam double y, @RequestParam int radius) {
		String query = "음식점";
		String category_group_code = "FD6";
		return kakaoMapService.searchPlace(query, category_group_code, x, y, radius);
	}

	@GetMapping("/searches")    // the number of food store is 15 stores. <- use this api.
	public Mono<List<ResultOfMaps>> searchPlaces(/*@RequestParam String query, @RequestParam String category_group_code,*/ @RequestParam double x, @RequestParam double y, @RequestParam int radius) {
		String query = "음식점";
		String category_group_code = "FD6";
		int page = 1;
		return kakaoMapService.searchPlaces(query, category_group_code, x,y,radius, page);
	}

	@GetMapping("/searches/threetimes")    // the number of food store is 45 stores.
	public Mono<List<ResultOfMaps>> searchPlacesTreeTimes(/*@RequestParam String query, @RequestParam String category_group_code,*/ @RequestParam double x, @RequestParam double y, @RequestParam int radius)  {
		String query = "음식점";
		String category_group_code = "FD6";
		int page = 3;
		return kakaoMapService.searchPlacesMultiplePages(query, category_group_code, x, y, radius, page);
	}
}

