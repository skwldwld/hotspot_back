package com.example.hotspot_local.service;

import com.example.hotspot_local.repository.ReviewRepository;
import com.example.hotspot_local.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

	private final ReviewRepository reviewRepository;



}
