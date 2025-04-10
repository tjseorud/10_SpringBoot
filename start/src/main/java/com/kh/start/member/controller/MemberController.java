package com.kh.start.member.controller;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.start.member.model.dto.ChangePasswordDTO;
import com.kh.start.member.model.dto.MemberDTO;
import com.kh.start.member.model.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	/*
	 *  회원가입
	 * ID/PW/NAME 받아서
	 * 가공 후
	 * 서비스로 넘기고 -> 여기 일은 모름
	 * 서비로부터 된걸 받아서
	 * JSON형태로 상태코드와 함께 넘겨요
	 */
	
	/*
	 * /members == 약속
	 * GET					--> 조회요청(select)
	 * GET(/members/멤버번호)	--> 멤버번호로 조건을 걸어서 단일 조회요청(select)
	 * POST					--> 데이터 생성요청(insert)
	 * PUT					--> 데이터 갱신요청(update)
	 * DELETE				--> 데이터 삭제요청(delete)
	 * 
	 * 계층구조로 식별할 때 		/자원/PK
	 * 요청시 전달값이 많을 때 	/자원?K=V&K=V&K=V 
	 */
	
	@PostMapping
	public ResponseEntity<?> signUp(@RequestBody @Valid MemberDTO member) {
		//log.info("CT : {}",member);
		memberService.signUp(member);
		
		return ResponseEntity.status(201).build();
	}
	
	/*
	 * 현재 비밀번호 :
	 * 새 비밀번호 :
	 * 새 비밀번호 확인 :
	 */
	//PW 변경 기능구현
	@PutMapping
	public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordDTO passwordEntity) {
		//log.info("비번비번 {}",passwordEntity);
		memberService.changePassword(passwordEntity);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteByPassword(@RequestBody Map<String, String> request) {
		//log.info("이거 지워줘 {}",request);
		memberService.deleteByPassword(request.get("password"));
		
		return ResponseEntity.ok("지워짐");
	}
	
}
