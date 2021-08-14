<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(function(){

	/* 정보 입력 기능 */
	$("input.memo").on("input", function(){
		var memo = $(this).val();
		var idx = $(this).parent().nextAll("td.idx_td").children("input.idx_num").val();

		if(memo == 'dele') {
 			$(this).parents("tr").css({
				"display" : "none"
			});
			// ajax 호출
			$.ajax({
				type: "GET"
				, url: "/deleteOne"
				, data: { "idx" : idx }
				, success : function(){
					console.log("정상적으로 컨트롤러를 작동했습니다.");
				}
				, error: function(){
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			}); // ajax end
		} // if - end		
	}); // input.memo oninput function end(입력기능)
	
	/* 
		정보 업데이트 기능
		blur 시점의 값을 컨트롤러로 보내고,
		차이점이 있는지 없는지의 유무에 따라 기능 진행.
	*/
	$("input.whenUsed, select.cate1, input.price, input.content, input.place, input.memo").blur(function(){
		
		var current_data = $(this).val();
		var current_class = $(this).attr("class");
		var idx = $(this).parent().nextAll("td.idx_td").children("input.idx_num").val();
		/* 
			아무것도 없는 인풋창을 blur 했을 때, null이 아니라 ""공백으로 인식,
			차이점이 발생했다고 처리하였고, 업데이트가 진행되었음.
			때문에 jsp에서 먼저 공백처리해서 보내겠음!
		*/		
		if( current_data == "" ) {
			console.log("공백 값은 처리하지 않습니다.");
			return false;
			
		} else {
			/* console.log("blur후 상태 값 : " + current_data);
			console.log("현재칼럼 : " + current_class);
			console.log("고유넘버 : " + idx); */
			
			$.ajax({
				type: "GET"
				, url: "updateThis"
				, data: { "category" : current_class
						, "item" : current_data
						, "idx" : idx }
				, success: function() {
					console.log("정보수정이 정상적으로 처리 되었습니다.");
				}
				, error: function() {
					console.log("정보수정 중 오류가 발생하였습니다.");
				}
			}); //ajax end			
		} // if - end
		
	}); // blur(); end (업데이트기능)
}); // javaScript end
</script>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>날짜</th>
				<th>분류</th>
				<th>금액</th>
				<th>내용</th>
				<th>메모</th>
				<th>사용처</th>
				<th><!-- 인덱스 --></th>
			</tr>
		</thead>
		<tbody>
			<!-- 컨트롤러에서 가계부 내역을, 모델list에 담아 왔고, 그것을 items에 넣고 다시 vo로 명칭  -->
			<c:forEach items="${list}" var="vo">
				<tr>
					<td><input class="whenUsed" type="text" value="${vo.whenUsed}" /></td>
					<td>
						<select class="cate1">
							<option value="grocery"
								<c:if test="${vo.cate1 == 'grocery'}">selected</c:if>>식재료</option>
							<option value="dinnin"
								<c:if test="${vo.cate1 == 'dinnin'}">selected</c:if>>외식</option>
							<option value="snacks"
								<c:if test="${vo.cate1 == 'snacks'}">selected</c:if>>간식</option>
							<option value="cafe"
								<c:if test="${vo.cate1 == 'cafe'}">selected</c:if>>카페</option>
							<option value="daily-use"
								<c:if test="${vo.cate1 == 'daily-use'}">selected</c:if>>생필품</option>
							<option value="date"
								<c:if test="${vo.cate1 == 'date'}">selected</c:if>>데이트</option>
							<option value="rent"
								<c:if test="${vo.cate1 == 'rent'}">selected</c:if>>월세</option>
							<option value="electric"
								<c:if test="${vo.cate1 == 'electric'}">selected</c:if>>전기요금</option>
							<option value="gas"
								<c:if test="${vo.cate1 == 'gas'}">selected</c:if>>가스요금</option>
							<option value="clothes"
								<c:if test="${vo.cate1 == 'clothes'}">selected</c:if>>의류</option>
							<option value="cosmetics"
								<c:if test="${vo.cate1 == 'cosmetics'}">selected</c:if>>미용</option>
							<option value="book"
								<c:if test="${vo.cate1 == 'book'}">selected</c:if>>도서</option>
							<option value="present"
								<c:if test="${vo.cate1 == 'present'}">selected</c:if>>선물</option>
							<option value="etc"
								<c:if test="${vo.cate1 == 'etc'}">selected</c:if>>기타</option>
						</select>
					</td>
					<td><input class="price" type="text" value="${vo.price}" /></td>
					<td><input class="content" type="text" value="${vo.content}" /></td>
					<td><input class="memo" type="text" value="${vo.memo}" /></td>
					<td><input class="place" type="text" value="${vo.place}" /></td>
					<td class="idx_td"><input class="idx_num" type="text" value="${vo.idx_num}" hidden /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>