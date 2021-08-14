<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>이건 과연 완성 될까?</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	//date 입력창의 value 값을 항상 현재 날짜 기준으로 적용하기
	$(function(){
		var today = new Date();
		document.getElementById("whenUsed").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	}); //jQeury Section end
	
	//form 전송작업
	function fn_submit() {
		var price = document.getElementById("price").value;
		
		//price null 값 체크
		if(price === null || price === "") {
			alert("금액을 입력하세요!");
			return false; //return false 안해 놓으면, 금액이 입력되어 있지 않더라도 다음 코드로 진행됨 주의.
		} else {
			var submit_form = document.insertUsedMoney;
			//submit_form.method = "get" or "post"로 변경도 가능
			submit_form.method = "get";
			//form name 값 .action = "컨트롤러 url 맵핑주소";
			submit_form.action = "spendThisMuch";
			submit_form.submit();
		}
	} //function fn_submit() end
	
</script>
</head>
<body>
	<!-- action="컨트롤러의 맵핑URL주소, 처음 /슬래쉬는 떼고!" -->
	<!-- 
		지금은 form에서 바로 action을 취하지 않고,
		cate2의 값에 따라서 자바스크립트를 이용 - form을 전송할 계획임.	 
	 -->
	<form name="insertUsedMoney" action="insertToFamily" method="post">
		<div id="inputSection">
			<input id="whenUsed" name="whenUsed" type="date"/>
			<select id="cate1" name="cate1">
				<option value="grocery">식재료</option>
				<option value="dinnin">외식</option>
				<option value="snacks">간식</option>
				<option value="cafe">카페</option>
				<option value="daily-use">생필품</option>
				<option value="date">데이트</option>
				<option value="rent">월세</option>
				<option value="electric">전기요금</option>
				<option value="gas">가스요금</option>
				<option value="clothes">의류</option>
				<option value="cosmetics">미용</option>
				<option value="book">도서</option>
				<option value="present">선물</option>
				<option value="etc">기타</option>
			</select>
			<input id="price" name="price" type="number" placeholder="금액"/>
			<input id="content" name="content" type="text" placeholder="내용"/>
			<input id="memo" name="memo" type="text" placeholder="메모"/>
			<input id="place" name="place" type="text" placeholder="사용처"/>
			<select id="cate2" name="cate2">
				<option value="1">공동</option>
				<option value="2">개인</option>
			</select>
			<button type="button" onclick="fn_submit();">입력</button>
		</div>
	</form> <!-- form end -->
	<div id="pageController">
		<button type="button" onclick="location.href='<c:url value="/howMuchSpent"/>'" >리스트 불러오기</button>
	</div> <!-- div#pageController -->
</body>
</html>