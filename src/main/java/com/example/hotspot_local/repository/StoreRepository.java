package com.example.hotspot_local.repository;

import com.example.hotspot_local.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	ArrayList<Store> findByUserEmail(String email);
}
