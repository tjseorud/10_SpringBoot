package com.kh.start.token.model.service;

import java.util.Map;

public interface TokenService {
	//1. AccessToken 만들기
	//2. RefreshToken 만들기
	Map<String, String> generateToken(String username, Long memberNo);
	
	//3. RefreshToken Insert Into Table
	//4. 만료된 RefreshToken Delete
	//5. 사용자가 RefreshToken을 가지고 증명할때 Select From DB
	Map<String, String> refreshToken(String refreshToken);
	
}
