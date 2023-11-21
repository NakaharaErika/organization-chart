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
  <title>組織表検索システム</title>
</head>
<body>
	<header>
	    <h1>Todoリスト</h1>
	    
	    <% DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser"); %>
				<% if (loggedInUser != null) { %>
				    <p>ようこそ、<%= loggedInUser.getName() %>さん</p>
				<% } %>
				<!--　まるまる課も表示させたい  -->
	    <nav class="pc-nav">
	    	<form action="logout" method="POST"><input type="submit" value="ログアウト"></form>
	    </nav>
	</header>
	
	<p><a href='new'>新規登録</a><p><!-- 社員の追加登録 -->		
	<form action="sort" method = "POST">
		<p>組織票の並び順を指定してください</p>
		<select name = "items">
			<option selected value = "#">項目</option> 
			<option value = "id">id</option>
			<option value = "date">登録日</option>
			<option value = "genre">ジャンル</option>
			<option value = "priority">優先度</option>
		</select>
		<select name = "sort">
			<option selected value = "#">並び替え順</option> 
			<option value = "asc">昇順</option>
			<option value = "desc">降順</option>
		</select>
		<input type = "submit" value = "実行">
		<br>
	</form>	
	<br>
	<hr>
	<% String message = (String) request.getAttribute("message"); %>
			<% if (message != null) { %>
			    <p><%= message %></p>
			<% } %>
	
	<table class="table">
		<thead>
			<tr>
			 <th scope="col">ID</th>
			 <th scope="col">タイトル</th>
			 <th scope="col">ジャンル</th>
			 <th scope="col">登録日</th>
			 <th scope="col">優先度</th>
		</thead>
		<tbody>	
			<% ArrayList<HashMap<String, String>> rows = (ArrayList<HashMap<String, String>>)request.getAttribute("rows"); %>
			<% int counter = 1; %>
			<% for(HashMap<String,String> columns : rows){ %>
			<tr>
			    <th scope="row"><%= counter++ %></th>
			    <td><a href='show?id=<%= columns.get("No") %>'><%= columns.get("title") %></a></td>
			    <td><%= columns.get("genre") %></td>
			    <td><%= columns.get("date") %></td>
			    <td><%= columns.get("priorityLevel") %></td>
			 <tr>
			<% } %>
		</tbody>
	</table>
	
	<br>
	<p><a href='editGenre'>ジャンルの編集</a><p>
	<br>
	
</body>
</html>