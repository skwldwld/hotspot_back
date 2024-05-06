package com.example.hotspot_local;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HotspotLocalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotspotLocalApplication.class, args);
	}

}
