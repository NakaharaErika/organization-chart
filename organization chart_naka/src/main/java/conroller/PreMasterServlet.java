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
import service.SecurityService;
import service.SelectMaster;

@WebServlet("/crud")
public class PreMasterServlet extends HttpServlet {
	
	  SelectMaster list = new SelectMaster();
	 SecurityService service = new SecurityService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session = request.getSession();
        DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");
        
        
        String view = null;
        if (loggedInUser != null) {
        	String mst = request.getParameter("table");
	    	if (request.getAttribute("errormessage") == null) {
	            request.setAttribute("errormessage", "権限は情報管理部のみです");
	        }
	    	//権限を確認
	    	try {
				if(service.hasPermission(loggedInUser, "CRUD All Tables")) {
				    	//一番最新の社員番号を取得
						try {
							List<HashMap<String, String>> mstList;
							mstList = list.selectMster(mst);
							request.setAttribute("mstList", mstList);
							request.setAttribute("table", mst);
							request.setAttribute("message", "現在のテーブル：" + mst);
							view = "/WEB-INF/views/master.jsp";
						} catch (Exception e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						} 
				    } else {
				    	view = "list";
				    }
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
        } else {
        	response.sendRedirect("start");
        }
        
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);
	}
}