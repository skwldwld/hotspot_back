package com.example.hotspot_local.exception;

public class UserException {

	public static void UserLoginCheck(String userEmail) {
		if(userEmail.isEmpty()) {
			throw new IllegalArgumentException("userEmail is empty");
		}
	}

}
