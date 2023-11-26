package conroller;

import java.io.IOException;
import java.util.HashMap;

import entity.DBWork;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.GetEmpDeteailById;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	
	 private GetEmpDeteailById service = new GetEmpDeteailById();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session = request.getSession();
        DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
        	
	    	if (request.getAttribute("message") == null) {
	            request.setAttribute("message", "社員詳細");
	        }
	
	        String postId = request.getParameter("id"); // String 型のまま使用
	        	//社員情報の取り出し
	            HashMap<String, String> empDetails;
				try {
					empDetails = service.getEmpDeteailById(postId);
					request.setAttribute("empDetails", empDetails);
		            
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} 
	            
	        } else {
	        	response.sendRedirect("start");
	        }

            
            String view = "/WEB-INF/views/edit.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);

        
	}


}