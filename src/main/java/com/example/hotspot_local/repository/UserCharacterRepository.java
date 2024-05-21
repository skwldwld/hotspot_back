package com.example.hotspot_local.repository;

import com.example.hotspot_local.domain.UserCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCharacterRepository extends JpaRepository<UserCharacter, Long> {
	UserCharacter findByCharacterName(String characterName);
}
