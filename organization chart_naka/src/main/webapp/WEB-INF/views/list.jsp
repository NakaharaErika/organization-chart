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
    		<div class="col-md-6">
			<form action="sortList" method = "GET" class="sarch">
			<p>検索オプション</p>
				<fieldset class="row m-2">
					<legend class="col-form-label col-sm-2 pt-0">並び順</legend>
					<div class="col-sm-10">
			      		<div class="form-check col-4">
						<select name = "items" id="inputState" class="form-select mb-1">
							<option value = "Post.post_id">役職</option>
							<option value = "emp_code">社員番号</option>
						</select>
						<select name = "sort"  id="inputState" class="form-select">
							<option value = "asc">昇順</option>
							<option value = "desc">降順</option>
						</select>
						</div>
					</div>
				</fieldset>
				
				
				<fieldset class="row m-2">
				    <legend class="col-form-label col-sm-2 pt-0">閲覧部署</legend>
				    <div class="col-sm-10">
				        <div class="form-check">
				            <input class="form-check-input" type="radio" name="depId" value="0" id="gridRadios0" checked>
				            <label class="form-check-label" for="gridRadios0">全部署</label>
				        </div>
				        <%
				        List<DepartmentWork> departments = (List<DepartmentWork>) session.getAttribute("departments");
				        if (departments != null) {
				            for (int i = 0; i < departments.size(); i++) {
				                DepartmentWork department = departments.get(i);
				        %>
				                    <input class="form-check-input" type="radio" name="depId" value="<%= department.getDepId() %>" id="gridRadios<%= i+1 %>">
				                    <label class="form-check-label" for="gridRadios<%= i+1 %>"><%= department.getDepName() %></label>
				        <%
				            }
				        }
				        %>
				    </div>
				</fieldset>

				<button type="submit" class="btn btn-primary m-3">実行</button>
			</form>	
			</div>
			
			<div class="col-md-6">
				<form action = 'preCreate' method="post">
				 <button type="submit" class="btn btn-primary m-3">新規作成</button>
				</form>
			</div>
		</div>
	
	<br>
	<hr>
	
	<%
    String message = (String) request.getAttribute("message");
    if (message != null) { %>
        <p><%= message %></p>
    <% }
    String errormessage = (String) request.getAttribute("errormessage");
    if (errormessage != null) { %>
        <p><%= errormessage %></p>
    <% }
    %>
    
	<table class="table">
		<thead>
			<tr>
			 <th scope="col">#</th>
			 <th scope="col">社員番号</th>
			 <th scope="col">性</th>
			 <th scope="col">名</th>
			 <th scope="col">部署</th>
			 <th scope="col">役職</th>
			 <th scope="col">編集</th>
			 <th scope="col">削除</th>
		</thead>
		<% 
            List<HashMap<String, String>> rows = (List<HashMap<String, String>>) request.getAttribute("rows");
            int counter = 1;
            for (HashMap<String, String> columns : rows) {
            %>
                <tr>
                    <th scope="row"><%= counter++ %></th>
                    <td><a href='show?id=<%= columns.get("id") %>'><%= columns.get("emp_code") %></a></td>
                    <td><%= columns.get("family_name") %></td>
                    <td><%= columns.get("last_name") %></td>
                    <td><%= columns.get("dep_name") %></td>
                    <td><%= columns.get("post_name") %></td>
                    <td>
                        <% if ("1".equals(request.getAttribute("ediFlg")) || (loggedInUser.getDepId() == Integer.parseInt(columns.get("dep_id")))) { %>
	                        <a href='edit?id=<%= columns.get("id") %>' class="btn btn-outline-primary btn-sm">編集</a>
	                    <% } else { %>
	                        <p>-</p>
                    	<% } %>
                    </td>
                    <td>
                        <% if ("1".equals(request.getAttribute("delFlg")) || (loggedInUser.getDepId() == Integer.parseInt(columns.get("dep_id")))) { %>
                            <a href='show?id=<%= columns.get("id") %>' class="btn btn-outline-danger btn-sm">削除</a>
                        <% } else { %>
                            <p>-</p>
                        <% } %>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
	<br>
	
	<br>
	</div>
</body>
</html>