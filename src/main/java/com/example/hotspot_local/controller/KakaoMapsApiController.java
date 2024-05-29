package com.example.hotspot_local.controller;

import com.example.hotspot_local.controller.response.AboutMap.ResultOfDetailStoreInfoResponse;
import com.example.hotspot_local.controller.response.AboutMap.ResultOfStoresInfo;
import com.example.hotspot_local.service.KakaoMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class KakaoMapsApiController {

	private final KakaoMapService kakaoMapService;

	@GetMapping("/get/stores")
	public Mono<List<ResultOfStoresInfo>> searchPlacesCache(@RequestParam double x, @RequestParam double y, @RequestParam int radius) {
		String query = "음식점";
		String category_group_code = "FD6";
		int page = 1;
		return kakaoMapService.saveStoresInCache(query, category_group_code, x, y, radius, page)
			.then(kakaoMapService.searchPlaces(query, category_group_code, x, y, radius, page));
	}

	@GetMapping("/get/stores/detail")
	public Mono<ResultOfDetailStoreInfoResponse> searchStore(@RequestParam String storeId) {
		return kakaoMapService.getStoreFromCache(storeId);
	}

	@GetMapping("/stores/specific") // change address (get 생략)
	public ArrayList<ResultOfStoresInfo> searchSpecificStore(@RequestParam double x, @RequestParam double y, @RequestParam int radius, @RequestParam int spicyLevel) {
		String query = "음식점";
		String category_group_code = "FD6";
		int page = 3;
		Mono<List<ResultOfStoresInfo>> tmp  = kakaoMapService.searchPlacesMultiplePages(query, category_group_code, x, y, radius, page);

		return kakaoMapService.categorizedStores(tmp, spicyLevel);
	}

	@GetMapping("/searches/threetimes")    // the number of food store is 45 stores.
	public Mono<List<ResultOfStoresInfo>> searchPlacesThreeTimes(@RequestParam double x, @RequestParam double y, @RequestParam int radius)  {
		String query = "음식점";
		String category_group_code = "FD6";
		int page = 3;
		return kakaoMapService.searchPlacesMultiplePages(query, category_group_code, x, y, radius, page);
	}


// Behind codes are temporal codes for developing.

	@GetMapping("/search")
	public Mono<ResultOfDetailStoreInfoResponse> searchPlace(@RequestParam double x, @RequestParam double y, @RequestParam int radius) {
		String query = "음식점";
		String category_group_code = "FD6";
		return kakaoMapService.searchPlace(query, category_group_code, x, y, radius);
	}
}

