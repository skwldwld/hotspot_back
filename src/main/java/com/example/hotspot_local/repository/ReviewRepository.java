package com.example.hotspot_local.repository;

import com.example.hotspot_local.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {}
