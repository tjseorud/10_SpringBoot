package com.kh.start.auth.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {
	
	// 애플리케이션 설정파일()에 정의된
	// 속성의 값들을 클래스 내부에서 불러서 사용하고싶다!
	@Value("${jwt.secret}")
	private String secretKey;
	
	{
		log.info("{}",secretKey);
	}
		
}
