package com.example.hotspot_local.domain;

import com.example.hotspot_local.domain.Review;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	private String userName;

	private String nickName;

//	private String characterImage;

	private int personalSpicyLevel;

	@Column(unique = true)
	private String email;

	@JsonManagedReference   // 순환 참조 방지
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Review> reviewList = new ArrayList<>();

	@JsonManagedReference
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Store> storeList = new ArrayList<>();


	public static User from(String userName, String nickName, int personalSpicyLevel, String email) {
		return User.builder()
				.userName(userName)
				.nickName(nickName)
				.personalSpicyLevel(personalSpicyLevel)
				.email(email)
				.build();
	}

	public static User form(String userName, String nickName, int personalSpicyLevel, String email, ArrayList<Review> reviewList, ArrayList<Store> storeList) {
		return User.builder()
				.userName(userName)
				.nickName(nickName)
				.personalSpicyLevel(personalSpicyLevel)
				.email(email)
				.reviewList(reviewList)
				.storeList(storeList)
				.build();
	}

}
