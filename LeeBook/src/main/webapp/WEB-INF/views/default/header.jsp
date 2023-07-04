<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헤더</title>
<link rel="stylesheet" href="../resources/css/header.css?v=1.0">  
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>  

</head>
<body>
	<div class="wrapper_header">
		<div class="wrap_header">
			<div class="top_gnb_area">
				<ul class="list">
					<c:if test = "${member == null}">	<!-- 로그인 x -->	
						<li >
							<a href="/member/login">로그인</a>
						</li>
						<li>
							<a href="/member/join">회원가입</a>
						</li>
					</c:if>
					<c:if test="${member != null }">	<!-- 로그인 o -->		
						<c:if test="${member.adminCk == 1 }">	<!-- 관리자 계정 -->
							<li><a href="/admin/main">관리자 페이지</a></li>
						</c:if>							
						<li>
							<a href="/member/logout.do">로그아웃</a>
						</li>
						<li>
							마이페이지
						</li>
						<li>
							<a href="/cart/${member.memberId}">장바구니</a>
						</li>
					</c:if>				
					<li>
						고객센터
					</li>			
				</ul>			
			</div>
			<div class="top_area">
				<!-- 로고영역 -->
				<div class="logo_area">
					<a href="/main"><img src="${contextPath }/resources/img/mLogo.png"></a>
				</div>
				<div class="search_area">
                	<div class="search_wrap">
                		<form id="searchForm" action="/search" method="get">
                			<div class="search_input">
                				<select name="type">
                					<option value="T">책 제목</option>
                					<option value="A">작가</option>
                				</select>
                				<input type="text" name="keyword">
                    			<button class='btn search_btn'>검 색</button>                				
                			</div>
                		</form>
                	</div>
				</div>
				<div class="login_area">			
					<!-- 로그인 하지 않은 상태 -->
					<c:if test = "${member == null }">
						<div class="login_button"><a href="/member/login">로그인</a></div>
						<span><a href="/member/join">회원가입</a></span>				
					</c:if>									
					<!-- 로그인한 상태 -->
					<c:if test="${ member != null }">
						<div class="login_success_area">
							<span>회원 : ${member.memberName}</span>
							<span>충전금액 : <fmt:formatNumber value="${member.money }" pattern="\#,###.##"/></span>
							<span>포인트 : <fmt:formatNumber value="${member.point }" pattern="#,###" /></span>
							
						</div>
					</c:if>					
				</div>
				<div class="clearfix"></div>			
			</div>
			<div class="navi_bar_area">
				<div class="dropdown">
				    <button class="dropbtn">국내 
				      <i class="fa fa-caret-down"></i>
				    </button>
				    <div class="dropdown-content">
				    	<c:forEach items="${cate1}" var="cate"> 
				    		<a href="search?type=C&cateCode=${cate.cateCode}">${cate.cateName}</a>
				    	</c:forEach>	      		      		      
				    </div>			
				</div>
				<div class="dropdown">
				    <button class="dropbtn">국외 
				      <i class="fa fa-caret-down"></i>
				    </button>
				    <div class="dropdown-content">
				    	<c:forEach items="${cate2}" var="cate"> 
				    		<a href="search?type=C&cateCode=${cate.cateCode}">${cate.cateName}</a>
				    	</c:forEach>     		      		      
				    </div>			
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">			
/* gnb_area 로그아웃 버튼 작동 */
	$("#gnb_logout_button").click(function(){
		//alert("버튼 작동");
		$.ajax({
			type:"POST",
			url:"/member/logout.do",
			success:function(data){
				alert("로그아웃 성공");
				document.location.reload();	 
			} 
		}); // ajax 
	});
	
</script>	
</body>
</html>