package com.web.book.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.book.model.MemberVO;
import com.web.book.model.OrderDTO;
import com.web.book.model.OrderPageDTO;
import com.web.book.service.MemberService;
import com.web.book.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private OrderService orderService;
	
	
	@GetMapping("/order/{memberId}")
	public String orderPgaeGET(@PathVariable("memberId") String memberId, OrderPageDTO opd, Model model) {
		
		model.addAttribute("orderList", orderService.getGoodsInfo(opd.getOrders()));
		model.addAttribute("memberInfo", memberService.getMemberInfo(memberId));
		return "/order";
		
	}

	@PostMapping("/order")
	public String orderPagePost(OrderDTO od, HttpServletRequest request) {

		System.out.println(od);

		orderService.order(od);

		MemberVO member = new MemberVO();
		member.setMemberId(od.getMemberId());

		HttpSession session = request.getSession();

		try {
			MemberVO memberLogin = memberService.memberLogin(member);
			memberLogin.setMemberPw("");
			session.setAttribute("member", memberLogin);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "redirect:/main";
	}
	
	
}
