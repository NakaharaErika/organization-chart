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

@WebServlet("/check")
public class CheckServlet extends HttpServlet {
	
	SecurityService service = new SecurityService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session = request.getSession();
        DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");
			if(loggedInUser != null) {
				//セッションに登録された社員がReadEmployeeを保持する場合のみ、閲覧ページへ移動
				//情報管理部は平社員も全ての権限を持つ
				try {
					if(service.hasPermission(loggedInUser, "Read Employee")) {//閲覧権限あり
						//listサーブレットに遷移
						String view = "/list";
						RequestDispatcher dispatcher = request.getRequestDispatcher(view);
						dispatcher.forward(request, response);
					} else {//閲覧権限なし
						String view = "/WEB-INF/views/staff.jsp";
						RequestDispatcher dispatcher = request.getRequestDispatcher(view);
						dispatcher.forward(request, response);
					}
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			} else {
		    	response.sendRedirect("aaa");
			}
	}
}
