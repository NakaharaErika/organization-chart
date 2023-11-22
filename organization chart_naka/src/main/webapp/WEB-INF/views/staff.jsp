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
  <title>人事検索システム</title>
  <style>ul {list-style: none; margin: 0; padding: 0;} li {float: left; margin-right: 20px; }</style>
  <link rel="stylesheet" href="./css/style.css">
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
    
    <p>あなたの役職は <%= loggedInUser.getPostName()%> なので、人事検索システムの閲覧権限はありません。</p>
</body>
</html>