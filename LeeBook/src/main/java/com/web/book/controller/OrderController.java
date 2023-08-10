package com.web.book.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.book.model.MemberVO;
import com.web.book.model.OrderDTO;
import com.web.book.model.OrderItemDTO;
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
	@ResponseBody
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
			
			
			System.out.println("총가격"+od);
			List<OrderItemDTO> orderItemList = od.getOrders(); // OrderItemDTO 객체들이 담긴 List
			
			int totalPrice = 0;

			for (OrderItemDTO orderItem : orderItemList) {
			    int itemTotalPrice = orderItem.getTotalPrice();
			    totalPrice += itemTotalPrice;
			   
			    
			}
			System.out.println("Total Price: " + totalPrice);

			// 카카오페이 결제 준비 API 요청을 위한 파라미터 설정

			// 결제 내역 테이블에 추가
			// 가맹점 코드(10자)
			String cid = "TC0ONETIME";
			// 가맹점 주문번호(최대 100자)
			String partner_order_id = "partner_order_id";

			// 가맹점 회원 id(최대 100자)
			String partner_user_id = "id";

			// 상품 이름 (각각의 메뉴이름을 합쳐서 결제하면 해결됨)
			String item_name ="초코파이";

			// 상품 수량
			String quantity = "0";

			// 상품 총액
			int total_amount = totalPrice;

			// 상품 부가세 금액(필수 X)
			int vat_amount = 0;

			// 상품 비과세 금액
			int tax_free_amount = 0;

			String success_url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ "/main";
			String fail_url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ "/fail";
			String cancel_url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ "/main";

			// 카카오페이 결제 준비 API 요청 파라미터 설정
			StringBuilder sb = new StringBuilder();
			sb.append("cid=").append(cid).append("&partner_order_id=").append(partner_order_id)
					.append("&partner_user_id=").append(partner_user_id).append("&item_name=").append(item_name)
					.append("&quantity=").append(quantity).append("&total_amount=").append(total_amount)
					.append("&vat_amount=").append(vat_amount).append("&tax_free_amount=").append(tax_free_amount)
					.append("&approval_url=").append(success_url).append("&fail_url=").append(fail_url)
					.append("&cancel_url=").append(cancel_url);

			URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "KakaoAK 070c771b567f337d6694caa9488ed0e0");
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			connection.setDoOutput(true);

			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(sb.toString().getBytes("UTF-8"));
			outputStream.close();

			int responseCode = connection.getResponseCode();
			InputStream inputStream;
			if (responseCode == 200) {
				inputStream = connection.getInputStream();
			} else {
				inputStream = connection.getErrorStream();
			}

			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuilder responseBuilder = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				responseBuilder.append(line);
			}

			bufferedReader.close();
			connection.disconnect();

			return responseBuilder.toString();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "{\"result\":\"success\"}";
	}
	
	
}
