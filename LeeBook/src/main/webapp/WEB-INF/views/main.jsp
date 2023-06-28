<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
	<c:import url="default/header.jsp"></c:import>
		<div class="content_area">
			<div class="slide_div_wrap">
				<div class="slide_div">
					<div>
						<a> <img src="../resources/img/bnA_w01_a8daff.jpg"
							style="width: 100%; height: 100%;">
						</a>
					</div>
					<div>
						<a> <img src="../resources/img/bnD_w01_c3c5f7.jpg"
							style="width: 100%; height: 100%;">
						</a>
					</div>
					<div>
						<a> <img src="../resources/img/bnK_w01_c3c5f7.jpg"
							style="width: 100%; height: 100%;">
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="ls_wrap">
			<div class="ls_div_subject">평점순 상품</div>
			<div class="ls_div">
				<c:forEach items="${ls}" var="ls">
					<a href="/goodsDetail/${ls.bookId}">
						<div class="ls_div_content_wrap">
							<div class="ls_div_content">
								<div class="image_wrap" data-bookid="${ls.imageList[0].bookId}"
									data-path="${ls.imageList[0].uploadPath}"
									data-uuid="${ls.imageList[0].uuid}"
									data-filename="${ls.imageList[0].fileName}">
									<img>
								</div>
								<div class="ls_category">${ls.cateName}</div>
								<div class="ls_rating">${ls.ratingAvg}</div>
								<div class="ls_bookName">${ls.bookName}</div>
							</div>
						</div>
					</a>
				</c:forEach>
			</div>
		</div>
	<c:import url="default/footer.jsp"></c:import>
	<script>
	$(document).ready(function(){
		
		$(".slide_div").slick(
				{
					dots: true,
					autoplay : true,
					infinite: true,
					autoplaySpeed: 3000,
					fade: true,
					cssEase: 'linear'
				}				
		);	
		
		$(".ls_div").slick({
			slidesToShow: 4,
			slidesToScroll: 4,
			prevArrow : "<button type='button' class='ls_div_content_prev'>이전</button>",		// 이전 화살표 모양 설정
			nextArrow : "<button type='button' class='ls_div_content_next'>다음</button>",		// 다음 화살표 모양 설정	
		});		
		
		/* 이미지 삽입 */
		$(".image_wrap").each(function(i, obj){
			
			const bobj = $(obj);
			
			if(bobj.data("bookid")){
				const uploadPath = bobj.data("path");
				const uuid = bobj.data("uuid");
				const fileName = bobj.data("filename");
				
				const fileCallPath = encodeURIComponent(uploadPath + "/s_" + uuid + "_" + fileName);
				
				$(this).find("img").attr('src', '/display?fileName=' + fileCallPath);
			} else {
				$(this).find("img").attr('src', '/resources/img/goodsNoImage.png');
			}
			
		});			
				
	});

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