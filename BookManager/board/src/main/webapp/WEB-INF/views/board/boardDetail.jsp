<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>

body{
    font-size: 14px;
    font-family: 'Roboto', sans-serif;
}

.boardDetail{
	margin:0 auto;
	width:500px;
	height:400px;
}

.btnArea{
	margin: 0 auto;
	text-align: center;
	margin-top: 30px;
}

input[type="button"]{
    color: #fff;
    font-size: 16px;
    background-color: #6A24FE;
}
</style>
<body>
<div class="boardDetail">
	<h2 style="text-align: center">${boardDetail.title}</h2><hr><br>
	<textarea rows="30" cols="50" readonly style="margin-left:65px;">${boardDetail.content}</textarea>
	
	
	<div class="btnArea">
		<input type="button" name="boardUpdate" value="수정" onclick="updateBoard(${boardDetail.seq_num})">
		<input type="button" name="boardDelete" value="삭제" onclick="deleteBoard(${boardDetail.seq_num})">
		<input type="button" name="list" value="목록" onclick="back()">
	</div>
</div>
</body>


<script>

function updateBoard(seq_num)
{
	location.href="boardUpdate?seq_num="+seq_num;
}

function deleteBoard(seq_num)
{
	location.href="boardDelete?seq_num="+seq_num;
}
function back()
{
	history.back();	
}


</script>
</html>