package com.example.hotspot_local.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
// 여긴 login한 유저 관련 정보만 담을까? 그리고 User에 이제 다른 test 결과들 넣는 거 어떤데?
// 아님 이거 없애고 걍 User에 바로 다 정보 넣는 방식을 사용하던가 해야겠네..
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String email;

	private String role;
}
