<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>달력형 가계부</title>
<style>
	div {
	border: 1px solid #f00;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(function(){
		/* 기간 제거 기능 */
		$("button.delePeriod").click(function(){
			var idx = $(this).prev("span.idx").text();
			console.log("삭제된 기간의 idx : " + idx);
			// 삭제 컨트롤러 ajax 호출
			$.ajax({
				type: "GET"
				, url: "/deletePeriod"
				, data : { "idx" : idx }
				, success : function(){
					console.log("기간 삭제가 정상적으로 완료되었습니다.");
					window.location = location;
				}
				, error: function(){
					console.log("기간 삭제 중 오류가 발생 하였습니다.");
				}
			});
		});
	});
</script>
</head>
<body>
<form action="/setPeriod" method="post">
from<input type="date" id="startDate" name="startDate" />
to<input type="date" id="endDate" name="endDate" />
<button type="submit">저장</button>
</form>
<c:forEach items="${period}" var="pvo">
<div>
<p>${pvo.startPoint} ~ ${pvo.endPoint}</p>
<span class="idx">${pvo.idx_num}</span>
<button type="button" class="delePeriod">삭제</button>
</div>
</c:forEach>
</body>
</html>