package com.example.hotspot_local.controller.request;

import lombok.Data;

@Data
public class UserLocationRequest {
	double xaxis;
	double yaxis;
	int radius;
}
