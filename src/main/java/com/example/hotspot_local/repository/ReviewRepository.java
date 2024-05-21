package com.example.hotspot_local.repository;

import com.example.hotspot_local.domain.Review;
import com.example.hotspot_local.dto.ReviewDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	ArrayList<Review> findByStoreId(String storeId);

	Review findByReviewId(Long reviewId);

	ArrayList<Review> findByUserEmail(String email);
}
