package com.example.hotspot_local.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDto {

	private Long storeId;
	private String storeName;
	private int x_axis;
	private int y_axis;
	private int storeSpicyLevel;
	private String localNumberAddress;
	private String loadNameAddress;
	private String phoneNumber;


}
