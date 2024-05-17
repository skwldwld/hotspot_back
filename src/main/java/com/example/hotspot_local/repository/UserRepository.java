package com.example.hotspot_local.repository;

import com.example.hotspot_local.domain.User;
import com.example.hotspot_local.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(Long userId);

	User findByUserName(String username);
}
