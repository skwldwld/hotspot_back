package com.example.hotspot_local.service;

import com.example.hotspot_local.controller.response.AboutMap.ResultOfDetailStoreInfoResponse;
import com.example.hotspot_local.controller.response.AboutMap.ResultOfStoresInfo;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class KakaoMapService {
	@Value("${kakao.api.key}")
	private String kakaoApiKey;

	private final WebClient webClient;

	private ConcurrentHashMap<String, ResultOfDetailStoreInfoResponse> cache;

	@PostConstruct
	public void init() {
		cache = new ConcurrentHashMap<>();
	}

	public KakaoMapService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("https://dapi.kakao.com").build();
	}

	// search many places (15 stores) :: on map
	public Mono<List<ResultOfStoresInfo>> searchPlaces(String query, String category_group_code, double x, double y, int radius, int page) {
		return this.webClient.get()
			.uri(uriBuilder -> uriBuilder.path("/v2/local/search/keyword.json")
				.queryParam("query", query)
				.queryParam("category_group_code", category_group_code)
				.queryParam("x", x)
				.queryParam("y", y)
				.queryParam("radius", radius)
				.queryParam("page", page)
				.build())
			.header("Authorization", "KakaoAK " + kakaoApiKey)
			.retrieve()
			.bodyToMono(JsonNode.class)
			.map(this::extractResults);
	}

	public List<ResultOfStoresInfo> extractResults(JsonNode jsonNode) {
		List<ResultOfStoresInfo> results = new ArrayList<>();

		JsonNode documents = jsonNode.path("documents");

		if (documents.isArray()) {
			for (JsonNode item : documents) {
				ResultOfStoresInfo resultOfMaps = new ResultOfStoresInfo();
				resultOfMaps.setStoreName(item.path("place_name").asText());
				resultOfMaps.setXAxis(item.path("x").asDouble());
				resultOfMaps.setYAxis(item.path("y").asDouble());
				resultOfMaps.setLocalNumberAddress(item.path("address_name").asText());
				resultOfMaps.setStoreId(item.path("id").asText());
				results.add(resultOfMaps);
			}
		}
		return results;
	}

	// search many places in cache (15 stores) :: on map
	public Mono<List<ResultOfDetailStoreInfoResponse>> searchPlacesInCache(String query, String category_group_code, double x, double y, int radius, int page) {
		return this.webClient.get()
			.uri(uriBuilder -> uriBuilder.path("/v2/local/search/keyword.json")
				.queryParam("query", query)
				.queryParam("category_group_code", category_group_code)
				.queryParam("x", x)
				.queryParam("y", y)
				.queryParam("radius", radius)
				.queryParam("page", page)
				.build())
			.header("Authorization", "KakaoAK " + kakaoApiKey)
			.retrieve()
			.bodyToMono(JsonNode.class)
			.map(this::extractResultsAboutCache);
	}

	public List<ResultOfDetailStoreInfoResponse> extractResultsAboutCache(JsonNode jsonNode) {
		List<ResultOfDetailStoreInfoResponse> results = new ArrayList<>();

		JsonNode documents = jsonNode.path("documents");

		if (documents.isArray()) {
			for (JsonNode item : documents) {
				ResultOfDetailStoreInfoResponse resultOfMaps = new ResultOfDetailStoreInfoResponse();

				resultOfMaps.setStoreName(item.path("place_name").asText());
				resultOfMaps.setXAxis(item.path("x").asDouble());
				resultOfMaps.setYAxis(item.path("y").asDouble());
				resultOfMaps.setLocalNumberAddress(item.path("address_name").asText());
				resultOfMaps.setPhoneNumber(item.path("phone").asText());
				resultOfMaps.setStoreId(item.path("id").asText());

				results.add(resultOfMaps);
			}
		}
		return results;
	}

	// store/put stores_list in cache -> if we want to check the cache, then change the code.
	public Mono<Void> saveStoresInCache(String query, String category_group_code, double x, double y, int radius, int page) {
//		System.out.println("start saveStoresInCache");
		return searchPlacesInCache(query, category_group_code, x, y, radius, page)
			.flatMapMany(Flux::fromIterable) // "Mono<List<ResultOfMaps>>" -> Flux<ResultOfMaps>
			.flatMap(store -> cacheStore(store.getStoreId(), store))
			.then();
//			.doOnSuccess(unused -> System.out.println("complete saveStoresInCache")); // check this method.
	}


	// @CachePut does not support Mono and Flux.
	public Mono<ResultOfDetailStoreInfoResponse> cacheStore(String storeId, ResultOfDetailStoreInfoResponse store) {
    return Mono.fromSupplier(
        () -> {
          cache.put(storeId, store);
//          System.out.println("성공!");
          return store;
        });
	}

	public Mono<ResultOfDetailStoreInfoResponse> getStoreFromCache(String storeId) {
		return Mono.justOrEmpty(cache.get(storeId));
	}


	// search many places three times :: on store list
	public Mono<List<ResultOfDetailStoreInfoResponse>> searchPlacesMultiplePages(String query, String category_group_code, double x, double y, int radius, int page) {
		List<Mono<List<ResultOfDetailStoreInfoResponse>>> pageRequests = new ArrayList<>();

		for (int i = 1; i <= page; i++) {
			Mono<List<ResultOfDetailStoreInfoResponse>> pageRequest = searchPlacesInCache(query, category_group_code, x, y, radius, i);
			pageRequests.add(pageRequest);
		}

		return Flux.mergeSequential(pageRequests)
			.collectList()
			.map(lists -> lists.stream().flatMap(List::stream).collect(Collectors.toList()));
	}

	// search only one place
	public Mono<ResultOfDetailStoreInfoResponse> searchPlace(String query, String category_group_code, double x, double y, int radius) {
		return this.webClient.get()
			.uri(uriBuilder -> uriBuilder.path("/v2/local/search/keyword.json")
				.queryParam("query", query)
				.queryParam("category_group_code", category_group_code)
				.queryParam("x", x)
				.queryParam("y", y)
				.queryParam("radius", radius)
				.build())
			.header("Authorization", "KakaoAK " + kakaoApiKey)
			.retrieve()
			.bodyToMono(JsonNode.class)
			.map(this::extractResult);
	}

	public ResultOfDetailStoreInfoResponse extractResult(JsonNode jsonNode) {
		ResultOfDetailStoreInfoResponse resultOfMaps = new ResultOfDetailStoreInfoResponse();

		JsonNode documents = jsonNode.path("documents");
		JsonNode firstItem = documents.get(0);

		if (firstItem != null) {
			resultOfMaps.setStoreName(firstItem.path("place_name").asText());
			resultOfMaps.setXAxis(firstItem.path("x").asDouble());
			resultOfMaps.setYAxis(firstItem.path("y").asDouble());
			resultOfMaps.setLocalNumberAddress(firstItem.path("address_name").asText());
			resultOfMaps.setPhoneNumber(firstItem.path("phone").asText());
			resultOfMaps.setStoreId(firstItem.path("id").asText());

		} else {
			resultOfMaps.setStoreName("검색 결과가 없습니다.");
		}

		return resultOfMaps;
	}


}