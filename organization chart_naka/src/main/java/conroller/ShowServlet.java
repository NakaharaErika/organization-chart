package conroller;

import java.io.IOException;
import java.util.HashMap;

import entity.DBWork;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.GetEmpDeteailById;
import service.SecurityService;

@WebServlet("/show")
public class ShowServlet extends HttpServlet {

    private GetEmpDeteailById list = new GetEmpDeteailById();
    SecurityService service = new SecurityService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	 HttpSession session = request.getSession();
         DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");

         if (loggedInUser != null) {
    	
		    	if (request.getAttribute("message") == null) {
		            request.setAttribute("message", "社員詳細");
		        }
		
		        String postId = request.getParameter("id"); // String 型のまま使用
		        
		      //編集・削除・パスワード変更権限の確認
				try {
					Boolean ediAllFlg = service.hasPermission(loggedInUser,"Edit Employee");
					Boolean ediPartFlg = service.hasPermission(loggedInUser,"Edit MydepEmp");
					Boolean delAllFlg = service.hasPermission(loggedInUser,"Delete Employee");
					Boolean delPartFlg = service.hasPermission(loggedInUser,"Delete MydepEmp");
					Boolean resFlg = service.hasPermission(loggedInUser,"Reset Password");
					
					request.setAttribute("ediAllFlg", ediAllFlg);
					request.setAttribute("ediPartFlg", ediPartFlg);
					request.setAttribute("delAllFlg", delAllFlg);
					request.setAttribute("delPartFlg", delPartFlg);
					request.setAttribute("resFlg", resFlg);
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				
		        	//社員情報の取り出し
		            HashMap<String, String> empDetails;
					try {
						empDetails = list.getEmpDeteailById(postId);
						request.setAttribute("empDetails", empDetails);
			            
					} catch (Exception e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					} 
		            
		        } else {
		        	response.sendRedirect("start");
		        }
         response.sendRedirect("list");
//        String view = "/WEB-INF/views/post.jsp";
//        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
//        dispatcher.forward(request, response);
    }
}
