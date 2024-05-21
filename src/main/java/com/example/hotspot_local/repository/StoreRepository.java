package com.example.hotspot_local.repository;

import com.example.hotspot_local.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	ArrayList<Store> findByUserEmail(String email);

//	@Query(value = "SELECT * FROM store WHERE spiLevel = ?1", nativeQuery = true)
//	ArrayList<Store> findByStoreSpiLevel(int spiLevel);

}
