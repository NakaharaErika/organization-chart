<%@ page language="java"
contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="entity.DBWork" %>
<%@ page import="entity.DepartmentWork" %>
<%@ page import="entity.PostWork" %>
<%@ page import="service.DateUtil" %>
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
  		<form action="create" method="post">    
  		<table class="table">
			<thead>
				<tr>
				    <th scope="col">社員番号</th>
				    <th scope="col">性</th>
				    <th scope="col">名</th>
				    <th scope="col">所属部署</th>
				    <th scope="col">役職</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				<% HashMap<String, String> empDetails = (HashMap<String, String>) request.getAttribute("empDetails"); %>
				    <% if (empDetails != null) { %>
				    <input type="hidden" name="id" value='<%= empDetails.get("id") %>'>
					 <div class="row">
						<td><!-- 社員番号 -->
							<div class="col-auto">   
							  <input type="text" class="form-control" name="emp_code" placeholder="<%= empDetails.get("emp_code") %>">
							</div>
					    </td>
					    <td><!-- 性 -->
					    	<div class="col-auto">   
							  <input type="text" class="form-control" name="family_name" placeholder="<%= empDetails.get("family_name") %>">
							 </div>
					    </td>
					    <td><!-- 名 -->
					    	<div class="col-auto">   
							  <input type="text" class="form-control" name="last_name" placeholder="<%= empDetails.get("last_name") %>">
							</div>
					    </td>
					    <td><!-- 所属部署 -->
					    	<select name = "Employee.dep_id" id="inputState" class="form-select mb-1">
					    	<% 
				            List<DepartmentWork> departments = (List<DepartmentWork>) session.getAttribute("departments");
				            if (departments != null) {
				                for (DepartmentWork department : departments) {
				                    Integer depId = department.getDepId(); // 部署IDを取得
				                    String depName = department.getDepName(); // 部署名を取得
					        %>
					                        <option value = "<%= depId %>" <%= depName.equals(empDetails.get("dep_name"))? "selected" : "" %>><%= depName %></option>
					        <%
					                }
					            }
					        %>
							</select>
					    </td>
					    <td><!-- 役職 -->
					    	<select name = "Employee.post_id" id="inputState" class="form-select mb-1">
					    	<% 
				            List<PostWork> posts = (List<PostWork>) session.getAttribute("posts");
				            if (posts != null) {
				                for (PostWork post : posts) {
				                    Integer postId = post.getPostId(); // 役職IDを取得
				                    String postName = post.getPostName(); // 役職名を取得
					        %>
					                        <option value = "<%= postId %>" <%= postName.equals(empDetails.get("post_name"))? "selected" : "" %>><%= postName %></option>
					        <%
					                }
					            }
					        %>
							</select>
					    </td>
					</div>
				</tr>
			</tbody>
		</table>
		<table class="table">
			<thead>
				<tr>
				    <th scope="col">入社日</th>
				    <th scope="col">誕生日</th>
				</tr>
			</thead>
			<tbody>	
				<tr>
					    <td><!-- 入社日 -->
					    <div class="row">
  							<div class="col-auto">
							    <input type="text" class="form-control" name="hire_date_year" placeholder="<%= DateUtil.getYear(empDetails.get("hire_date")) %>" size="4">
							</div>
							<div class="col-auto">
							    <input type="text" class="form-control" name="hire_date_month" placeholder="<%= DateUtil.getMonth(empDetails.get("hire_date")) %>" size="2">
							</div>
							<div class="col-auto">
							    <input type="text" class="form-control" name="hire_date_day" placeholder="<%= DateUtil.getDay(empDetails.get("hire_date")) %>" size="2">
							</div>
						</div>
						</td>
						<td><!-- 誕生日 -->
						<div class="row">
  							<div class="col-auto">
							    <input type="text" class="form-control" name="birth_year" placeholder="<%= DateUtil.getYear(empDetails.get("Birth")) %>" size="4">
							</div>
							<div class="col-auto">
							    <input type="text" class="form-control" name="birth_month" placeholder="<%= DateUtil.getMonth(empDetails.get("Birth")) %>" size="2">
							</div>
							<div class="col-auto">
							    <input type="text" class="form-control" name="birth_day" placeholder="<%= DateUtil.getDay(empDetails.get("Birth")) %>" size="2">
							</div>
						</div>
						</td>
		    
				    <% } %>
				</tr>
			</tbody>
		</table>
		<button type="submit" class="btn btn-primary mb-2">保存する</button>
		</form>
		    <ul>
		      <li>
			      <form action="list" method="post" style="display: inline;">
			    	<button type="submit" style="background: none; border: none; color: blue; text-decoration: underline; cursor: pointer; padding: 0; font: inherit;">戻る</button>
				  </form>
			  </li>
			  <li><a href='show?id=<%= empDetails.get("id") %>'>キャンセル</a></li>
		    </ul>
 		</div>
</div>      
</body>
</html>