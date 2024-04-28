package com.example.hotspot_local.service;

import com.example.hotspot_local.controller.response.ResultOfMaps;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KakaoMapService {
	@Value("${kakao.api.key}")
	private String kakaoApiKey;

	private final WebClient webClient;

	public KakaoMapService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("https://dapi.kakao.com").build();
	}

	// Mono : execute once
	// Flux : execute many times

	// search many places (15 stores) :: on map
	public Mono<List<ResultOfMaps>> searchPlaces(String query, String category_group_code, double x, double y, int radius, int page) {
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

	public List<ResultOfMaps> extractResults(JsonNode jsonNode) {
		List<ResultOfMaps> results = new ArrayList<>();

		JsonNode documents = jsonNode.path("documents");

		if (documents.isArray()) {
			for (JsonNode item : documents) {
				ResultOfMaps resultOfMaps = new ResultOfMaps();

				resultOfMaps.setStoreName(item.path("place_name").asText());
				resultOfMaps.setXAxis(item.path("x").asDouble());
				resultOfMaps.setYAxis(item.path("y").asDouble());
				resultOfMaps.setLocalNumberAddress(item.path("address_name").asText());
				resultOfMaps.setLoadNameAddress(item.path("road_address_name").asText());
				resultOfMaps.setPhoneNumber(item.path("phone").asText());

				results.add(resultOfMaps);
			}
		} else {
		}

		return results;
	}

	// search many places three times :: on store list
	public Mono<List<ResultOfMaps>> searchPlacesMultiplePages(String query, String category_group_code, double x, double y, int radius, int page) {
		List<Mono<List<ResultOfMaps>>> pageRequests = new ArrayList<>();

		for (int i = 1; i <= page; i++) {
			final int total_page = i;
			Mono<List<ResultOfMaps>> pageRequest = searchPlaces(query, category_group_code, x, y, radius, total_page);
			pageRequests.add(pageRequest);
		}

		return Flux.mergeSequential(pageRequests)
			.collectList()
			.map(lists -> lists.stream().flatMap(List::stream).collect(Collectors.toList()));
	}

	// search only one place
	public Mono<ResultOfMaps> searchPlace(String query, String category_group_code, double x, double y, int radius) {
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

	public ResultOfMaps extractResult(JsonNode jsonNode) {
		ResultOfMaps resultOfMaps = new ResultOfMaps();

		JsonNode documents = jsonNode.path("documents");
		JsonNode firstItem = documents.get(0);

		if (firstItem != null) {
			resultOfMaps.setStoreName(firstItem.path("place_name").asText());
			resultOfMaps.setXAxis(firstItem.path("x").asDouble());
			resultOfMaps.setYAxis(firstItem.path("y").asDouble());
			resultOfMaps.setLocalNumberAddress(firstItem.path("address_name").asText());
			resultOfMaps.setLoadNameAddress(firstItem.path("road_address_name").asText());
			resultOfMaps.setPhoneNumber(firstItem.path("phone").asText());
		} else {
			resultOfMaps.setStoreName("검색 결과가 없습니다.");
		}

		return resultOfMaps;
	}


}