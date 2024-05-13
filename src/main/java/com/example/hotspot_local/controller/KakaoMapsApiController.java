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

import java.util.List;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class KakaoMapsApiController {

	private final KakaoMapService kakaoMapService;

	@GetMapping("/search")  // 받는 부분을 UserLocationRequest로 받아서 처리하는 코드로 바꿀 것
	public Mono<ResultOfDetailStoreInfoResponse> searchPlace(@RequestParam double x, @RequestParam double y, @RequestParam int radius) {
		String query = "음식점";
		String category_group_code = "FD6";
		return kakaoMapService.searchPlace(query, category_group_code, x, y, radius);
	}

	// before find 15 stores (use cache)
	@GetMapping("/searches/Stores")
	public Mono<List<ResultOfStoresInfo>> searchPlacesNoCache(@RequestParam double x, @RequestParam double y, @RequestParam int radius) {
		String query = "음식점";
		String category_group_code = "FD6";
		int page = 1;
		// reactive programming (Mono랑 Flux 사용하는 것)에서는 asynchroneous하게 동작하기 때문에, 이렇게 chaining/subscribe를 통해 순차적으로 동작하도록 해야 함. (.then()을 사용해야 함.)
		// 그렇지 않을 경우, 실행할 필요가 없다고 여기고 실행하지 않고 넘어감.
		return kakaoMapService.saveStoresInCache(query, category_group_code, x, y, radius, page)    // cache에 저장은 하고,
			.then(kakaoMapService.searchPlaces(query, category_group_code, x, y, radius, page));    // 다른 내용을 전달해도 무관하긴 하겠지..
	}



	// 폼 바꿔야 함. -> request로 받아서 처리하는 코드로 바꿀 것ㅎ
	@GetMapping("/searches/Stores/")
	public Mono<ResultOfDetailStoreInfoResponse> searchStore(@RequestParam String storeId) {
		return kakaoMapService.getStoreFromCache(storeId);
	}

	@GetMapping("/searches/threetimes")    // the number of food store is 45 stores.
	public Mono<List<ResultOfDetailStoreInfoResponse>> searchPlacesTreeTimes(/*@RequestParam String query, @RequestParam String category_group_code,*/ @RequestParam double x, @RequestParam double y, @RequestParam int radius)  {
		String query = "음식점";
		String category_group_code = "FD6";
		int page = 3;
		return kakaoMapService.searchPlacesMultiplePages(query, category_group_code, x, y, radius, page);
	}
}

