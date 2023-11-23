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

@WebServlet("/check")
public class CheckServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session = request.getSession();
        DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");
			if(loggedInUser != null) {
				//セッションに登録された社員の役職コードで遷移先を判断
				Integer postId = loggedInUser.getPostId();
				if(postId > 0 && postId <= 2) {//閲覧権限あり
					//listサーブレットに遷移
					String view = "/list";
					RequestDispatcher dispatcher = request.getRequestDispatcher(view);
					dispatcher.forward(request, response);
				} else {//閲覧権限なし
					String view = "/WEB-INF/views/staff.jsp";
					RequestDispatcher dispatcher = request.getRequestDispatcher(view);
					dispatcher.forward(request, response);
				}
			} else {
		    	response.sendRedirect("start");
			}
	}
}
