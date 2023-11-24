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

@WebServlet("/resetAcc")
public class ResetServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getAttribute("message") == null) {
			request.setAttribute("message", "人事システム");
		}
		
        HttpSession session = request.getSession();
        DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");
        String errormessage;
        String view;
        if (loggedInUser != null) {
        	//押下者が情報管理部の人間かどうか確認
        	if (loggedInUser.getDepId() == 2) {
        	//表示下のpostを保持
	    		String empCode = request.getParameter("empCode");
	    		request.setAttribute("empCode", empCode);
	    		view = "/WEB-INF/views/reset.jsp";
	    		errormessage = "パスワードをリセットします";
        	} else {
        		errormessage = "リセット権限は情報管理部のみです";
        		view = "list";
        	}
        	
		//パスワードのリセット画面を表示
        request.setAttribute("message", errormessage);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
        } else {
	    	response.sendRedirect("start");
		}
	}
}
