package com.kh.start.member.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

//Value Object
//불변성 setter가 없음

@Value
@Getter
@Builder
public class MemberVO {
	
	private Long memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String role;
}
