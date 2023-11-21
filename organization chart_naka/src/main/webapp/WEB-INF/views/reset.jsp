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
  <title>パスワードをリセット</title>
</head>
<body>
    <h1>パスワードをリセット</h1>
    <%String message = (String)request.getAttribute("message"); %>
	<!-- エラーメッセージが存在するときだけ表示する -->
	<% if (message != null) {%>
	    <%=message %>
	<%} %>
	<!-- ユーザーIDとパスワードの入力を行う -->
	<form action="reset" method="post">
		<span><strong>現在のユーザーID</strong></span>
	    <input type="text" name="user_id">
	    <br>
	    <span><strong>新しいパスワード</strong></span>
	    <input type="password" name="password">
	    <br>
	    <br>
	    <input type="submit" value="パスワードをリセット">
	</form>
	<br>
	<p><a href='start'>戻る</a><p>
	
</body>
</html>