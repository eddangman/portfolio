package com.web.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	
	//회원가입
	@RequestMapping(value = "/join",method = RequestMethod.GET )
	public void join() {
		
	}
}
