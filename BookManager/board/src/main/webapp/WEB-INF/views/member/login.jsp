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

.login-wrapper{
	margin: 0 auto;
    width: 400px;
    height: 350px;
    padding: 40px;
    box-sizing: border-box;
}

.login-wrapper > h2{
    font-size: 24px;
    color: #6A24FE;
    margin-bottom: 20px;
}
#login-form > input{
    width: 100%;
    height: 48px;
    padding: 0 10px;
    box-sizing: border-box;
    margin-bottom: 16px;
    border-radius: 6px;
    background-color: #F8F8F8;
}
#login-form > input::placeholder{
    color: #D2D2D2;
}
#login-form > input[type="submit"]{
    color: #fff;
    font-size: 16px;
    background-color: #6A24FE;
    margin-top: 20px;
}

#login-form > input[type="button"]{
    color: #fff;
    font-size: 16px;
    background-color: #6A24FE;
}

#login-form > label{
    color: #999999;
}

</style>
<body>
    <div class="login-wrapper">
        <h2>로그인</h2>
        <form method="post" action="/member/login.do" id="login-form">
            <input type="text" name="userId" placeholder="아이디">
            <input type="password" name="userPassword" placeholder="패스워드">
            <input type="submit" value="로그인">
            <input type="button" value="회원가입" onclick="moveJoin()">
        </form>
    </div>
</body>
<script>
	function moveJoin()
	{
		location.href="/member/join";
	}
</script>

</html>