package com.web.book.service;

import com.web.book.model.MemberVO;

public interface MemberService {
	/* 회원가입 */
	public void memberJoin(MemberVO member) throws Exception;
	
	/* 아이디 중복 검사 */
	public int idCheck(String memberId) throws Exception;

	public MemberVO memberLogin(MemberVO member)throws Exception;
}
