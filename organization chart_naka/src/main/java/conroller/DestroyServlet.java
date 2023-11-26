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
import service.DestroyEmp;

@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
	
	private DestroyEmp service = new DestroyEmp();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getAttribute("message") == null) {
			request.setAttribute("message", "人事システム");
		}
		
        HttpSession session = request.getSession();
        DBWork loggedInUser = (DBWork) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {

    		String empId = request.getParameter("id"); // String 型のまま使用
        	try {
				service.destroyEmp(empId);
				request.setAttribute("errormessage", "削除が完了しました");
			} catch (Exception e) {
				request.setAttribute("errormessage", "不正な操作です");
				String view = "list";
		        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		        dispatcher.forward(request, response);
			}
            
   
	        String view = "list";
	        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
	        dispatcher.forward(request, response);
		} else {
	    	response.sendRedirect("start");
	    }
	}
}