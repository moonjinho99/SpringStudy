<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css" />
<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
</head>
<style>
.bookList{
	margin:0 auto;
    width: 1000px;
    height:600px;
    box-sizing: border-box;
}
h2{
	text-align: center;
}
table{
	text-align: center;
	width: 1000px;
	height: 100px;
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
	max-width: 60%;
    height: auto;
    display: block;
    padding: 5px;
    margin-top: 10px;
    margin: auto;	
}
</style>
<body>
<div class="bookList">
	<div class="bookListHeader">
		<h2>책 목록</h2>
		<a href="/member/logout" style="float:left;">로그아웃</a>
		<input type="button" id="regBtn" value="등록하기" style="text-align:right;" onclick="regBookPage()"/>
	</div>
	<table id="book_table">
		<thead>
			<tr>
				<th>번호</th>
				<th>이미지</th>
				<th>제목</th>
				<th>종류</th>
				<th>저자</th>
				<th>등록일</th>
				<th>재고</th>
				<th>가격</th>
			</tr>
		</thead>
	</table>

</div>
</body>
 <script type="text/javascript" src="../resources/js/book/bookList.js"></script>
</html>