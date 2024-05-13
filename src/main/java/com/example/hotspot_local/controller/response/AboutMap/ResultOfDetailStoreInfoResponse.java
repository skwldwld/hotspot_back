package com.example.hotspot_local.controller.response.AboutMap;

import lombok.Data;

@Data
public class ResultOfDetailStoreInfoResponse {
	private String storeId;
	private String storeName;
	private String localNumberAddress;
	private double xAxis;
	private double yAxis;

	private String phoneNumber; // 얘는 detail에서만 쓰임

}
