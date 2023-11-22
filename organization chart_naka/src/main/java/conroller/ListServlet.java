package conroller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import entity.DBWork;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.GetEmpListById;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
	
	GetEmpListById list = new GetEmpListById();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
	     DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");
	 if (loggedInUser != null) {
	    	//社員リストを格納
		 	//getで受け取った時や初期表示は自分の部署だけ降順で表示させる
	    	List<HashMap<String, String>> empList;
			try {
				empList = list.getEmpListById(loggedInUser.getDepId(),"asc");
				request.setAttribute("rows", empList);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}      
	 } else {
		response.sendRedirect("login");
    }
	 	
	    String view = "/WEB-INF/views/list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	    
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
	     DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");
	 if (loggedInUser != null) {
	    	//ユーザーのtodoリストを格納
	    	List<HashMap<String, String>> todos;
			try {
				todos = list.getTodoListByUserId(loggedInUser.getNo());
				request.setAttribute("rows", todos);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}      
	 } else {
		response.sendRedirect("login");
   }
	 	
	    String view = "/WEB-INF/views/list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	    
	}
	
}
