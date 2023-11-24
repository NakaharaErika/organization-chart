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
    <%String errormessage = (String)request.getAttribute("errormessage"); %>
	<!-- エラーメッセージが存在するときだけ表示する -->
	<% if (errormessage != null) {%>
	    <%=errormessage %>
	<%} %>
	<!-- ユーザーIDとパスワードの入力を行う -->
	<form action="reset" method="post">
		<%String empCode = (String)request.getAttribute("empCode"); %>
		<input type="hidden" name="<%=empCode %>">
		<p>パスワードを変更する社員番号：<%=empCode %></p>
		<span><strong>現在の社員番号</strong></span>
	    <input type="text" name="crrEmp">
	    <br>
	    <span><strong>新しいパスワード</strong></span>
	    <input type="password" name="password">
	    <br>
	    <br>
	    <input type="submit" value="パスワードをリセット">
	</form>
	<br>
	 <form action="list" method="post" style="display: inline;">
		    		<button type="submit" style="background: none; border: none; color: blue; text-decoration: underline; cursor: pointer; padding: 0; font: inherit;">戻る</button>
	 </form>
	
</body>
</html>