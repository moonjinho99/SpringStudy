<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<!-- <script src="js/jquery.min.js" type="text/javascript"></script> -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css" />
<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
</head>
<style>
body{
    font-size: 14px;
    font-family: 'Roboto', sans-serif;
}

.board{
margin: 0 auto;
    width: 800px;
    height: 800px;
    box-sizing: border-box;
}

h2{
	text-align:center;
}

table{
	text-align: center;
	width: 800px;
	height: 200px;
}

table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}

th{
	color:#fff;
	background-color: #6A24FE;
}

img{
	width: 80px;
	height: 80px;
}
</style>
<body>

<div class="board">
	<h2>게시판</h2>
	<h5>${member.name}님 환영합니다!</h5>
	<a href="../member/logout" style="float:right;">로그아웃</a>
	<a href="boardWrite">글 등록</a>
	<table id="board_table">
		<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
		</thead>		
	</table>
</div>
</body>

<script>
$(document).ready(function(){
	$('#board_table').DataTable({
		pageLength: 2,
		bPaginate: true,
		bLengthChange: true,
		lengthMenu : [[2,3,5,10,-1],[2,3,5,10,"All"]],
		bAutoWidth: false,
		processing: true,
		ordering: true,
		responsive: true,
		bServerSide: false,
		sServerMethod: "POST",
		searching: true,
		ajax : {
			"url":"../board/getBoardList.do",
			"type":"POST",
			"data": function(d){
				
			}
		},
		columns : [
			{data: "seq_num"},
			{data: "title"},
			{data: "writeUser"}
		]
		
	});
});

$("#board_table").on('click', 'tbody tr', function () {
	    var row = $("#board_table").DataTable().row(this).data();
	    console.log(row.seq_num);
		location.href="http://localhost:8080/board/boardDetail?seq_num="+row.seq_num;
	});

</script>
</html>