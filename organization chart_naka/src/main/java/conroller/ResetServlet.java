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
import service.SecurityService;

@WebServlet("/resetPass")
public class ResetServlet extends HttpServlet {
	
	SecurityService service = new SecurityService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getAttribute("message") == null) {
			request.setAttribute("message", "人事システム");
		}
		
        HttpSession session = request.getSession();
        DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");
        String errormessage;
        String view = null;
        if (loggedInUser != null) {
        	//押下者が情報管理部の人間かどうか確認
        	try {
				if (service.hasPermission(loggedInUser,"Reset Password")) {
				//表示下のpostを保持
					String empCode = request.getParameter("empCode");
					request.setAttribute("empCode", empCode);
					view = "/WEB-INF/views/reset.jsp";
					errormessage = "パスワードをリセットします";
				} else {
					//パスワードのリセット画面を表示
					errormessage = "リセット権限は情報管理部のみです";
					request.setAttribute("message", errormessage);
					view = "list";        
				}
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
 
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
        } else {
	    	response.sendRedirect("start");
		}
	}
}
