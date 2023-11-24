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
  <title>ログイン画面</title>
</head>
<body>
    <h1>ログイン画面</h1>
    <%String message = (String)request.getAttribute("message"); %>
	<!-- エラーメッセージが存在するときだけ表示する -->
	<% if (message != null) {%>
	    <%=message %>
	<%} %>
	<!-- ログインフォーム。ユーザーIDとパスワードの入力を行う -->
	<form action="start" method="post">
		<span><strong>社員番号</strong></span>
	    <input type="text" name="user_id">
	    <br>
	    <span><strong>パスワード</strong></span>
	    <input type="password" name="password">
	    <br>
	    <br>
	    <input type="submit" value="ログイン">
	</form>
	<br>
	
</body>
</html>