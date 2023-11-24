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
import service.SecurityService;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
	
	SecurityService service = new SecurityService();
	GetEmpListById list = new GetEmpListById();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//初回ログイン時の飛び先
		 HttpSession session = request.getSession();
	     DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");
	     
	 if (loggedInUser != null) {
	    	//社員リストを格納
		 	//getで受け取った時や初期表示は自分の部署だけ降順で表示させる
		 	//編集権限を確認（Reaad Employee or 情報部)
	    	List<HashMap<String, String>> empList;
			try {
				empList = list.getEmpListById(loggedInUser.getDepId(),"asc");
				//閲覧権限の確認
				String ediFlg = (service.hasPermission(loggedInUser, "Edit Employee"))? "1":"";
				request.setAttribute("ediFlg", ediFlg);
				//削除権限の確認
				String delFlg = (service.hasPermission(loggedInUser, "Delete Employee"))? "1":"";
				request.setAttribute("delFlg", delFlg);
				
				//初期セットのメッセージを出力
				request.setAttribute("message", "現在の表示：あなたの所属部署");
				request.setAttribute("rows", empList);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}      
	 } else {
		response.sendRedirect("bbb");
     }
	 	
	    String view = "/WEB-INF/views/list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);   
	}
}
