<%@ page language="java"
contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="entity.DBWork" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/style.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
  <title>パスワードをリセット</title>
</head>
<body>
    <header>
	    <h1>人事検索システム</h1>
	    
	    <% DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser"); %>
	     <% if (loggedInUser != null) { %>
	        <p>ようこそ、<%= loggedInUser.getDepName() + " の " + loggedInUser.getFamilyName() + " " + loggedInUser.getLastName() %>さん</p>
	    <% } %>
			
	    <nav class="pc-nav">
	    	<form action="logout" method="POST"><input type="submit" value="ログアウト"></form>
	    </nav>
	</header>
<div class="container">
  		<div class="row mt-3">

    <h2>パスワードをリセット</h2>
    <%String errormessage = (String)request.getAttribute("message"); %>
	<!-- エラーメッセージが存在するときだけ表示する -->
	<% if (errormessage != null) {%>
	    <%=errormessage %>
	<%} %>
	<!-- ユーザーIDとパスワードの入力を行う -->
	<form action="reset" method="post">
		<%String empCode = (String)request.getAttribute("empCode"); %>
		<input type="hidden" name="empCode" value="<%=empCode %>">
		<p>パスワードを変更する社員番号：<%=empCode %></p>
		<div class="w-50">
			<span><strong>現在の社員番号</strong></span>
		    <input type="text" name="crrEmp" class="form-control" size="4">
		</div>
	    <br>
	    <div class="w-50">
		    <span><strong>新しいパスワード</strong></span>
		    <input type="password" name="password" class="form-control" size="10">
		</div>
	    <br>
	    <br>
	    <button type="submit" class="btn btn-success">パスワードをリセット</button>
	</form>
	<br>
	 <form action="list" method="post" style="display: inline;">
		    		<button type="submit" style="background: none; border: none; color: blue; text-decoration: underline; cursor: pointer; padding: 0; font: inherit;">戻る</button>
	 </form>
	</div>
</div>
</body>
</html>