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
  <title>人事検索システム</title>
  <style>ul {list-style: none; margin: 0; padding: 0;} li {float: left; margin-right: 20px; }</style>
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
  		<%String message = (String)request.getAttribute("message"); %>
		<% if (message != null) {%>
			    <%=message %>
		<%} %>
  		<div class="col-md-6 mt-3">    
  		<table class="table">
			<thead>
				<tr>
				    <th scope="col">社員番号</th>
				    <th scope="col">名前</th>
				    <th scope="col">所属部署</th>
				    <th scope="col">役職</th>
				    <th scope="col">入社日</th>
				    <th scope="col">誕生日</th>
				</tr>
			</thead>
			<tbody>	
				<tr>
					<% HashMap<String, String> empDetails = (HashMap<String, String>) request.getAttribute("empDetails"); %>
				    <% if (empDetails != null) { %>
				    <td><%= empDetails.get("emp_code") %></td>
				    <td><%= empDetails.get("family_name") + " " + empDetails.get("last_name") %></td>
				    <td><%= empDetails.get("dep_name") %></td>
				    <td><%= empDetails.get("post_name") %></td>
				    <td><%= empDetails.get("hire_date") %></td>
				    <td><%= empDetails.get("Birth") %></td>
				    <% } %>
				</tr>
			</tbody>
		</table>
		    <ul>
		      <li>
		        <form action="list" method="post" style="display: inline;">
		    		<button type="submit" style="background: none; border: none; color: blue; text-decoration: underline; cursor: pointer; padding: 0; font: inherit;">戻る</button>
				</form>
			  </li>
		      <li><p><a href='edit?id=<%= empDetails.get("id") %>'>編集</a></p></li>
		      <li><p><a href='destroy?id=<%= empDetails.get("id") %>'>削除</a></p></li>
		      <li><p><a href='resetPass?empCode=<%= empDetails.get("emp_code") %>'>パスワードをリセット</a><p><li>
		    </ul>
 		</div>
 		</div>
</div>      
</body>
</html>