<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
body{
    font-size: 14px;
    font-family: 'Roboto', sans-serif;
}
.boardWrite{
	margin: 0 auto;
	width: 1000px;
	height: 800px;
}

h2{
	text-align:center;
}

table{
	margin: 0 auto;
	width: 500px;
	height: 600px;
}

tr{
	width:500px;
	height:50px;

}

.btnDiv{
	padding-left : 380px;
}
</style>
</head>
<body>

<form method="post" action="../board/write.do" id="write-form">
<input type="hidden" name="writeUser" value="${member.id}">
<div class="boardWrite">
<h2>게시글 등록</h2>
<table>
<tr>
	<th>제목</th>
	<td><input type="text" name="title"></td>
</tr>
<tr>
	<th>내용</th>
	<td><textarea rows="30" cols="50" name="content"></textarea></td>
</tr>

	
</table>
<div class="btnDiv">
	<td><input type="submit" value="등록"
	style="
	width:60px; 
	height:40px;
	color: #fff;
    font-size: 16px;
    background-color: #6A24FE;"
	></td>
	<td><input type="button" onclick ="cancel()" value="취소" 
	style="margin-left:80px; 
	width:60px; 
	height:40px;
	color: #fff;
    font-size: 16px;
    background-color: #6A24FE;"></td>
</div>
</div>
</form>
<script>
	var title = document.getElementsByName("title")[0].value;
	vat content = document.getElementsByName("content")[0].value;
	
	function cancel()
	{
		history.back();
	}
	
</script>

</body>
</html>