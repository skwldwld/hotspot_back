package com.example.hotspot_local.exception;

public class UserException {

	public static void UserLoginCheck(String userId) {
		if(userId.isEmpty()) {
			throw new IllegalArgumentException("userId is empty");
		}
	}

}
