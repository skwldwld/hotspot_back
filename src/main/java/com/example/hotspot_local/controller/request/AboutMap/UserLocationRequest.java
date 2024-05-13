package com.example.hotspot_local.controller.request.AboutMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLocationRequest {
	double xAxis;
	double yAxis;
	int radius;
}
