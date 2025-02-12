<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
*{
    padding: 0;
    margin: 0;
    border: none;
}
body{
    font-size: 14px;
    font-family: 'Roboto', sans-serif;
}

.join-wrapper{
	margin: 0 auto;
    width: 400px;
    height: 350px;
    padding: 40px;
    box-sizing: border-box;
}

.join-wrapper > h2{
    font-size: 24px;
    color: #6A24FE;
    margin-bottom: 20px;
}
#join-form > input{
    width: 100%;
    height: 48px;
    padding: 0 10px;
    box-sizing: border-box;
    margin-bottom: 16px;
    border-radius: 6px;
    background-color: #F8F8F8;
}
#join-form > input::placeholder{
    color: #D2D2D2;
}
#join-form > input[type="submit"]{
    color: #fff;
    font-size: 16px;
    background-color: #6A24FE;
    margin-top: 20px;
}

#join-form > input[type="button"]{
    color: #fff;
    font-size: 16px;
    background-color: #6A24FE;
}

#join-form > label{
    color: #999999;
}

</style>

<body>
<div class="join-wrapper">
        <h2>회원가입</h2>
        <form method="post" action="/member/join.do" id="join-form">
            <input type="text" name="joinId" placeholder="아이디">
            <input type="password" name="joinPassword" placeholder="패스워드">
            <input type="text" name="joinName" placeholder="사용자명">
            <input type="submit" value="회원가입">
            <input type="button" value="취소" onclick="back()">
        </form>
    </div>
</body>

<script>
	function back()
	{
		history.back();
	}
</script>
</html>