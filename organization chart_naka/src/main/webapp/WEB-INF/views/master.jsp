<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
  <title>人事検索システム</title>
</head>
<body>
<% DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser"); %>
	<header>
        <h1>人事検索システム</h1>
        <% if (session.getAttribute("loggedInUser") != null) { %>
            <p>ようこそ、<%= loggedInUser.getDepName() %> の <%= loggedInUser.getFamilyName() %> <%= loggedInUser.getLastName() %> さん</p>
        <% } %>
			
	    <nav class="pc-nav">
	    	<form action="logout" method="POST"><input type="submit" value="ログアウト"></form>
	    </nav>
	</header>
	
	
	
	<div class="container">
  		<div class="row ">
    		
    <%
    String errormessage = (String) request.getAttribute("errormessage");
    if (errormessage != null) { %>
        <p><%= errormessage %></p>
    <% }%>
    <br>
    <%
    String message = (String) request.getAttribute("message");
    if (message != null) { %>
        <p><%= message %></p>
    <% } %>
	<table class="table">
		<thead>
			<tr>
			 <th scope="col">id</th>
			 <th scope="col">要素名</th>
			 <th scope="col">編集</th>
			 <th scope="col">削除</th>
		</thead>
		<% 
            List<HashMap<String, String>> rows = (List<HashMap<String, String>>) request.getAttribute("mstList");
            for (HashMap<String, String> row : rows) {
            	//HashMapではキーを指定する必要があるので、一旦キーをArrayListに格納
            	List<String>keys = new ArrayList<>(row.keySet());
            %>
                <tr>
                    <td><%= row.get(keys.get(0)) %></a></td>
                    <td><%= row.get(keys.get(1)) %></td>
                    <td>
			                <form action="edit" method="post">
			                    <input type="hidden" name="id" value="<%= row.get(keys.get(0)) %>">
			                    <button type="submit" class="btn btn-outline-primary btn-sm">編集</button>
			                </form>
			        </td>
			        <td>
			            	<form action="destroy" method="post">
			                    <input type="hidden" name="id" value="<%= row.get(keys.get(0)) %>">
			                    <button type="submit" class="btn btn-outline-danger btn-sm">削除</button>
			                </form>
			        </td>
                </tr>
            <% } %>
        </tbody>
    </table>
	<br>
	<form action="insert" method="post">
                <input type="hidden" name="id" value="<%= (String) request.getAttribute("table") %>">
             	<button type="submit" class="btn btn-outline-success btn-sm">カラムを追加</button>
	</form>
	<br>
	<form action="list" method="post" style="display: inline;">
		    		<button type="submit" style="background: none; border: none; color: blue; text-decoration: underline; cursor: pointer; padding: 0; font: inherit;">戻る</button>
				</form>
	</div>
</body>
</html>