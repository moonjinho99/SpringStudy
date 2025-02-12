<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>
<style>

.bookReg{
	margin: 0 auto;
	width:1000px;
	height:800px;
}

h2{
	text-align:center;
}

table{
	text-align: center;
	width:1500px;
	height:800px;
}
.insertData{
	width:800px;
	height:50px;
}

.bookRegFooter{
	width:1000px;
	height:100px;
	text-align:center;
}

.bookRegFooter>input{
	float:left;
	padding:5px;
	font-size:20px;
	margin-right:30px;
	width:100px;
}

#result_card img{
		max-width: 40%;
	    height: auto;
	    display: block;
	    padding: 5px;
	    margin-top: 10px;
	    margin: auto;	
	}
	#result_card {
		position: relative;
	}

.imgDeleteBtn{
	    position: absolute;
	    top: 0%;
	    right: 33%;
	    background-color: #ef7d7d;
	    color: wheat;
	    width: 30px;
	    height: 30px;
	    border-radius: 50%;
	    text-align: center;
	    border: none;
	    display: block;
	    cursor: pointer;	
	}

</style>
<body>
<div class="bookReg">
	<div class="bookRegHeader">
		<h2>책 등록 페이지</h2>
	</div>
	
	<table class="bookRegTable" id="bookRegTable">
		<tr>
			<th>제목</th>
			<td><input type="text" id="book_title" class="insertData" placeholder="책 제목을 입력하세요"></td>
		</tr>
		
		<tr>
			<th>장르</th>
			<td><select id="book_category" class="insertData">
				<option value="소설">소설</option>
				<option value="시">시</option>
				<option value="수필">수필</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<th>저자</th>
			<td><input type="text" id="book_author" class="insertData" placeholder="저자를 입력하세요"></td>
		</tr>
		
		<tr>
			<th>재고</th>
			<td><input type="text" id="book_quantity" class="insertData" placeholder="재고를 입력하세요"></td>
		</tr>
		
		<tr>
			<th>가격</th>
			<td><input type="text" id="book_price" class="insertData" placeholder="가격을 입력하세요"></td>
		</tr>
		
		<tr>
			<th>내용</th>
			<td><textarea rows="1" cols="30" id = "book_content" class="insertData" placeholder="줄거리를 입력하세요" style="width:800px; height:400px;"></textarea></td>
		</tr>
		<tr>
			<th>이미지</th>
			<td><div id ="uploadResult">
					
					</div></td>
				</div>
			<td><input type="file" id="bookRegImg" name="uploadFile"></td>
		</tr>
	</table>
	<div class="bookRegFooter" style="margin-top:30px;">
		<input type="button" value="목록" onclick="javascript:history.back()" style="margin-left:400px;"/>
		<input type="button" value="등록하기" onclick="regData()"/>
	</div>
</div>
</body>
 <script type="text/javascript" src="../resources/js/book/bookReg.js"></script>
</html>