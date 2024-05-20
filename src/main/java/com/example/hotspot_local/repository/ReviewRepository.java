package com.example.hotspot_local.repository;

import com.example.hotspot_local.domain.Review;
import com.example.hotspot_local.dto.ReviewDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	ArrayList<Review> findByStoreId(String storeId);

	Review findByReviewId(Long reviewId);
}
