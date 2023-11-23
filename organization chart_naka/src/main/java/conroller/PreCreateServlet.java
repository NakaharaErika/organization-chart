package conroller;

import java.io.IOException;

import entity.DBWork;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.CreateEmp;

@WebServlet("/preCreate")
public class PreCreateServlet extends HttpServlet {
	
	 private CreateEmp service = new CreateEmp();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session = request.getSession();
        DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");
        
        String view = null;
        if (loggedInUser != null) {
        	
	    	if (request.getAttribute("message") == null) {
	            request.setAttribute("message", "新規登録権限は課長以上のみです");
	        }
	
	        int postId = loggedInUser.getPostId();
	        
	        if(postId == 1) {
	        	//一番最新の社員番号を取得
	            String empDetails;
				try {
					empDetails = service.getPreEmp();
					request.setAttribute("empDetails", empDetails);
					view = "/WEB-INF/views/create.jsp";
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} 
	        } else {
	        	view = "list";
	        }
        } else {
        	response.sendRedirect("start");
        }
        
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);

        
	}


}