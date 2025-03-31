package com.kh.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping
// SpringBoot는 기본적으로 produces속성이 application/json charset=UTF-8이기 때문에
// 자동으로 MessageConvertor로 변환됨
public class TestContrller {
	/*
	 * Spring Starter
	 * 
	 * 특정 기능에 필용한 의존성을 한번에 관리할수 있는 개념
	 * 
	 * 각각의 Starter는 관련된 라이브러리들의 집합으로 모든 의존성을 하나의 Starter로 쉽게 추가할 수 있음
	 * 
	 * ex)
	 * spring-boot-starter-web : 웹 애플리케이션 개발에 필요한 의존성틀
	 *  (Sevlet, Spring MVC, Jackson 등)
	 * spring-boot-starter-security : 스프링 시큐리티와 관련된 의존성틀
	 * 
	 * 필요한 기능에 맞는 Starter만 추가하면 되니까 의존성을 직접관리할 필요가 없음
	 * 모든 개발자가 동일한 Starter를 사용하기 때문에 프로젝트간 의존성 충돌도 방지할 수 있음
	 * 
	 * * Starter에 모든 라이브러리가 존재하는것은 아님
	 */
	
	@Autowired
	private StudyBeen studyBeen;
	
	@GetMapping
	public ResponseEntity<String> getTest() {
		return ResponseEntity.ok("응답 확인!");
	}
	
}
