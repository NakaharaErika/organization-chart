<%@ page language="java"
contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="entity.DBWork" %>
<%@ page import="entity.DepartmentWork" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/style.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
  <title>Todoリスト</title>
</head>
<body>
	<header>
	    <h1>Todo新規作成</h1>
	    
	    <% DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser"); %>
				<% if (loggedInUser != null) { %>
				    <p>ようこそ、<%= loggedInUser.getName() %>さん</p>
				<% } %>
	    <nav class="pc-nav">
	    	<form action="logout" method="POST"><input type="submit" value="ログアウト"></form>
	    </nav>
	</header>
	
	<p><%= request.getAttribute("message") %></p>
	
	<form action="create" method="get">
		<label for="title">タイトル</label><br>
		<input type="text" name="title"><br>
		<br>
		<label for="content">本文</label><br>
		<textarea name="content" cols="30" rows="10"></textarea><br>
		<br>
		<label for="date">締切日</label><br>
		<input type="date" name="date"><br>
		
	
			<label for="genre">ジャンル</label><br>
			<select name="genre" id="genre">
			    <% List<Genre> userGenres = (List<Genre>) request.getAttribute("userGenres");
			       for (Genre genre : userGenres) {
			    %>
			    <option value="<%= genre.getGenreId() %>" <%= selectedGenre == genre.getGenreId() ? "selected" : "" %>><%= genre.getGenreName() %></option>
			    <% } %>
			</select>
			
		<br>
		
		<label for="priority">優先度</label><br>
		<% List<HashMap<String, String>> priorities = (List<HashMap<String, String>>) request.getAttribute("priorities"); %>
		<% for (HashMap<String, String> prioritylist : priorities) { %>
		    <input type="radio" name="priority" value="<%= prioritylist.get("id") %>"> <%= prioritylist.get("priorityLevel") %>
		<% } %>
		<br>

		
		
		<button type="submit">保存する</button>
	</form>
	
	<ul>
		<li><p><a href='list'>戻る</a></p></li>
	</ul>
</body>
</html>