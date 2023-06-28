<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지</title>
<link rel="stylesheet" href="/resources/css/member/join.css">
<link rel="stylesheet" href="/resources/css/main.css">
</head>
<body>
<c:import url="../default/header.jsp"></c:import>
	<div class="wrapper">
		<form id="join_form" method="post">
			<div class="wrap">
				<div class="subjecet">
					<span>회원가입</span>
				</div>
				<div class="id_wrap">
					<div class="id_name">아이디</div>
					<div class="id_input_box">
						<input class="id_input" name="memberId">
					</div>
					<span class="id_input_re_1">사용 가능한 아이디입니다.</span>
					<span class="id_input_re_2">아이디가 이미 존재합니다.</span>	
					<span class="final_id_ck">아이디를 입력해주세요.</span>			
				</div>
				<div class="pw_wrap">
					<div class="pw_name">비밀번호</div>
					<div class="pw_input_box">
						<input class="pw_input" name="memberPw">
					</div>
					<span class="final_pw_ck">비밀번호를 입력해주세요.</span>
				</div>
				<div class="pwck_wrap">
					<div class="pwck_name">비밀번호 확인</div>
					<div class="pwck_input_box">
						<input class="pwck_input">
					</div>
					<span class="final_pwck_ck">비밀번호 확인을 입력해주세요.</span>
					<span class="pwck_input_re_1">비밀번호가 일치합니다.</span>
					<span class="pwck_input_re_2">비밀번호가 일치하지 않습니다.</span>
				</div>
				<div class="user_wrap">
					<div class="user_name">이름</div>
					<div class="user_input_box">
						<input class="user_input" name="memberName">
					</div>
					<span class="final_name_ck">이름을 입력해주세요.</span>
				</div>
				<div class="mail_wrap">
					<div class="mail_name">이메일</div> 
					<div class="mail_input_box">
						<input class="mail_input" name="memberMail">
					</div>
					<span class="final_mail_ck">이메일을 입력해주세요.</span>
					<sapn class="mail_input_box_warn"></sapn>
					<div class="mail_check_wrap">
						<div class="mail_check_input_box" id="mail_check_input_box_false">
							<input class="mail_check_input" disabled="disabled">
						</div>
						<div class="mail_check_button">
							<span>인증번호 전송</span>
						</div>
						<div class="clearfix"></div>
						<span id="mail_check_input_box_warn"></span>
					</div>
				</div>
				<div class="address_wrap">
					<div class="address_name">주소</div>
					<div class="address_input_1_wrap">
						<div class="address_input_1_box">
							<input class="address_input_1" name="memberAddr1" readonly="readonly">
						</div>
						<div class="address_button" onclick="execution_daum_address()">
							<span>주소 찾기</span>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class ="address_input_2_wrap">
						<div class="address_input_2_box">
							<input class="address_input_2" name="memberAddr2" readonly="readonly">
						</div>
					</div>
					<div class ="address_input_3_wrap">
						<div class="address_input_3_box">
							<input class="address_input_3" name="memberAddr3" readonly="readonly">
						</div>
					</div>
					<span class="final_addr_ck">주소를 입력해주세요.</span>
				</div>
				<div class="join_button_wrap">
					<input type="button" class="join_button" value="가입하기">
				</div>
			</div>
		</form>
	</div>
<c:import url="../default/footer.jsp"></c:import>	
</body>
</html>