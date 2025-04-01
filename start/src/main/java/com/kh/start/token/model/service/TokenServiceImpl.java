package com.kh.start.token.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.start.auth.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
	
	private final JwtUtil tokenUtil; 

	@Override
	public Map<String, String> generateToken(String username) {
		//1, 2
		Map<String, String> tokens = createToken(username);
		
		//3
		
		//4
		
		//5
		
		return tokens;
	}
	
	private Map<String, String> createToken(String username) {
		String accessToken = tokenUtil.getAccessToken(username);
		String refreshToken = tokenUtil.getRefreshToken(username);
		
		Map<String, String> tokens = new HashMap<>();
		tokens.put("accessToken", accessToken);
		tokens.put("refreshToken", refreshToken);
		
		return tokens;
	}

}
