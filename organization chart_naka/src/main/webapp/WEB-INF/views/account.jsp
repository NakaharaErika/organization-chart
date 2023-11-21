<%@ page language="java"
contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>アカウント新規作成</title>
</head>
<body>
    <h1>新規作成</h1>
    <%String message = (String)request.getAttribute("message"); %>
	<!-- エラーメッセージが存在するときだけ表示する -->
	<% if (message != null) {%>
	    <%=message %>
	<%} %>
	<!-- ログインフォーム。ユーザーIDとパスワードの入力を行う -->
	<form action="account" method="POST">
		<span><strong>ユーザーID</strong></span>
	    <input type="text" name="user_id">
	    <br>
	    <span><strong>パスワード</strong></span>
	    <input type="text" name="password">
	    <br>
	    <span><strong>ユーザー名</strong></span>
	    <input type="text" name="name">
		<br>
		<br>    
	    <input type="submit" value="新規登録">
	</form>
	
</body>
</html>